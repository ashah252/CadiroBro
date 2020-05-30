package com.equinox.cadiro.api

import com.equinox.cadiro.api.models.{ArmourFilterType, ArmourFilters, ArmourOption, FilterList}


case class Min(value: Int)
case class Max(value: Int)

abstract class CadiroFilter() {
	def integrate(filterList: FilterList): FilterList
}

sealed trait ArmourFilterOption
object ArmourFilterOption{
	case object Armour extends ArmourFilterOption
	case object Evasion extends ArmourFilterOption
	case object EnergyShieldFilter extends ArmourFilterOption
	case object BlockFilter extends ArmourFilterOption
}


case class ArmourFilter(`type`: ArmourFilterOption, min: Option[Min], max: Option [Max]) extends CadiroFilter {

	def toOptionModel: ArmourOption = {
		this match {
			case ArmourFilter(_, Some(min), Some(max)) => ArmourOption(Some(min.value), Some(max.value))
			case ArmourFilter(_, None, Some(max)) => ArmourOption(None, Some(max.value))
			case ArmourFilter(_, Some(min), None) => ArmourOption(Some(min.value), None)
			case _ => ArmourOption(None, None)
		}
	}

	override def integrate(filterList: FilterList): FilterList = {

		filterList.armour_filters match {
			case Some(armourFilters) =>
				`type` match {
					case ArmourFilterOption.Armour =>
						filterList.copy(armour_filters = Some(armourFilters.copy(filters = armourFilters.filters.copy(ar = Some(toOptionModel)))))

					case ArmourFilterOption.BlockFilter =>
						filterList.copy(armour_filters = Some(armourFilters.copy(filters = armourFilters.filters.copy(block = Some(toOptionModel)))))

					case ArmourFilterOption.EnergyShieldFilter =>
						filterList.copy(armour_filters = Some(armourFilters.copy(filters = armourFilters.filters.copy(es = Some(toOptionModel)))))

					case ArmourFilterOption.Evasion =>
						filterList.copy(armour_filters = Some(armourFilters.copy(filters = armourFilters.filters.copy(ev = Some(toOptionModel)))))

					case _ => filterList

				}

			case None => integrate(filterList.copy(armour_filters = Some(ArmourFilters(ArmourFilterType(None, None, None, None)))))
		}
	}
}






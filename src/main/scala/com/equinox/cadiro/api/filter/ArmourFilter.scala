package com.equinox.cadiro.api.filter

import com.equinox.cadiro.api.models.{ArmourFilterType, ArmourFilters, FilterList}

sealed trait ArmourFilterOption extends CadiroFilterOption
object ArmourFilterOption{
	case object Armour extends ArmourFilterOption
	case object Evasion extends ArmourFilterOption
	case object EnergyShieldFilter extends ArmourFilterOption
	case object BlockFilter extends ArmourFilterOption
}

case class ArmourFilter(override val `type`: ArmourFilterOption, override val range: Option[Range]) extends MinMaxFilter(`type`, range) {

	override def integrate(filterList: FilterList): FilterList = {

		filterList.armour_filters match {
			case Some(armourFilters) =>
				`type` match {
					case ArmourFilterOption.Armour =>
						filterList.copy(armour_filters = Some(armourFilters.copy(filters = armourFilters.filters.copy(ar = Some(toMinMaxOptionModel)))))

					case ArmourFilterOption.BlockFilter =>
						filterList.copy(armour_filters = Some(armourFilters.copy(filters = armourFilters.filters.copy(block = Some(toMinMaxOptionModel)))))

					case ArmourFilterOption.EnergyShieldFilter =>
						filterList.copy(armour_filters = Some(armourFilters.copy(filters = armourFilters.filters.copy(es = Some(toMinMaxOptionModel)))))

					case ArmourFilterOption.Evasion =>
						filterList.copy(armour_filters = Some(armourFilters.copy(filters = armourFilters.filters.copy(ev = Some(toMinMaxOptionModel)))))

				}

			case None => integrate(filterList.copy(armour_filters = Some(ArmourFilters(ArmourFilterType(None, None, None, None)))))
		}
	}
}

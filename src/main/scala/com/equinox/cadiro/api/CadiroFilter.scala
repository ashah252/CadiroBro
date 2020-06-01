package com.equinox.cadiro.api

import com.equinox.cadiro.api.models.{ArmourFilterType, ArmourFilters, ArmourOption, FilterList, SocketFilterType, SocketFilters, SocketOption}


case class Min(value: Int)
case class Max(value: Int)

trait CadiroFilterOption
abstract class CadiroFilter(`type`: CadiroFilterOption) {
	def integrate(filterList: FilterList): FilterList
	def getType: String = `type`.toString
}



sealed trait ArmourFilterOption extends CadiroFilterOption
object ArmourFilterOption{
	case object Armour extends ArmourFilterOption
	case object Evasion extends ArmourFilterOption
	case object EnergyShieldFilter extends ArmourFilterOption
	case object BlockFilter extends ArmourFilterOption
}


case class ArmourFilter(`type`: ArmourFilterOption, min: Option[Min], max: Option [Max]) extends CadiroFilter(`type`) {

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

				}

			case None => integrate(filterList.copy(armour_filters = Some(ArmourFilters(ArmourFilterType(None, None, None, None)))))
		}
	}
}


sealed trait SocketFilterOption extends CadiroFilterOption
object SocketFilterOption {
	case object Sockets extends SocketFilterOption
	case object Links extends SocketFilterOption
}
case class SocketFilter(`type`: SocketFilterOption, socketFilter: SocketOption) extends CadiroFilter(`type`) {
	override def integrate(filterList: FilterList): FilterList = {

		filterList.socket_filters match {
			case Some(socketFilters) =>
				`type` match {

					case SocketFilterOption.Sockets => filterList.copy(socket_filters = Some(socketFilters.copy(filters = socketFilters.filters.copy(sockets = Some(socketFilter)))))

					case SocketFilterOption.Links => filterList.copy(socket_filters = Some(socketFilters.copy(filters = socketFilters.filters.copy(links = Some(socketFilter)))))

				}

			case None => integrate(filterList.copy(socket_filters = Some(SocketFilters(SocketFilterType(None, None)))))
		}

	}

}




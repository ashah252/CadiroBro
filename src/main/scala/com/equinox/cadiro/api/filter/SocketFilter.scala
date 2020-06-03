package com.equinox.cadiro.api.filter

import com.equinox.cadiro.api.models._

sealed trait SocketFilterOption extends CadiroFilterOption
object SocketFilterOption {
	case object Sockets extends SocketFilterOption
	case object Links extends SocketFilterOption
}


case class SocketFilter(override val `type`: SocketFilterOption, override val range: Option[Range]) extends MinMaxFilter(`type`, range) {

	private case class SocketColor(var blue: Option[Int], var green: Option[Int], var red: Option[Int], var white: Option[Int])

	private val colors: SocketColor = SocketColor(None, None, None, None)

	def toSocketOptionModel: SocketOption = {
		val minMaxOption: MinMaxOption = toMinMaxOptionModel
		SocketOption(colors.blue, colors.green, colors.red, colors.white, minMaxOption.min, minMaxOption.max)
	}

	def withBlueColors(colorCount: Int): SocketFilter = {
		colors.blue = Some(colorCount)
		this
	}

	def withGreenColors(colorCount: Int): SocketFilter = {
		colors.green = Some(colorCount)
		this
	}

	def withRedColors(colorCount: Int): SocketFilter = {
		colors.red = Some(colorCount)
		this
	}

	def withWhiteColors(colorCount: Int): SocketFilter = {
		colors.white = Some(colorCount)
		this
	}

	override def integrate(filterList: FilterList): FilterList = {

		filterList.socket_filters match {
			case Some(socketFilters) =>
				`type` match {

					case SocketFilterOption.Sockets => filterList.copy(socket_filters = Some(socketFilters.copy(filters = socketFilters.filters.copy(sockets = Some(toSocketOptionModel)))))

					case SocketFilterOption.Links => filterList.copy(socket_filters = Some(socketFilters.copy(filters = socketFilters.filters.copy(links = Some(toSocketOptionModel)))))

				}

			case None => integrate(filterList.copy(socket_filters = Some(SocketFilters(SocketFilterType(None, None)))))
		}

	}

}

package com.equinox.cadiro.api.models

import play.api.libs.json.{Json, OFormat}

case class SearchQueryRoot(
                            query: SearchQuery,
                            sort: Option[SortingOption]
                          )

case class SearchQuery(
                      status: StatusOption,
                      name: String,
                      filters: Option[FilterList],
                      `type`: Option[String],
                      )

case class SortingOption(price: String)

case class StatusOption(option: String)

case class FilterList(
                        armour_filters: Option[ArmourFilters],
                        socket_filters: Option[SocketFilters]
                     )


case class ArmourFilters(
                        filters: Option[ArmourFilterType]
                     )



case class ArmourFilterType(
                          ar: Option[ArmourOption],
                          es: Option[ArmourOption],
                          ev: Option[ArmourOption],
                          block: Option[ArmourOption]

                        )

case class ArmourOption(
                         min: Option[Int],
                         max: Option[Int]
                       )


case class SocketFilters(
                             filters: Option[SocketFilterType]
                           )

case class SocketFilterType(
                          sockets: Option[SocketOption],
                          links:  Option[SocketOption]
                       )

case class SocketOption(
                         b: Option[Int],
                         g: Option[Int],
                         r: Option[Int],
                         w: Option[Int],
                         min: Option[Int],
                         max: Option[Int]
                       )

object SearchQueryRoot {
  implicit val searchQueryRootFormat: OFormat[SearchQueryRoot] = Json.format[SearchQueryRoot]
}

object SearchQuery {
  implicit val searchQueryRootFormat: OFormat[SearchQuery] = Json.format[SearchQuery]
}

object SortingOption {
  implicit val searchQueryRootFormat: OFormat[SortingOption] = Json.format[SortingOption]
}

object StatusOption {
  implicit val searchQueryRootFormat: OFormat[StatusOption] = Json.format[StatusOption]
}

object FilterList {
  implicit val searchFilterListFormat: OFormat[FilterList] = Json.format[FilterList]
}

object ArmourFilterType {
  implicit val searchArmourFilterTypeFormat: OFormat[ArmourFilterType] = Json.format[ArmourFilterType]
}

object SocketFilterType {
  implicit val searchSocketFilterTypeFormat: OFormat[SocketFilterType] = Json.format[SocketFilterType]
}


object ArmourFilters {
  implicit val searchArmourFiltersFormat: OFormat[ArmourFilters] = Json.format[ArmourFilters]
}

object ArmourOption {
  implicit val searchArmourOptionFormat: OFormat[ArmourOption] = Json.format[ArmourOption]
}

object SocketFilters {
  implicit val searchSocketFiltersFormat: OFormat[SocketFilters] = Json.format[SocketFilters]
}

object SocketOption {
  implicit val searchSocketOptionFormat: OFormat[SocketOption] = Json.format[SocketOption]
}
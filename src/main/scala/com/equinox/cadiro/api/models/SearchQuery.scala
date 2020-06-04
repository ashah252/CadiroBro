package com.equinox.cadiro.api.models

import play.api.libs.json.{Json, OFormat}

case class SearchQueryRoot(
                            query: SearchQuery,
                            sort: Option[SortingOption]
                          )

case class SearchQuery(
                      status: StatusOption,
                      name: Option[String],
                      filters: Option[FilterList],
                      `type`: Option[String],
                      )

case class SortingOption(price: String)

case class StatusOption(option: String)

case class FilterList(
                        type_filters: Option[TypeFilters],
                        armour_filters: Option[ArmourFilters],
                        socket_filters: Option[SocketFilters],
                        weapon_filters: Option[WeaponFilters]
                     )



case class MinMaxOption(
                         min: Option[Int],
                         max: Option[Int]
                       )

case class SimpleOption(
                       option: String
                       )

case class TypeFilters(
                      filters: TypeFilterType
                      )

case class TypeFilterType(
                          category: Option[SimpleOption],
                          rarity: Option[SimpleOption]
                         )

case class WeaponFilters(
                        filters: WeaponFilterType
                        )

case class WeaponFilterType(
                             damage: Option[MinMaxOption],
                             pdps: Option[MinMaxOption],
                             crit: Option[MinMaxOption],
                             aps: Option[MinMaxOption],
                             dps: Option[MinMaxOption],
                             edps: Option[MinMaxOption]
                           )

case class ArmourFilters(
                        filters: ArmourFilterType
                     )



case class ArmourFilterType(
                          ar: Option[MinMaxOption],
                          es: Option[MinMaxOption],
                          ev: Option[MinMaxOption],
                          block: Option[MinMaxOption]

                        )


case class SocketFilters(
                             filters: SocketFilterType
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

object WeaponFilters {
  implicit val searchWeaponFiltersFormat: OFormat[WeaponFilters] = Json.format[WeaponFilters]
}

object WeaponFilterType {
  implicit val searchWeaponFilterTypeFormat: OFormat[WeaponFilterType] = Json.format[WeaponFilterType]
}


object ArmourFilters {
  implicit val searchArmourFiltersFormat: OFormat[ArmourFilters] = Json.format[ArmourFilters]
}

object TypeFilters {
  implicit val searchTypeFiltersFormat: OFormat[TypeFilters] = Json.format[TypeFilters]
}

object TypeFilterType {
  implicit val searchTypeFilterTypeFormat: OFormat[TypeFilterType] = Json.format[TypeFilterType]
}

object SimpleOption {
  implicit val searchSimpleOptionFormat: OFormat[SimpleOption] = Json.format[SimpleOption]
}

object MinMaxOption {
  implicit val searchMinMaxOptionFormat: OFormat[MinMaxOption] = Json.format[MinMaxOption]
}

object SocketFilters {
  implicit val searchSocketFiltersFormat: OFormat[SocketFilters] = Json.format[SocketFilters]
}

object SocketOption {
  implicit val searchSocketOptionFormat: OFormat[SocketOption] = Json.format[SocketOption]
}
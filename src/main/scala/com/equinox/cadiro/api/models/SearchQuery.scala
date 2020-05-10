package com.equinox.cadiro.api.models

import play.api.libs.json.{Json, OFormat}

case class SearchQueryRoot(
                            query: SearchQuery,
                            sort: Option[SortingOption]
                          )

case class SearchQuery(
                      status: StatusOption,
                      name: String,
                      `type`: Option[String],
                      )

case class SortingOption(price: String)

case class StatusOption(option: String)

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
package com.equinox.cadiro.api.models

import play.api.libs.json.{Json, OFormat}

case class SearchResult(
                       id: String, //param to add in get request
                       complexity: Int,
                       result: Option[List[String]] //iterable identifiers
                       )


object SearchResult {
  implicit val searchResult: OFormat[SearchResult] = Json.format[SearchResult]
}
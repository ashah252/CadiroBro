package com.equinox.cadiro.api.models

import play.api.libs.json.{Json, OFormat}

case class StaticStatCategory(
                       label: Option[String],
                       entries: Option[List[StaticStatEntry]]
                     )

case class StaticStatEntry(
                            id: String,
                            text: String,
                            `type`: Option[String]
                          )

case class StaticStats(
                      result: List[StaticStatCategory]
                      )


object StaticStats {
  implicit val staticItemsFormat: OFormat[StaticStats] = Json.format[StaticStats]
}

object StaticStatCategory {
  implicit val staticItemFormat: OFormat[StaticStatCategory] = Json.format[StaticStatCategory]
}

object StaticStatEntry {
  implicit val staticItemEntryFormat: OFormat[StaticStatEntry] = Json.format[StaticStatEntry]
}

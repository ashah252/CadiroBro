package com.equinox.cadiro.api.models

import play.api.libs.json.{Json, OFormat}

case class Flags(
                  unique: Option[Boolean],
                  prophecy: Option[Boolean]
                )

case class StaticItemEntry(
                            name: Option[String],
                            id: Option[String],
                            `type`: Option[String],
                            text: String,
                            image: Option[String]
                  )
case class StaticItemCategory(
                       id: Option[String],
                       label: Option[String],
                       entries: Option[List[StaticItemEntry]]
                     )
case class StaticItems(
                        result: List[StaticItemCategory]
                      )

object StaticItems {
  implicit val staticItemsFormat: OFormat[StaticItems] = Json.format[StaticItems]
}

object StaticItemCategory {
  implicit val staticItemFormat: OFormat[StaticItemCategory] = Json.format[StaticItemCategory]
}

object StaticItemEntry {
  implicit val staticItemEntryFormat: OFormat[StaticItemEntry] = Json.format[StaticItemEntry]
}

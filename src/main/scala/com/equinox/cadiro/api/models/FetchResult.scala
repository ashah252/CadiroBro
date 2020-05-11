package com.equinox.cadiro.api.models

import play.api.libs.json.{JsValue, Json, OFormat}

/**
 * @deprecated
 */
case class Value (
                   `0`: String,
                   `1`: Int
                 )

/**
 * @deprecated
 */
case class HashValue (
                   `0`: String,
                   `1`: List[Int]
                 )

case class OnlineStatus (
                          league: String
                        )


case class Magnitudes (
                        hash: String,
                        min: Int,
                        max: Int
                      )

case class Account (
                     name: String,
                     lastCharacterName: String,
                     online: Option[OnlineStatus],
                     language: String
                   )


case class Hashes (
                    `implicit`: Option[List[JsValue]],
                    explicit: Option[List[JsValue]]
                  )


case class Implicit (
                      name: String,
                      tier: String,
                      magnitudes: Option[List[Magnitudes]]
                    )

case class Mods (
                  `implicit`: Option[List[Implicit]],
                  enchant: Option[List[Implicit]],
                  explicit: Option[List[Implicit]]
                )


case class Price (
                   `type`: String,
                   amount: Double,
                   currency: String
                 )

case class Extended (
                      mods: Option[Mods],
                      hashes: Option[Hashes],
                      text: String
                    )


case class Stash (
                   name: String,
                   x: Int,
                   y: Int
                 )



case class IncubatedItem (
                           name: String,
                           level: Int,
                           progress: Int,
                           total: Int
                         )

case class Listing (
                     method: String,
                     indexed: String,
                     stash: Option[Stash],
                     whisper: String,
                     account: Option[Account],
                     price: Option[Price]
                   )



case class Properties (
                        name: String,
                        values: Option[List[JsValue]],
                        displayMode: Int,
                        `type`: Int
                      )

case class Requirements (
                          name: String,
                          values: Option[List[JsValue]],
                          displayMode: Int
                        )

case class ItemSocket(
                     group: Int,
                     attr: String,
                     sColour: String,
                     )

case class Item (
                  verified: Boolean,
                  w: Int,
                  h: Int,
                  icon: String,
                  league: String,
                  sockets: Option[List[ItemSocket]],
                  name: String,
                  typeLine: String,
                  identified: Boolean,
                  ilvl: Int,
                  note: Option[String],
                  properties: Option[List[Properties]],
                  requirements: Option[List[Requirements]],
                  implicitMods: Option[List[String]],
                  explicitMods: Option[List[String]],
                  flavourText: Option[List[String]],
                  frameType: Int,
                  incubatedItem: Option[IncubatedItem],
                  extended: Option[Extended]
                )

case class FetchResult (
                    id: String,
                    listing: Option[Listing],
                    item: Option[Item]
                  )

case class FetchRootInterface (
                           result: List[FetchResult]
                         )



object FetchRootInterface {
  implicit val fetchResultRootFormat: OFormat[FetchRootInterface] = Json.format[FetchRootInterface]
}

object FetchResult {
  implicit val fetchResultFormat: OFormat[FetchResult] = Json.format[FetchResult]
}

object Item {
  implicit val itemFormat: OFormat[Item] = Json.format[Item]
}

object ItemSocket{
  implicit val itemSocketFormat: OFormat[ItemSocket] = Json.format[ItemSocket]
}

object Extended {
  implicit val extendedFormat: OFormat[Extended] = Json.format[Extended]
}

object Mods {
  implicit val modsFormat: OFormat[Mods] = Json.format[Mods]
}

object Magnitudes {
  implicit val magnitudesFormat: OFormat[Magnitudes] = Json.format[Magnitudes]
}

object Requirements {
  implicit val requirementsFormat: OFormat[Requirements] = Json.format[Requirements]
}

object Value {
  implicit val valuesFormat: OFormat[Value] = Json.format[Value]
}

object Listing {
  implicit val listingFormat: OFormat[Listing] = Json.format[Listing]
}

object Price {
  implicit val priceFormat: OFormat[Price] = Json.format[Price]
}

object Account {
  implicit val accountFormat: OFormat[Account] = Json.format[Account]
}

object OnlineStatus {
  implicit val onlineFormat: OFormat[OnlineStatus] = Json.format[OnlineStatus]
}

object Stash {
  implicit val stashFormat: OFormat[Stash] = Json.format[Stash]
}

object Implicit {
  implicit val implicitFormat: OFormat[Implicit] = Json.format[Implicit]
}

object IncubatedItem {
  implicit val incubatedItemFormat: OFormat[IncubatedItem] = Json.format[IncubatedItem]
}

object Hashes {
  implicit val hashesFormat: OFormat[Hashes] = Json.format[Hashes]
}

object HashValue {
  implicit val hashValueFormat: OFormat[HashValue] = Json.format[HashValue]
}

object Properties {
  implicit val propertyFormat: OFormat[Properties] = Json.format[Properties]
}


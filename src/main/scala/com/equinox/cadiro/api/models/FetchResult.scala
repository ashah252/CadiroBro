package com.equinox.cadiro.api.models

import play.api.libs.json.{Json, OFormat}

case class Value (
                   `0`: String,
                   `1`: Int
                 )

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
                    `implicit`: Option[List[HashValue]],
                    explicit: Option[List[HashValue]]
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
                   amount: Int,
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
                        values: Option[List[Value]],
                        displayMode: Int,
                        `type`: Int
                      )

case class Requirements (
                          name: String,
                          values: Option[List[Value]],
                          displayMode: Int
                        )

case class Item (
                  verified: Boolean,
                  w: Int,
                  h: Int,
                  icon: String,
                  league: String,
                  name: String,
                  typeLine: String,
                  identified: Boolean,
                  ilvl: Int,
                  note: String,
                  properties: Option[List[Properties]],
                  requirements: Option[List[Requirements]],
                  implicitMods: List[String],
                  explicitMods: List[String],
                  flavourText: List[String],
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
  implicit val implicitFormat: OFormat[IncubatedItem] = Json.format[IncubatedItem]
}

object Hashes {
  implicit val implicitFormat: OFormat[Hashes] = Json.format[Hashes]
}

object HashValue {
  implicit val implicitFormat: OFormat[HashValue] = Json.format[HashValue]
}

object Properties {
  implicit val implicitFormat: OFormat[Properties] = Json.format[Properties]
}
package com.equinox.cadiro.api.models

import play.api.libs.json.{Json, OFormat}

case class Stash(
                  name: Option[String],
                  x: Option[Double],
                  y: Option[Double]
                )
case class OnlineStatus(
                         league: Option[String]
                       )
case class Account(
                    name: Option[String],
                    lastCharacterName: Option[String],
                    online: Option[OnlineStatus],
                    language: Option[String]
                  )
case class Price(
                  `type`: Option[String],
                  amount: Option[Double],
                  currency: Option[String]
                )
case class Listing(
                    method: Option[String],
                    indexed: Option[String],
                    stash: Option[Stash],
                    whisper: Option[String],
                    account: Option[Account],
                    price: Option[Price]
                  )
case class Values(
                   `0`: Option[String],
                   `1`: Option[Double]
                 )
case class Requirements(
                         name: Option[String],
                         values: Option[List[Values]],
                         displayMode: Option[Double]
                       )
case class Magnitudes(
                       hash: Option[String],
                       min: Option[Double],
                       max: Option[Double]
                     )
case class ModEntry(
                     name: Option[String],
                     tier: Option[String],
                     magnitudes: Option[List[Magnitudes]]
                   )
case class Mods(
                 `implicit`: Option[List[ModEntry]],
                 explicit: Option[List[ModEntry]]
               )
case class Extended(
                     mods: Option[Mods],
                     hashes: Option[Mods],
                     text: Option[String]
                   )
case class Item(
                 verified: Option[Boolean],
                 w: Option[Double],
                 h: Option[Double],
                 icon: Option[String],
                 league: Option[String],
                 name: Option[String],
                 typeLine: Option[String],
                 identified: Option[Boolean],
                 ilvl: Option[Double],
                 note: Option[String],
                 corrupted: Option[Boolean],
                 requirements: Option[List[Requirements]],
                 implicitMods: Option[List[String]],
                 explicitMods: Option[List[String]],
                 flavourText: Option[List[String]],
                 frameType: Option[Double],
                 extended: Option[Extended]
               )
case class FetchResult(
                        id: Option[String],
                        listing: Option[Listing],
                        item: Option[Item]
                      )
case class FetchResultRoot(
                            result: List[FetchResult]
                          )

object FetchResultRoot {
  implicit val fetchResultRootFormat: OFormat[FetchResultRoot] = Json.format[FetchResultRoot]
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

object ModEntry {
  implicit val modEntryFormat: OFormat[ModEntry] = Json.format[ModEntry]
}

object Magnitudes {
  implicit val magnitudesFormat: OFormat[Magnitudes] = Json.format[Magnitudes]
}

object Requirements {
  implicit val requirementsFormat: OFormat[Requirements] = Json.format[Requirements]
}

object Values {
  implicit val valuesFormat: OFormat[Values] = Json.format[Values]
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
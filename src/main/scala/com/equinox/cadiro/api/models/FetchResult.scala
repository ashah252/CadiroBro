package com.equinox.cadiro.api.models

case class Stash(
                  name: String,
                  x: Double,
                  y: Double
                )
case class Online(
                   league: String
                 )
case class Account(
                    name: String,
                    lastCharacterName: String,
                    online: Online,
                    language: String
                  )
case class Price(
                  `type`: String,
                  amount: Double,
                  currency: String
                )
case class Listing(
                    method: String,
                    indexed: String,
                    stash: Option[Stash],
                    whisper: Option[String],
                    account: Account,
                    price: Option[Price]
                  )
case class Values(
                   `0`: String,
                   `1`: Int
)
case class Requirements(
                         name: String,
                         values: Option[List[Values]],
                         displayMode: Int
                       )
case class Magnitudes(
                       hash: String,
                       min: Int,
                       max: Int
                     )
case class ModEntry(
                     name: String,
                     tier: String,
                     magnitudes: Option[List[Magnitudes]]
                   )
case class Mods(
                 `implicit`: Option[List[ModEntry]],
                 explicit: Option[List[ModEntry]]
               )
case class Extended(
                     mods: Option[Mods],
                     hashes: Option[Mods],
                     text: String
                   )
case class Item(
                 verified: Boolean,
                 w: Double,
                 h: Double,
                 icon: String,
                 league: String,
                 name: String,
                 typeLine: String,
                 identified: Boolean,
                 ilvl: Double,
                 note: String,
                 corrupted: Boolean,
                 requirements: Option[List[Requirements]],
                 implicitMods: Option[List[String]],
                 explicitMods: Option[List[String]],
                 flavourText: Option[List[String]],
                 frameType: Int,
                 extended: Extended
               )
case class FetchResult(
                        id: String,
                        listing: Listing,
                        item: Item
                      )
case class FetchResultRoot(
                            result: List[FetchResult]
                          )
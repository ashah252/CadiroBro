package com.equinox.cadiro.api.models

case class StaticItem(
                       id: String,
                       label: String,
                       entries: List[StaticItemEntry]
                     )

case class StaticItemEntry(
                            id: String,
                            text: String,
                            imageUrl: Option[String]
                          )
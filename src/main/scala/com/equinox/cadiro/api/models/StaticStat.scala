package com.equinox.cadiro.api.models

case class StaticStat(
                       label: String,
                       entries: List[StaticStatEntry]
                     )

case class StaticStatEntry(
                            id: String,
                            text: String,
                            entryType: String
                          )

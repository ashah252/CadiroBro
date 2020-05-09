package com.equinox.cadiro.api.models

import play.api.libs.json.{Json, OFormat}

case class League(
                   id: String,
                   text: String,
                 )

case class Leagues(result: List[League])

object Leagues {
  implicit val leaguesJsonFormat: OFormat[Leagues] = Json.format[Leagues]
}

object League {
  implicit val leagueJsonFormat: OFormat[League] = Json.format[League]
}
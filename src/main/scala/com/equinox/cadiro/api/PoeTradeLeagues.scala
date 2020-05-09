package com.equinox.cadiro.api

import com.equinox.cadiro.api.models.{League, Leagues}
import com.equinox.cadiro.utils.HttpNetManager
import org.apache.http.client.methods.CloseableHttpResponse
import play.api.libs.json.Json

object PoeTradeLeagues {
  def apply(closeableHttpResponse: CloseableHttpResponse): PoeTradeLeagues = {
    new PoeTradeLeagues(
      Json.parse(
        HttpNetManager
          .getEntity(closeableHttpResponse)
          .getOrElse("")

      ).as[Leagues].result
    )
  }
}

class PoeTradeLeagues(leagues: List[League]) {

  def getLeague(league: String): Option[League] = {
    leagues.filter(_.id.equals(league)) match {
      case ::(head, next) => Some(head)
      case Nil => None
    }
  }
}

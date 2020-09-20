package com.equinox.cadiro.api.db

import com.equinox.cadiro.api.PoeTrade
import com.equinox.cadiro.api.models.{League, Leagues}
import com.equinox.cadiro.utils.{CadiroLogManager, HttpNetManager}
import play.api.libs.json.Json

object PoeTradeLeagues {
  def apply(responseEntity: String): PoeTradeLeagues = {
    CadiroLogManager.logger.info("Parsing Leagues")

    new PoeTradeLeagues(
      Json.parse(responseEntity).as[Leagues].result
    )
  }
}

class PoeTradeLeagues(val leagues: List[League]) extends PoeTrade {

  def getLeague(league: String): Option[League] = {
    leagues.filter(_.id.equals(league)) match {
      case ::(head, next) => Some(head)
      case Nil => None
    }
  }

  override def refresh: Option[PoeTradeLeagues] = CadiroDB.getAvailableLeagues
}

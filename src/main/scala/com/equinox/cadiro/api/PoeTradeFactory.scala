package com.equinox.cadiro.api

import com.equinox.cadiro.api.models.League
import com.equinox.cadiro.utils.{ApiHostConf, HttpNetManager}


object PoeTradeFactory {

  def setLeague(league: League): PoeTradeSearch = PoeTradeSearch()

  def getAvailableLeagues: Option[PoeTradeLeagues] = {
    HttpNetManager.sr(
      HttpNetManager.createGet(ApiHostConf.leaguesHost),
      httpUriResponse => {
        PoeTradeLeagues(httpUriResponse)
      })
  }

  def getStats: Option[PoeTradeStaticStats] = {
    HttpNetManager.sr(
      HttpNetManager.createGet(ApiHostConf.statsHost),
      httpUriResponse => {
        PoeTradeStaticStats(httpUriResponse)
      })
  }

  def getItems: Option[PoeTradeStaticItems] = {
    HttpNetManager.sr(
      HttpNetManager.createGet(ApiHostConf.itemsHost),
      httpUriResponse => {
        PoeTradeStaticItems(httpUriResponse)
      })
  }

}

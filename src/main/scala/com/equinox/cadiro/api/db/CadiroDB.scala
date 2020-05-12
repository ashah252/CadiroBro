package com.equinox.cadiro.api.db

import com.equinox.cadiro.utils.{ApiHostConf, HttpNetManager}

object CadiroDB {

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

  def getUniques: Option[PoeTradeUniqueItems] = {

    HttpNetManager.sr(
      HttpNetManager.createGet(ApiHostConf.uniquesHost),
      httpUriResponse => {
        PoeTradeUniqueItems(httpUriResponse)
      })
  }

}

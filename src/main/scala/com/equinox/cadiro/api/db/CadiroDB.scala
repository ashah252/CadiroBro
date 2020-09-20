package com.equinox.cadiro.api.db

import com.equinox.cadiro.utils.{ApiHostConf, HttpNetManager}

object CadiroDB {

  def getAvailableLeagues: Option[PoeTradeLeagues] = {

    HttpNetManager.sr(
      HttpNetManager.createGet(ApiHostConf.leaguesHost),
      responseEntity => {
        PoeTradeLeagues(responseEntity.getOrElse(""))
      })
  }

  def getStats: Option[PoeTradeStaticStats] = {

    HttpNetManager.sr(
      HttpNetManager.createGet(ApiHostConf.statsHost),
      responseEntity => {
        PoeTradeStaticStats(responseEntity.getOrElse(""))
      })
  }

  def getItems: Option[PoeTradeStaticItems] = {

    HttpNetManager.sr(
      HttpNetManager.createGet(ApiHostConf.itemsHost),
      responseEntity => {
        PoeTradeStaticItems(responseEntity.getOrElse(""))
      })
  }

  def getUniques: Option[PoeTradeUniqueItems] = {

    HttpNetManager.sr(
      HttpNetManager.createGet(ApiHostConf.uniquesHost),
      responseEntity => {
        PoeTradeUniqueItems(responseEntity.getOrElse(""))
      })
  }

}

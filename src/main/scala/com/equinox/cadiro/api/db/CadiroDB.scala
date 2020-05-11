package com.equinox.cadiro.api.db

import com.equinox.cadiro.utils.{ApiHostConf, CadiroLogManager, HttpNetManager}

object CadiroDB {

  def getAvailableLeagues: Option[PoeTradeLeagues] = {
    CadiroLogManager.logger.info("Sending Get Request: {}", ApiHostConf.leaguesHost)

    HttpNetManager.sr(
      HttpNetManager.createGet(ApiHostConf.leaguesHost),
      httpUriResponse => {
        PoeTradeLeagues(httpUriResponse)
      })
  }

  def getStats: Option[PoeTradeStaticStats] = {
    CadiroLogManager.logger.info("Sending Get Request: {}", ApiHostConf.statsHost)

    HttpNetManager.sr(
      HttpNetManager.createGet(ApiHostConf.statsHost),
      httpUriResponse => {
        PoeTradeStaticStats(httpUriResponse)
      })
  }

  def getItems: Option[PoeTradeStaticItems] = {
    CadiroLogManager.logger.info("Sending Get Request: {}", ApiHostConf.itemsHost)

    HttpNetManager.sr(
      HttpNetManager.createGet(ApiHostConf.itemsHost),
      httpUriResponse => {
        PoeTradeStaticItems(httpUriResponse)
      })
  }

  def getUniques: Option[PoeTradeUniqueItems] = {
    CadiroLogManager.logger.info("Sending Get Request: {}", ApiHostConf.uniquesHost)

    HttpNetManager.sr(
      HttpNetManager.createGet(ApiHostConf.uniquesHost),
      httpUriResponse => {
        PoeTradeUniqueItems(httpUriResponse)
      })
  }

}

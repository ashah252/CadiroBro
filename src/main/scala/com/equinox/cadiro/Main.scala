package com.equinox.cadiro

import com.equinox.cadiro.api.{Ascending, Cadiro, Online}
import com.equinox.cadiro.api.db.CadiroDB
import com.equinox.cadiro.utils.CadiroLogManager

object Main extends App {
  val leagues = CadiroDB.getAvailableLeagues.get
  CadiroLogManager.logger.debug("Leagues: {}", leagues.leagues)

  CadiroDB.getItems.get.items.foreach(CadiroLogManager.logger.debug("Static Item: {}", _))
  CadiroDB.getUniques.get.items.foreach(CadiroLogManager.logger.debug("Unique Item: {}", _))

  CadiroLogManager.logger.debug("Static Stats: {}", CadiroDB.getStats.get.stats)

  val cadiro = Cadiro
    .setLeague(leagues.getLeague("Hardcore Delirium").get)
    .setStatus(Online())
    .search("Farrul's Fur")
    .setOrder(Ascending())
    .execute

  cadiro.get.getNext
}

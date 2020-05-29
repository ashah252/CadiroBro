package com.equinox.cadiro

import com.equinox.cadiro.api.{Ascending, Cadiro, Online}
import com.equinox.cadiro.api.db.CadiroDB
import com.equinox.cadiro.utils.CadiroLogManager

object Main extends App {
  val leagues = CadiroDB.getAvailableLeagues.get
  CadiroLogManager.logger.debug("Leagues: {}", leagues.leagues)

//  CadiroDB.getItems.get.items.foreach(CadiroLogManager.logger.trace("Static Item: {}", _))
//  CadiroDB.getUniques.get.items.foreach(CadiroLogManager.logger.trace("Unique Item: {}", _))

//  CadiroLogManager.logger.trace("Static Stats: {}", CadiroDB.getStats.get.stats)

  val cadiro = Cadiro
    .setLeague(leagues.getLeague("Delirium").get)
    .setStatus(Online())
    .search("Headhunter")
    .setOrder(Ascending())
    .execute

  cadiro.get.getNext
}

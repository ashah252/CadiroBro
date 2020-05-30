package com.equinox.cadiro

import com.equinox.cadiro.api.ArmourFilterOption.Armour
import com.equinox.cadiro.api.{ArmourFilter, Ascending, Cadiro, CadiroFilter, Min, Online}
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
    .search("Farrul's Fur")
    .setOrder(Ascending())
    .addFilter(ArmourFilter(Armour, Some(Min(1)), None))
    .execute

  cadiro.get.getNext
}

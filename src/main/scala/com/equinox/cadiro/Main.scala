package com.equinox.cadiro

import com.equinox.cadiro.api.ArmourFilterOption.{Armour, Evasion}
import com.equinox.cadiro.api.SocketFilterOption.Links
import com.equinox.cadiro.api.{Any, ArmourFilter, Ascending, Cadiro, CadiroFilter, Descending, Min, Online, SocketFilter}
import com.equinox.cadiro.api.db.CadiroDB
import com.equinox.cadiro.api.models.SocketOption
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
    .search("Soul Taker")
    .setOrder(Ascending())
//    .addFilter(ArmourFilter(Armour, Some(Min(1)), None))
//    .addFilter(ArmourFilter(Evasion, Some(Min(1)), None))
//    .addFilter(SocketFilter(Links, SocketOption(None, None, None, None, Some(6), None)))
    .execute

  cadiro.get.getNext
}

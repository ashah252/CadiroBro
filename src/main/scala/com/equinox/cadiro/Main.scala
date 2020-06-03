package com.equinox.cadiro

import com.equinox.cadiro.api.Cadiro
import com.equinox.cadiro.api.filter.ArmourFilterOption.{Armour, Evasion}
import com.equinox.cadiro.api.filter.SocketFilterOption.Links
import com.equinox.cadiro.api.filter.{Any, Descending, Online, Range}
import com.equinox.cadiro.api.db.CadiroDB
import com.equinox.cadiro.api.filter.{Ascending, Online, SocketFilter}
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
    .search("Farrul's Fur")
    .setOrder(Ascending())
    .addFilter(SocketFilter(Links, Some(Range.min(6))).withWhiteColors(3))
    .execute

  cadiro.get.getNext
}

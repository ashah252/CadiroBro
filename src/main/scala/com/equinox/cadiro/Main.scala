package com.equinox.cadiro

import com.equinox.cadiro.api.Cadiro
import com.equinox.cadiro.api.filter.ArmourFilterOption.{Armour, Evasion}
import com.equinox.cadiro.api.filter.SocketFilterOption.Links
import com.equinox.cadiro.api.filter.{Any, Ascending, Descending, Online, Range, RarityFilter, SocketFilter, TypeFilter}
import com.equinox.cadiro.api.db.CadiroDB
import com.equinox.cadiro.api.filter.RarityFilterOption.{Normal, Rare, Unique}
import com.equinox.cadiro.api.filter.TypeFilterOption.Bow
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
//    .searchItem("Farrul's Fur")
    .setPriceOrder(Ascending())
    .addFilter(SocketFilter(Links, Some(Range.min(6))))
    .addFilter(RarityFilter(Normal))
    .addFilter(TypeFilter(Bow))
    .execute

  cadiro.get.getNext
}

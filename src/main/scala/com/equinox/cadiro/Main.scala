package com.equinox.cadiro

import com.equinox.cadiro.api.{Ascending, Cadiro, Online}
import com.equinox.cadiro.api.db.CadiroDB
import com.equinox.cadiro.utils.CadiroLogManager

object Main extends App {
  println(CadiroDB.getAvailableLeagues.get)
//
  CadiroDB.getItems.get.items.foreach(CadiroLogManager.logger.debug("Static Item: {}", _))
  CadiroDB.getUniques.get.items.foreach(CadiroLogManager.logger.debug("Unique Item: {}", _))
//
  println(CadiroDB.getStats)

  val cadiro = Cadiro
    .setLeague("Delirium")
    .setStatus(Online())
    .search("Farrul's Fur")
    .setOrder(Ascending())
    .execute

  cadiro.get.getNext
}

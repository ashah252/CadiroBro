package com.equinox.cadiro

import com.equinox.cadiro.api.{Ascending, Cadiro, Online}
import com.equinox.cadiro.api.db.CadiroDB

object Main extends App {
//  println(CadiroDB.getAvailableLeagues.get.getLeague("Delirium"))
//
//  println(CadiroDB.getItems)
//
//  println(CadiroDB.getStats)

  val cadiro = Cadiro
    .setLeague("Delirium")
    .setStatus(Online())
    .search("Tabula Rasa")
    .setOrder(Ascending())
    .execute

  cadiro.get.getNext
}

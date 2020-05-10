package com.equinox.cadiro

import com.equinox.cadiro.api.Cadiro
import com.equinox.cadiro.api.db.CadiroDB
import com.equinox.cadiro.api.models.{Ascending, Online}

object Main extends App {
//  println(CadiroDB.getAvailableLeagues.get.getLeague("Delirium"))
//
//  println(CadiroDB.getItems)
//
//  println(CadiroDB.getStats)

  val cadiro = Cadiro
    .setLeague("Delirium")
    .setStatus(Online())
    .search("Headhunter")
    .setOrder(Ascending())
    .setType("Leather Belt")
    .execute

  println(cadiro.get.searchResult)
}

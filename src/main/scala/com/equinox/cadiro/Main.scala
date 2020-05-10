package com.equinox.cadiro

import com.equinox.cadiro.api.db.CadiroDB

object Main extends App {
  println(CadiroDB.getAvailableLeagues.get.getLeague("Delirium"))

  println(CadiroDB.getItems)

  println(CadiroDB.getStats)
}

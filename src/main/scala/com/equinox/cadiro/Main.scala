package com.equinox.cadiro

import com.equinox.cadiro.api.PoeTradeFactory

object Main extends App {
  println(PoeTradeFactory.getAvailableLeagues.get.getLeague("Delirium"))
}

package com.equinox.cadiro

import com.equinox.cadiro.api.{PoeTradeFactory, PoeTradeStaticItems}

object Main extends App {
  println(PoeTradeFactory.getAvailableLeagues.get.getLeague("Delirium"))

  println(PoeTradeFactory.getItems)

  println(PoeTradeFactory.getStats)
}

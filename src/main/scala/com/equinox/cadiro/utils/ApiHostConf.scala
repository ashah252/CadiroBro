package com.equinox.cadiro.utils

import com.typesafe.config.ConfigFactory

object ApiHostConf {
  private val config = ConfigFactory.load("application.conf").getConfig("poe.api")

  val leaguesHost: String = config.getString("leaguesHost")
  val itemsHost: String = config.getString("itemsHost")
  val statsHost: String = config.getString("statsHost")
  val searchHost: String = config.getString("searchHost")

}

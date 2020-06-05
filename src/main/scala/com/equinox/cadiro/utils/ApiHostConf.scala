package com.equinox.cadiro.utils

import com.typesafe.config.ConfigFactory

object ApiHostConf {

  private val cadiroConfig = ConfigFactory.load()
  cadiroConfig.checkValid(ConfigFactory.defaultReference())

  private val apiConfig = cadiroConfig.getConfig("poe.api")
  private val poeConfig = cadiroConfig.getConfig("poe")

  val leaguesHost: String = apiConfig.getString("leaguesHost")
  val itemsHost: String = apiConfig.getString("itemsHost")
  val statsHost: String = apiConfig.getString("statsHost")
  val searchHost: String = apiConfig.getString("searchHost")
  val fetchHost: String = apiConfig.getString("fetchHost")
  val uniquesHost: String = apiConfig.getString("uniquesHost")

  val pageLimit: Int = poeConfig.getInt("pageLimit")
}

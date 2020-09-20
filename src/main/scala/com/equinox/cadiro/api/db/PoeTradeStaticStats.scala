package com.equinox.cadiro.api.db

import com.equinox.cadiro.api.models.{StaticStatCategory, StaticStats}
import com.equinox.cadiro.utils.{ApiHostConf, CadiroLogManager, HttpNetManager}
import play.api.libs.json.Json


object PoeTradeStaticStats {
  def apply(responseEntity: String): PoeTradeStaticStats = {
    CadiroLogManager.logger.info("Parsing Static Stats")

    new PoeTradeStaticStats(
      Json.parse(responseEntity).as[StaticStats].result
    )
  }
}

class PoeTradeStaticStats(val stats: List[StaticStatCategory])
package com.equinox.cadiro.api.db

import com.equinox.cadiro.api.models.{StaticStatCategory, StaticStats}
import com.equinox.cadiro.utils.{ApiHostConf, CadiroLogManager, HttpNetManager}
import org.apache.http.client.methods.CloseableHttpResponse
import play.api.libs.json.Json


object PoeTradeStaticStats {
  def apply(closeableHttpResponse: CloseableHttpResponse): PoeTradeStaticStats = {
    CadiroLogManager.logger.info("Parsing Static Stats")

    new PoeTradeStaticStats(
      Json.parse(
        HttpNetManager
          .getEntity(closeableHttpResponse)
          .getOrElse("")

      ).as[StaticStats].result
    )
  }
}

class PoeTradeStaticStats(val stats: List[StaticStatCategory])
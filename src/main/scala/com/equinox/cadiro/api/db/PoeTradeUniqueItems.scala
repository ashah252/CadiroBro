package com.equinox.cadiro.api.db

import com.equinox.cadiro.api.models.{StaticItemCategory, StaticItems}
import com.equinox.cadiro.utils.{CadiroLogManager, HttpNetManager}
import org.apache.http.client.methods.CloseableHttpResponse
import play.api.libs.json.Json

object PoeTradeUniqueItems {
  def apply(closeableHttpResponse: CloseableHttpResponse): PoeTradeUniqueItems = {
    CadiroLogManager.logger.info("Parsing Unique Items")

    new PoeTradeUniqueItems(
      Json.parse(
        HttpNetManager
          .getEntity(closeableHttpResponse)
          .getOrElse("")

      ).as[StaticItems].result
    )
  }
}

class PoeTradeUniqueItems(val items: List[StaticItemCategory])

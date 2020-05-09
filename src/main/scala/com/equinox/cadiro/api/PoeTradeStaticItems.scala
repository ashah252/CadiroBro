package com.equinox.cadiro.api

import com.equinox.cadiro.api.models.{Leagues, StaticItemCategory, StaticItems}
import com.equinox.cadiro.utils.HttpNetManager
import org.apache.http.client.methods.CloseableHttpResponse
import play.api.libs.json.Json

object PoeTradeStaticItems {
  def apply(closeableHttpResponse: CloseableHttpResponse): PoeTradeStaticItems = {

    new PoeTradeStaticItems(
      Json.parse(
        HttpNetManager
          .getEntity(closeableHttpResponse)
          .getOrElse("")

      ).as[StaticItems].result
    )
  }
}

case class PoeTradeStaticItems(items: List[StaticItemCategory])

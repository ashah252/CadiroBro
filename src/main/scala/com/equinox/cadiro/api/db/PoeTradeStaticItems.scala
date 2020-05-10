package com.equinox.cadiro.api.db

import com.equinox.cadiro.api.PoeTrade
import com.equinox.cadiro.api.models.{StaticItemCategory, StaticItems}
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

class PoeTradeStaticItems(items: List[StaticItemCategory]) extends PoeTrade {
  override def refresh: Option[PoeTradeStaticItems] = CadiroDB.getItems
}

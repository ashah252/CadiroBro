package com.equinox.cadiro.api.db

import com.equinox.cadiro.api.models.{StaticItemCategory, StaticItems}
import com.equinox.cadiro.utils.{CadiroLogManager, HttpNetManager}
import play.api.libs.json.Json

object PoeTradeUniqueItems {
  def apply(responseEntity: String): PoeTradeUniqueItems = {
    CadiroLogManager.logger.info("Parsing Unique Items")

    new PoeTradeUniqueItems(
      Json.parse(responseEntity).as[StaticItems].result
    )
  }
}

class PoeTradeUniqueItems(val items: List[StaticItemCategory])

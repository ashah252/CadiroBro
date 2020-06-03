package com.equinox.cadiro.api.filter
import com.equinox.cadiro.api.models.FilterList

sealed trait RarityFilterOption extends CadiroFilterOption
object RarityFilterOption {
  case object Normal extends RarityFilterOption
  case object Magic extends RarityFilterOption
  case object Rare extends RarityFilterOption
  case object Unique extends RarityFilterOption
  case object RelicUnique extends RarityFilterOption
  case object NonUnique extends RarityFilterOption
}

class RarityFilter(override val `type`: RarityFilterOption) extends CadiroFilter(`type`) {
  override def integrate(filterList: FilterList): FilterList = filterList
}

package com.equinox.cadiro.api.filter
import com.equinox.cadiro.api.models.{FilterList, SimpleOption, TypeFilterType, TypeFilters}

sealed trait RarityFilterOption extends CadiroFilterOption
object RarityFilterOption {

  def getRarityKeyword(`type`: RarityFilterOption): String = {
    `type` match {
      case Normal => "normal"
      case Magic => "magic"
      case Rare => "rare"
      case Unique => "unique"
      case RelicUnique => "uniquefoil"
      case NonUnique => "nonunique"
    }
  }

  case object Normal extends RarityFilterOption
  case object Magic extends RarityFilterOption
  case object Rare extends RarityFilterOption
  case object Unique extends RarityFilterOption
  case object RelicUnique extends RarityFilterOption
  case object NonUnique extends RarityFilterOption
}

case class RarityFilter(override val `type`: RarityFilterOption) extends CadiroFilter(`type`) {

  private def toSimpleOption(option: String): SimpleOption = SimpleOption(option)

  override def integrate(filterList: FilterList): FilterList = {
    filterList.type_filters match {
      case Some(typeFilters) =>
        filterList.copy(type_filters = Some(typeFilters.copy(filters = typeFilters.filters.copy(rarity = Some(toSimpleOption(RarityFilterOption.getRarityKeyword(`type`)))))))

      case None => integrate(filterList.copy(type_filters = Some(TypeFilters(TypeFilterType(None, None)))))
    }
  }
}

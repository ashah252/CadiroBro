package com.equinox.cadiro.api.models

trait Sorting {
  def toSortingOption: Option[SortingOption]
}

case class Ascending() extends Sorting {
  override def toSortingOption: Option[SortingOption] = Some(SortingOption("asc"))
}

case class Descending() extends Sorting {
  override def toSortingOption: Option[SortingOption] = Some(SortingOption("desc"))
}

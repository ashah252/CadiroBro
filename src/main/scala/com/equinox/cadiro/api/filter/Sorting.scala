package com.equinox.cadiro.api.filter

import com.equinox.cadiro.api.models.SortingOption

trait Sorting {
  def toSortingOption: Option[SortingOption]
}

case class Ascending() extends Sorting {
  override def toSortingOption: Option[SortingOption] = Some(SortingOption("asc"))
}

case class Descending() extends Sorting {
  override def toSortingOption: Option[SortingOption] = Some(SortingOption("desc"))
}

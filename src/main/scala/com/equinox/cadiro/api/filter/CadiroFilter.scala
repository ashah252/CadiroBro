package com.equinox.cadiro.api.filter

import com.equinox.cadiro.api.models._


case class Min(value: Int)
case class Max(value: Int)

trait CadiroFilterOption
abstract class CadiroFilter(val `type`: CadiroFilterOption) {
	def integrate(filterList: FilterList): FilterList
	def getType: String = `type`.toString

}

object CadiroFilter {
	def emptyFilter: FilterList = FilterList(None, None, None, None)
}








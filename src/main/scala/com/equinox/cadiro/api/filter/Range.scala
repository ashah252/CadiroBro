package com.equinox.cadiro.api.filter

case class Min(value: Int)
case class Max(value: Int)

abstract class Range()

object Range {
	def min(value: Int): MinRange = {
		MinRange(Min(value))
	}

	def max(value: Int): MaxRange = {
		MaxRange(Max(value))
	}

	def minAndMax(left: Int, right: Int): MinMaxRange = {
		MinMaxRange(Min(left), Max(right))
	}

	case class MinRange(min: Min) extends Range
	case class MaxRange(max: Max) extends Range
	case class MinMaxRange(min: Min, max: Max) extends Range

}
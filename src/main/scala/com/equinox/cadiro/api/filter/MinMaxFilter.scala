package com.equinox.cadiro.api.filter

import com.equinox.cadiro.api.models.MinMaxOption

abstract class MinMaxFilter(override val `type`: CadiroFilterOption, val range: Option[Range]) extends CadiroFilter(`type`){

	def toMinMaxOptionModel: MinMaxOption = {
		range match {
			case Some(Range.MinMaxRange(min, max)) => MinMaxOption(Some(min.value), Some(max.value))
			case Some(Range.MaxRange(max)) => MinMaxOption(None, Some(max.value))
			case Some(Range.MinRange(min)) => MinMaxOption(Some(min.value), None)
			case _ => MinMaxOption(None, None)
		}
	}

}

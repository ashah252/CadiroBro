package com.equinox.cadiro.api.filter

import com.equinox.cadiro.api.models.{FilterList, StaticStatEntry, StatGroup}

// cannot extend CadiroFilterOption because stats are not under filter category for search query
// for now only one type of statGroup
case class StatFilter( val `type`: StaticStatEntry,  val range: Option[Range]) {
    
}

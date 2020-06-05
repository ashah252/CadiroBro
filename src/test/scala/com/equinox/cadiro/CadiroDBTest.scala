package com.equinox.cadiro

import com.equinox.cadiro.api.Cadiro
import com.equinox.cadiro.api.db.CadiroDB
import com.equinox.cadiro.api.filter.RarityFilterOption.Normal
import com.equinox.cadiro.api.filter.SocketFilterOption.Links
import com.equinox.cadiro.api.filter.TypeFilterOption.Bow
import com.equinox.cadiro.api.filter.{Ascending, Online, Range, RarityFilter, SocketFilter, TypeFilter}
import com.equinox.cadiro.utils.CadiroLogManager

import org.junit.Test
import org.junit.Assert._

class CadiroDBTest {

  @Test
  def testLeagueConnection(): Unit = {
    val cadiroDB = CadiroDB.getAvailableLeagues

    assertTrue(cadiroDB.isDefined)

    val leagues = cadiroDB.get

    val standardLeague = leagues.getLeague("Standard")

    assertTrue(standardLeague.isDefined)

    val hardcoreStdLeague = leagues.getLeague("Hardcore")

    assertTrue(hardcoreStdLeague.isDefined)

    val pastLeague = leagues.getLeague("Legion")

    assertTrue(pastLeague.isEmpty)

  }

}

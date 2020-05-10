package com.equinox.cadiro.api

import com.equinox.cadiro.api.models.League

object Cadiro {
  def builder = new CadiroBuilder
}

class CadiroBuilder {
  def setLeague(league: League): Cadiro = {
    new Cadiro
  }
}

class Cadiro {

}

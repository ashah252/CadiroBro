package com.equinox.cadiro.api

abstract class PoeTrade {
  def refresh: Option[PoeTrade]
}

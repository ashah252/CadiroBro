package com.equinox.cadiro.api.models

trait Status {
  def toStatusOption: StatusOption
}

case class Online() extends Status {
  override def toStatusOption: StatusOption = StatusOption("online")
}

case class Any() extends Status {
  override def toStatusOption: StatusOption = StatusOption("any")
}

package com.equinox.cadiro.api.filter
import com.equinox.cadiro.api.models.FilterList

sealed trait TypeFilterOption extends CadiroFilterOption
object TypeFilterOption {

  sealed trait Weapon extends TypeFilterOption
  sealed trait OneHandedMelee extends Weapon
  sealed trait TwoHandedMelee extends Weapon
  sealed trait Armour extends TypeFilterOption
  sealed trait Accessory extends TypeFilterOption
  sealed trait Gem extends TypeFilterOption
  sealed trait Jewel extends TypeFilterOption
  sealed trait Miscellaneous extends TypeFilterOption
  sealed trait Monster extends Miscellaneous
  sealed trait Currency extends Miscellaneous

  case object AnyWeapon extends Weapon
  case object OneHanded extends Weapon
  case object AnyOneHandedMelee extends OneHandedMelee
  case object AnyTwoHandedMelee extends TwoHandedMelee
  case object Bow extends Weapon
  case object Claw extends Weapon
  case object AnyDagger extends Weapon
  case object RuneDagger extends Weapon
  case object OneHandedAxe extends OneHandedMelee
  case object OneHandedMace extends OneHandedMelee
  case object OneHandedSword extends OneHandedMelee
  case object Sceptre extends Weapon
  case object AnyStaff extends Weapon
  case object WarStaff extends Weapon
  case object TwoHandedAxe extends TwoHandedMelee
  case object TwoHandedMace extends TwoHandedMelee
  case object TwoHandedSword extends TwoHandedMelee
  case object Wand extends Weapon

  case object FishingRod extends TypeFilterOption

  case object AnyArmour extends Armour
  case object BodyArmour extends Armour
  case object Boots extends Armour
  case object Gloves extends Armour
  case object Helmet extends Armour
  case object Shield extends Armour
  case object Quiver extends Armour

  case object AnyAccessory extends Accessory
  case object Amulet extends Accessory
  case object Belt extends Accessory
  case object Ring extends Accessory

  case object AnyGem extends Gem
  case object SkillGem extends Gem
  case object SupportGem extends Gem
  case object AwakenedSupportGem extends Gem

  case object AnyJewel extends Jewel
  case object BaseJewel extends Jewel
  case object AbyssJewel extends Jewel
  case object ClusterJewel extends Jewel


  case object Flask extends Miscellaneous
  case object Map extends Miscellaneous
  case object MapFragment extends Miscellaneous
  case object Scarab extends Miscellaneous
  case object WatchStone extends Miscellaneous
  case object LeagueStone extends Miscellaneous
  case object Prophecy extends Miscellaneous
  case object DivinationCard extends Miscellaneous

  case object CapturedBeast extends Monster
  case object MetamorphSample extends Monster

  case object AnyCurrency extends Currency
  case object UniqueFragment extends Currency
  case object Resonator extends Currency
  case object Fossil extends Currency
  case object Incubator extends Currency



}
case class TypeFilter(override val `type`: TypeFilterOption) extends CadiroFilter(`type`) {
  override def integrate(filterList: FilterList): FilterList = filterList
}

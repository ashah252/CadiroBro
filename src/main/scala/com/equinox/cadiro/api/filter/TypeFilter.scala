package com.equinox.cadiro.api.filter
import com.equinox.cadiro.api.models.{FilterList, SimpleOption, TypeFilterType, TypeFilters}

sealed trait TypeFilterOption extends CadiroFilterOption
object TypeFilterOption {

  def getTypeKeyword(`type`: TypeFilterOption): String = {
    `type` match {
      case weapon: Weapon => weapon match {
        case oneHandMelee: OneHandedMelee => oneHandMelee match {
          case AnyOneHandedMelee => "weapon.onemelee"
          case OneHandedAxe => "weapon.oneaxe"
          case OneHandedMace => "weapon.onemace"
          case OneHandedSword => "weapon.onesword"
        }
        case twoHandMelee: TwoHandedMelee => twoHandMelee match {
          case AnyTwoHandedMelee => "weapon.twomelee"
          case TwoHandedAxe => "weapon.twoaxe"
          case TwoHandedMace => "weapon.twomace"
          case TwoHandedSword => "weapon.twosword"
        }
        case AnyWeapon => "weapon"
        case OneHanded => "weapon.one"
        case Bow => "weapon.bow"
        case Claw => "weapon.claw"
        case AnyDagger => "weapon.dagger"
        case RuneDagger => "weapon.runedagger"
        case Sceptre => "weapon.sceptre"
        case AnyStaff => "weapon.staff"
        case WarStaff => "weapon.warstaff"
        case Wand => "weapon.wand"
        case FishingRod => "weapon.rod"
      }
      case armour: Armour => armour match {
        case AnyArmour => "armour"
        case BodyArmour => "armour.chest"
        case Boots => "armour.boots"
        case Gloves => "armour.gloves"
        case Helmet => "armour.helmet"
        case Shield => "armour.shield"
        case Quiver => "armour.quiver"
      }
      case accessory: Accessory => accessory match {
        case AnyAccessory => "accessory"
        case Amulet => "accessory.amulet"
        case Belt => "accessory.belt"
        case Ring => "accessory.ring"
      }
      case gem: Gem => gem match {
        case AnyGem => "gem"
        case SkillGem => "gem.activegem"
        case SupportGem => "gem.supportgem"
        case AwakenedSupportGem => "gem.supportgemplus"
      }
      case jewel: Jewel => jewel match {
        case AnyJewel => "jewel"
        case BaseJewel => "jewel.base"
        case AbyssJewel => "jewel.abyss"
        case ClusterJewel => "jewel.cluster"
      }
      case miscellaneous: Miscellaneous => miscellaneous match {
        case monster: Monster => monster match {
          case CapturedBeast => "monster.beast"
          case MetamorphSample => "monster.sample"
        }
        case currency: Currency => currency match {
          case AnyCurrency => "currency"
          case UniqueFragment => "currency.piece"
          case Resonator => "currency.resonator"
          case Fossil => "currency.fossil"
          case Incubator => "currency.incubator"
        }
        case map: Map => map match {
          case AnyMap => "map"
          case MapFragment => "map.fragment"
          case Scarab => "map.scarab"
        }
        case Flask => "flask"
        case WatchStone => "watchstone"
        case LeagueStone => "leaguestone"
        case Prophecy => "prophecy"
        case DivinationCard => "card"
      }
    }
  }

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
  sealed trait Map extends Miscellaneous

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
  case object FishingRod extends Weapon

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
  case object AnyMap extends Map
  case object MapFragment extends Map
  case object Scarab extends Map
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

  def toSimpleOption(option: String): SimpleOption = SimpleOption(option)

  override def integrate(filterList: FilterList): FilterList = {
    filterList.type_filters match {
      case Some(typeFilters) =>
        filterList.copy(type_filters = Some(typeFilters.copy(filters = typeFilters.filters.copy(category = Some(toSimpleOption(TypeFilterOption.getTypeKeyword(`type`)))))))

      case None => integrate(filterList.copy(type_filters = Some(TypeFilters(TypeFilterType(None, None)))))
    }
  }
}

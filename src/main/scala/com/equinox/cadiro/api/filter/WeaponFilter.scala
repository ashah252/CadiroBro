package com.equinox.cadiro.api.filter

import com.equinox.cadiro.api.models.{FilterList, WeaponFilterType, WeaponFilters}

sealed trait WeaponFilterOption extends CadiroFilterOption
object WeaponFilterOption {
	case object TotalDamage extends WeaponFilterOption
	case object PhysicalDamagePerSecond extends WeaponFilterOption
	case object CriticalChance extends WeaponFilterOption
	case object AttacksPerSecond extends WeaponFilterOption
	case object DamagePerSecond extends WeaponFilterOption
	case object ElementalDamagePerSecond extends WeaponFilterOption
}

case class WeaponFilter(override val `type`: WeaponFilterOption, override val range: Option[Range]) extends MinMaxFilter(`type`, range){

	override def integrate(filterList: FilterList): FilterList = {
		filterList.weapon_filters match {
			case Some(weaponFilters) =>
				`type` match {

					case WeaponFilterOption.TotalDamage => filterList.copy(weapon_filters = Some(weaponFilters.copy(filters = weaponFilters.filters.copy(damage = Some(toMinMaxOptionModel)))))

					case WeaponFilterOption.PhysicalDamagePerSecond => filterList.copy(weapon_filters = Some(weaponFilters.copy(filters = weaponFilters.filters.copy(pdps = Some(toMinMaxOptionModel)))))

					case WeaponFilterOption.CriticalChance => filterList.copy(weapon_filters = Some(weaponFilters.copy(filters = weaponFilters.filters.copy(crit = Some(toMinMaxOptionModel)))))

					case WeaponFilterOption.AttacksPerSecond => filterList.copy(weapon_filters = Some(weaponFilters.copy(filters = weaponFilters.filters.copy(aps = Some(toMinMaxOptionModel)))))

					case WeaponFilterOption.DamagePerSecond => filterList.copy(weapon_filters = Some(weaponFilters.copy(filters = weaponFilters.filters.copy(dps = Some(toMinMaxOptionModel)))))

					case WeaponFilterOption.ElementalDamagePerSecond => filterList.copy(weapon_filters = Some(weaponFilters.copy(filters = weaponFilters.filters.copy(edps = Some(toMinMaxOptionModel)))))
				}

			case None => integrate(filterList.copy(weapon_filters = Some(WeaponFilters(WeaponFilterType(None, None, None, None, None, None)))))
		}
	}
}

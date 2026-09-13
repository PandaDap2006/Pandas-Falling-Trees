package dev.pandasystems.fallingtrees.config

import kotlinx.serialization.Serializable

@Serializable
data class CommonConfig(
	var disableCrouchMining: Boolean = false,
	var disableExtraToolDamage: Boolean = false,
	var disableExtraFoodExhaustion: Boolean = false
)
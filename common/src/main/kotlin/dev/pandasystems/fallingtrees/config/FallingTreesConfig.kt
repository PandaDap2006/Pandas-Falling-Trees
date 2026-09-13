package dev.pandasystems.fallingtrees.config

import dev.pandasystems.pandalib.config.ConfigManager
import dev.pandasystems.pandalib.config.handle.ConfigHandle
import dev.pandasystems.pandalib.config.store.InMemoryConfigStore

lateinit var fallingTreesCommonConfig: ConfigHandle<CommonConfig>
	private set

internal fun initConfigs() {
	fallingTreesCommonConfig = ConfigManager.load(
		InMemoryConfigStore(),
		{ CommonConfig() }
	)
}
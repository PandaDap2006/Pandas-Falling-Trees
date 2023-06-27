package me.pandadev.fallingtrees;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;

@Config(name = FallingTrees.MOD_ID)
public class TreesConfig implements ConfigData {
	@ConfigEntry.Gui.Tooltip()
	public String[] valid_logs = new String[] {
			"minecraft:oak_log",
			"minecraft:spruce_log",
			"minecraft:acacia_log",
			"minecraft:birch_log",
			"minecraft:cherry_log",
			"minecraft:dark_oak_log",
			"minecraft:jungle_log",
			"minecraft:mangrove_log"
	};

	public static boolean isValidLog(Block block) {
		return Arrays.stream(FallingTrees.configHolder.getConfig().valid_logs)
				.anyMatch(s -> s.equals(BuiltInRegistries.BLOCK.getKey(block).toString()));
	}
}
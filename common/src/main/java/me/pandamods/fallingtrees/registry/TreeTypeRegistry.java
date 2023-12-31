package me.pandamods.fallingtrees.registry;

import me.pandamods.fallingtrees.FallingTrees;
import me.pandamods.fallingtrees.api.TreeRegistry;
import me.pandamods.fallingtrees.api.TreeType;
import me.pandamods.fallingtrees.trees.*;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class TreeTypeRegistry {

	public static final Supplier<TreeType> DEFAULT = TreeRegistry.register(new ResourceLocation(FallingTrees.MOD_ID, "default"), DefaultTree::new);
	public static final Supplier<TreeType> CACTUS = TreeRegistry.register(new ResourceLocation(FallingTrees.MOD_ID, "cactus"), CactusTree::new);
	public static final Supplier<TreeType> BAMBOO = TreeRegistry.register(new ResourceLocation(FallingTrees.MOD_ID, "bamboo"), BambooTree::new);
	public static final Supplier<TreeType> CHORUS = TreeRegistry.register(new ResourceLocation(FallingTrees.MOD_ID, "chorus"), ChorusTree::new);
	
	public static void register() {
	}
}

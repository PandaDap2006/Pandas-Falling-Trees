package me.pandamods.fallingtrees.forge;

import me.pandamods.fallingtrees.FallingTrees;
import me.pandamods.fallingtrees.config.FallingTreesConfig;
import me.pandamods.fallingtrees.config.screen.ConfigScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = FallingTrees.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FallingTreesClientForge {
	@SubscribeEvent
	public static void clientInit(FMLClientSetupEvent event) {
		ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () ->
				new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> new ConfigScreen(screen)));
	}
}

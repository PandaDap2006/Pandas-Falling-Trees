package me.pandamods.fallingtrees.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "client")
public class ClientConfig implements ConfigData {
	public boolean invertCrouchMining = false;

	@ConfigEntry.Gui.CollapsibleObject
	public SoundSettings soundSettings = new SoundSettings();

	public static class SoundSettings {
		public boolean enabled = true;
		public float startVolume = 0.7f;
		public float endVolume = 0.7f;
	}
}

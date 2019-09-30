package com.github.sarhatabaot.boatcore.api.config;

import com.github.sarhatabaot.boatcore.api.common.CustomColors;
import com.github.sarhatabaot.boatcore.api.plugin.CorePlugin;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sarhatabaot
 */
public abstract class SimpleConfig {
	protected static FileConfiguration config = CorePlugin.getInstance().getConfig();

	public String PREFIX = config.getString(Constants.CONFIG_PREFIX);
	public String VERSION = config.getString(Constants.CONFIG_VERSION);
	public boolean DEBUG = config.getBoolean(Constants.CONFIG_DEBUG);

	public static class Constants {
		public static String CONFIG_PREFIX = "prefix";
		public static String CONFIG_VERSION = "version";
		public static String CONFIG_DEBUG = "debug";
	}

	protected static List<Color> getColorList(String path) {
		List<String> stringColors = config.getStringList(path);
		List<Color> colors = new ArrayList<>();
		for(String color: stringColors) {
			colors.add(CustomColors.matchColor(color));
		}
		return colors;
	}


}

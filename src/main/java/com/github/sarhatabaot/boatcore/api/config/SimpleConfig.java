package com.github.sarhatabaot.boatcore.api.config;

import com.github.sarhatabaot.boatcore.api.common.CustomColors;
import com.github.sarhatabaot.boatcore.api.plugin.CorePlugin;
import jdk.internal.joptsimple.internal.Strings;
import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sarhatabaot
 * Default config for any plugin. "config.yml"
 * Extend this to get easy access to pre-made methods and accessors.
 */
public abstract class SimpleConfig {
	protected static FileConfiguration config = CorePlugin.getInstance().getConfig();

	public String PREFIX = config.getString(Constants.CONFIG_PREFIX);
	public String VERSION = config.getString(Constants.CONFIG_VERSION);
	public boolean DEBUG = config.getBoolean(Constants.CONFIG_DEBUG);

	@Getter
	private String[] header;

	public void setHeader(String... header) {
		this.header = header;
	}

	public void save() {
		saveHeader();
	}

	private void saveHeader() {
		config.options().copyHeader(true);
		if (header != null) {
			config.options().header(String.join("\n",header));
		}
		config.options().header(String.join("\n", Constants.DEFAULT_HEADER));
		CorePlugin.getInstance().saveConfig();
	}

	public static class Constants {
		public static String CONFIG_PREFIX = "prefix";
		public static String CONFIG_VERSION = "version";
		public static String CONFIG_DEBUG = "debug";
		public static String[] DEFAULT_HEADER = {CorePlugin.NAME + "Main Config File", "Visit "+CorePlugin.WEBSITE+" for more information.","Version: "+CorePlugin.VERSION};
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

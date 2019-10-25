package com.github.sarhatabaot.boatcore.api.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * @author sarhatabaot
 */
public abstract class CustomConfig  {
	@Setter
	@Getter(AccessLevel.PROTECTED)
	private String fileName;
	private File configFile;
	private JavaPlugin plugin;
	private FileConfiguration config;

	private String[] header;

	public void setHeader(String... header) {
		this.header = header;
	}

	/**
	 *
	 * @param fileName  file name including the type.
	 * @param plugin    plugin instance.
	 */
	public CustomConfig(final String fileName, final JavaPlugin plugin) {
		this.fileName = fileName;
		this.plugin = plugin;
	}

	/**
	 * Reloads the file
	 */
	protected void reloadConfig(){
		if (configFile == null) {
			configFile = new File(plugin.getDataFolder(), getFileName());
		}
		config = YamlConfiguration.loadConfiguration(configFile);

		// Look for defaults in the jar
		Reader defaultConfigStream;
		InputStream defaultConfigInputStream = plugin.getResource(getFileName());
		if (defaultConfigInputStream != null) {
			defaultConfigStream = new InputStreamReader(defaultConfigInputStream, StandardCharsets.UTF_8);
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
			config.setDefaults(defaultConfig);
		}

		plugin.getLogger().info(getFileName()+" reloaded.");
	}

	/**
	 * Saves the default configuration from the jar.
	 */
	protected void saveDefaultConfiguration(){
		if (configFile == null) {
			configFile = new File(plugin.getDataFolder(), getFileName());
		}
		if (!configFile.exists()) {
			plugin.saveResource(getFileName(), false);
		}
	}

	/**
	 * @return FileConfiguration config.
	 */
	protected FileConfiguration getConfig() {
		if (config == null) {
			reloadConfig();
		}
		return config;
	}

	public String getName() {
		return fileName.replace(".yml","");
	}
}

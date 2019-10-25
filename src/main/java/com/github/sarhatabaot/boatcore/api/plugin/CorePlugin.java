package com.github.sarhatabaot.boatcore.api.plugin;

import lombok.Getter;
import lombok.Setter;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author sarhatabaot
 */
public abstract class CorePlugin extends JavaPlugin {
	public static String NAME = getInstance().getDescription().getName();
	public static String VERSION = getInstance().getDescription().getVersion();
	public static String WEBSITE = getInstance().getDescription().getWebsite();
	@Getter @Setter
	private static CorePlugin instance;

	@Override
	public void onDisable() {
		setInstance(null);
	}

	@Override
	public void onEnable() {
		setInstance(this);
		new Metrics(this);
		beforeEnable();
	}

	public abstract void beforeEnable();

}

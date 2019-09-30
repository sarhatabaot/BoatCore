package com.github.sarhatabaot.boatcore.api.plugin;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author sarhatabaot
 */
public abstract class CorePlugin extends JavaPlugin {
	@Getter @Setter
	private static CorePlugin instance;

	@Override
	public void onDisable() {
		setInstance(null);
	}

	@Override
	public void onEnable() {
		setInstance(this);
		beforeEnable();
	}

	public abstract void beforeEnable();

}

package com.github.sarhatabaot.boatcore;

import com.github.sarhatabaot.boatcore.api.plugin.CorePlugin;

/**
 * @author sarhatabaot
 */
public class BoatCore extends CorePlugin {
	@Override
	public void beforeEnable() {
		getSLF4JLogger().info("BoatCore enabled.");
	}
}

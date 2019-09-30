package com.github.sarhatabaot.boatcore.api.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public abstract class Menu {
	private static SimpleSound sound = new SimpleSound(Sound.BLOCK_NOTE_BLOCK_HAT, 0.4F, 1.0F,true);

	/**
	 * Wrapper for easily storing sound values
	 */
	@Getter
	@AllArgsConstructor
	public static class SimpleSound {
		private Sound sound;
		private float volume = 1.0F;
		private float pitch = 1.0F;
		private boolean randomPitch = false;

		public SimpleSound(final Sound sound, final float volume, final float pitch) {
			this(sound,volume,pitch,false);
		}

		public void play(Player player){
			Validate.notNull(sound);
			player.playSound(player.getLocation(), sound, volume, getPitch());
		}

		public void play(Location location) {
			Validate.notNull(sound);
			location.getWorld().playSound(location, sound, volume, getPitch());
		}


		public float getPitch() {
			return randomPitch ? (float) Math.random() : pitch;
		}
	}
}

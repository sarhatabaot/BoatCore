package com.github.sarhatabaot.boatcore.api.menu.model;


import com.github.sarhatabaot.boatcore.api.common.CustomColors;
import lombok.Builder;
import lombok.Singular;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Our core class for easy and comfortable item creation.
 * <p>
 * You can use this to make named items with incredible speed and quality.
 */
@Builder
public final class ItemCreator {
	private Material material;
	private ItemStack item;

	@Singular
	private List<String> lores;
	private boolean glow;
	private boolean unbreakable;

	@Singular
	private List<Enchantment> enchantments;

	private CustomColors color;

	/**
	 * Should we hide all tags from the item (enchants, etc.)?
	 */
	@Builder.Default
	private boolean hideTags = false;

	/**
	 * The amount of the item
	 */
	@Builder.Default
	private final int amount = 1;

	/**
	 * The item name, colors are replaced
	 */
	private final String name;

	private ItemMeta meta;

	@Singular
	private List<ItemFlag> flags;


	/**
	 * Convenience method for quickly adding this item into a players inventory
	 *
	 * @param player
	 */
	public void give(Player player) {
		player.getInventory().addItem(make());
	}

	public ItemStack make() {
		Validate.isTrue(material != null || item != null, "Material or item must be set!");
		ItemStack is = item != null ? item.clone() : new ItemStack(material, amount);
		final ItemMeta itemMeta = meta != null ? meta.clone() : is.getItemMeta();

		if (is.getType() == Material.AIR)
			return is;

		if (this.material != null)
			is.setType(material);

		if (color != null && !item.getType().toString().contains("LEATHER"))
			applyColor(item);

		if (color != null && is.getType().toString().contains("LEATHER"))
			((LeatherArmorMeta) itemMeta).setColor(color.getDye().getColor());

		if (glow) {
			itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
			flags.add(ItemFlag.HIDE_ENCHANTS);
		}

		if (unbreakable) {
			flags.add(ItemFlag.HIDE_ATTRIBUTES);
			flags.add(ItemFlag.HIDE_UNBREAKABLE);
			itemMeta.setUnbreakable(true);
		}

		for(Enchantment enchantment: enchantments){
			itemMeta.addEnchant(enchantment, 1, false);
		}

		item.setItemMeta(itemMeta);
		return item;
	}

	private ItemStack applyColor(ItemStack item) {
		final String dye = color.getDye().toString();
		final List<String> colorableMaterials = Arrays.asList(
				"BANNER", "BED", "CARPET", "CONCRETE", "GLAZED_TERRACOTTA", "SHULKER_BOX",
				"STAINED_GLASS", "STAINED_GLASS_PANE", "TERRACOTTA", "WALL_BANNER", "WOOL");

		for (final String colorable : colorableMaterials) {
			final String suffix = "_" + colorable;

			if (item.getType().toString().endsWith(suffix)) {
				item.setType(Material.valueOf(dye + suffix));

				return item;
			}
		}

		// If not revert to wool
		item.setType(Material.valueOf(dye + "_WOOL"));
		return item;
	}


}
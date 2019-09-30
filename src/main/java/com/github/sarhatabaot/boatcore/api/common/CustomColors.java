package com.github.sarhatabaot.boatcore.api.common;

import org.bukkit.Color;
import org.bukkit.DyeColor;

/*
 * Add additional colors fromRGB https://www.colorhexa.com/ffa500
 */
public enum CustomColors {
	INDIGO(75, 0, 130),
	WHITE(255, 255, 255),
	SILVER(75,75,75),
	GRAY(50,50,50),
	BLACK(0,0,0),
	RED(100,0,0),
	MAROON(50,0,0),
	OLIVE(50,50,0),
	YELLOW(100,100,0),
	LIME(0,100,0),
	GREEN(20,80,20),
	AQUA(0,100,100),
	TEAL(0,50,50),
	BLUE(0,0,100),
	NAVY(0,0,50),
	FUCHSIA(100,0,100),
	PURPLE(50,0,50),
	ORANGE(100,65,0);

	private int red;
	private int green;
	private int blue;

	CustomColors(final int red, final int green, final int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public static CustomColors matchCustomColor(String color){
		return CustomColors.valueOf(color.toLowerCase());
	}

	public static Color matchColor(String color){
		return matchCustomColor(color).toColor();
	}

	public Color toColor() {
		return Color.fromRGB(this.red,this.green, this.blue);
	}

	public static Color getCustomColor(CustomColors color){
		return Color.fromRGB(color.red, color.green, color.blue);
	}

	public DyeColor getDye(){
		return DyeColor.getByColor(this.toColor());
	}
}

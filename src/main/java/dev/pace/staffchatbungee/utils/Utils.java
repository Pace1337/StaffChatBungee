package dev.pace.staffchatbungee.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static String color(final String sinColor) {
        return ChatColor.translateAlternateColorCodes('&', sinColor);
    }

    public static String replacePlaceholders(final String input, final HashMap<String, String> placeholders) {
        String output = input;
        for (final Map.Entry<String, String> entry : placeholders.entrySet()) {
            output = output.replaceAll(entry.getKey(), entry.getValue());
        }
        return output;
    }
}
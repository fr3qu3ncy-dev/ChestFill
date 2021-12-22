package de.fr3qu3ncy.chestfill.lang;

import de.fr3qu3ncy.bukkittools.datastorage.YAMLStorage;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Lang {

    private final YAMLStorage storage;

    public Lang(JavaPlugin plugin) {
        storage = new YAMLStorage(plugin, "plugins/ChestFill", "lang.yml", true);
    }

    public String getNoPermission() {
        return color(storage.getData().getString("no-permission"));
    }

    public String getNoBlockInReach() {
        return color(storage.getData().getString("no-block-in-reach"));
    }

    public String getNoFillableBlock() {
        return color(storage.getData().getString("no-fillable-block"));
    }

    public String getNoItemInHand() {
        return color(storage.getData().getString("no-item-in-hand"));
    }

    public String getFillSuccessful() {
        return color(storage.getData().getString("fill-successful"));
    }

    private String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}

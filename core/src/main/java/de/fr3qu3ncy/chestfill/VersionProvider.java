package de.fr3qu3ncy.chestfill;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface VersionProvider {

    ItemStack getItemInHand(Player player);

}

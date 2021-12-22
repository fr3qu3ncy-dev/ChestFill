package de.fr3qu3ncy.chestfill;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VersionProvider_8 implements VersionProvider {

    @Override
    public ItemStack getItemInHand(Player player) {
        return player.getItemInHand();
    }
}

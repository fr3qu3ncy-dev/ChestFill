package de.fr3qu3ncy.chestfill.command;

import de.fr3qu3ncy.bukkittools.commands.BukkitCommand;
import de.fr3qu3ncy.chestfill.ChestFill;
import de.fr3qu3ncy.chestfill.VersionProvider;
import de.fr3qu3ncy.chestfill.lang.Lang;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FillCommand extends BukkitCommand {

    private final Lang lang;

    public FillCommand(Lang lang) {
        this.lang = lang;
    }

    @Override
    public String getName() {
        return "fill";
    }

    @Override
    public boolean isPlayerOnly() {
        return true;
    }

    @Override
    public String getPermission() {
        return "chestfill.use";
    }

    @Override
    public String getNoPermissionMessage() {
        return lang.getNoPermission();
    }

    @Override
    public void performCommand(Player player, String[] args) {
        //Get the block the player is looking at
        Block targetBlock = player.getTargetBlock(null, 5);

        //Check if there was any block in reach
        if (targetBlock.getType() == Material.AIR) {
            player.sendMessage(lang.getNoBlockInReach());
            return;
        }

        //Check if the block has an inventory
        if (!(targetBlock.getState() instanceof InventoryHolder)) {
            player.sendMessage(lang.getNoFillableBlock());
            return;
        }


        //Get the item in player's main hand
        ItemStack heldItem = ChestFill.getVersionProvider().getItemInHand(player).clone();

        //Check if player specified an amount
        int stackSize = heldItem.getMaxStackSize();
        if (args.length >= 1) {
            try {
                stackSize = Integer.parseInt(args[0]);
                if (stackSize > 64) stackSize = 64;
                if (stackSize < 0) stackSize = 1;
            } catch (NumberFormatException ignored) {}
        }

        heldItem.setAmount(stackSize);

        //Check if player is holding an item
        if (heldItem.getType() == Material.AIR) {
            player.sendMessage(lang.getNoItemInHand());
            return;
        }

        //Get the block's inventory
        Inventory blockInv = ((InventoryHolder) targetBlock.getState()).getInventory();

        //Populate the inventory
        blockInv.clear();
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0 ; i < blockInv.getSize() ; i++) {
            items.add(heldItem);
        }
        blockInv.setContents(items.toArray(new ItemStack[0]));

        player.sendMessage(lang.getFillSuccessful());
    }
}

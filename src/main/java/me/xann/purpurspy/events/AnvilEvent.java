package me.xann.purpurspy.events;

import me.xann.purpurspy.PurpurSpy;
import me.xann.purpurspy.PurpurCommand;
import me.xann.purpurspy.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class AnvilEvent implements Listener {

    private final Plugin plugin = PurpurSpy.getPlugin(PurpurSpy.class);

    @EventHandler
    public void onAnvil(InventoryClickEvent e) {
        String pName = e.getWhoClicked().getName();

        Location loc = e.getWhoClicked().getLocation();
        String world = e.getWhoClicked().getWorld().getName();
        int blockX = loc.getBlockX();
        int blockY = loc.getBlockY();
        int blockZ = loc.getBlockZ();

        if (e.getInventory().getType() == InventoryType.ANVIL && e.getSlotType() == InventoryType.SlotType.RESULT && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
            for (Player admin : Bukkit.getServer().getOnlinePlayers()) {
                if (PurpurCommand.anvilList.contains(admin.getName())) {
                    String msg = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Anvil.Message-on-rename"))).replace("%player%", pName).replace("%world%", world).replace("%blockX%", Integer.toString(blockX)).replace("%blockY%", Integer.toString(blockY)).replace("%blockZ%", Integer.toString(blockZ)).replace("%renamedto%", Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName()).replace("%item%", e.getCurrentItem().getType().name()).replace("\\n", "\n").replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")));
                    String hover = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Anvil.Hover-message"))).replace("%player%", pName).replace("%world%", world).replace("%blockX%", Integer.toString(blockX)).replace("%blockY%", Integer.toString(blockY)).replace("%blockZ%", Integer.toString(blockZ)).replace("%renamedto%", e.getCurrentItem().getItemMeta().getDisplayName()).replace("%item%", e.getCurrentItem().getType().name()).replace("\\n", "\n").replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")));

                    if (!(hover.isEmpty())) {
                        admin.spigot().sendMessage(Util.getFormattedHoverMessage(msg, hover));
                    } else {
                        admin.sendMessage(msg);
                    }
                }
            }
        }
    }
}
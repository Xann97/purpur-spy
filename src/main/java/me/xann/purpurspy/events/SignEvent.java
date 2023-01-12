/*
 * Copyright (c) 2022 Xann97
 */

package me.xann.purpurspy.events;

import me.xann.purpurspy.PurpurSpy;
import me.xann.purpurspy.command.PurpurCommand;
import me.xann.purpurspy.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.Plugin;
import java.util.Objects;

public class SignEvent implements Listener {

    private final Plugin plugin = PurpurSpy.getPlugin(PurpurSpy.class);

    @EventHandler
    public void onSign(SignChangeEvent e) {
        Location loc = e.getBlock().getLocation();

        String line1 = e.getLine(0);
        String line2 = e.getLine(1);
        String line3 = e.getLine(2);
        String line4 = e.getLine(3);

        String pName = e.getPlayer().getName();
        String world = e.getPlayer().getWorld().getName();
        int blockX = loc.getBlockX();
        int blockY = loc.getBlockY();
        int blockZ = loc.getBlockZ();

        if (e.getPlayer().hasPermission("purpurspy.bypass.sign")) {
            return;
        } else {
            for (Player admin : Bukkit.getServer().getOnlinePlayers()) {
                if (PurpurCommand.signList.contains(admin.getName()) || PurpurCommand.allList.contains(admin.getName())) {
                    if (plugin.getConfig().getBoolean("Sign.Ignore-empty") && Objects.requireNonNull(line1).isEmpty() && Objects.requireNonNull(line2).isEmpty() && Objects.requireNonNull(line3).isEmpty() && Objects.requireNonNull(line4).isEmpty()) {
                        return;
                    } else {
                        String msg = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Sign.Message-on-place"))).replace("%player%", pName).replace("%world%", world).replace("%blockX%", Integer.toString(blockX)).replace("%blockY%", Integer.toString(blockY)).replace("%blockZ%", Integer.toString(blockZ)).replace("%line1%", line1).replace("%line2%", line2).replace("%line3%", line3).replace("%line4%", line4).replace("\\n", "\n").replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")));
                        String hover = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Sign.Hover-message"))).replace("%player%", pName).replace("%world%", world).replace("%blockX%", Integer.toString(blockX)).replace("%blockY%", Integer.toString(blockY)).replace("%blockZ%", Integer.toString(blockZ)).replace("%line1%", line1).replace("%line2%", line2).replace("%line3%", line3).replace("%line4%", line4).replace("\\n", "\n").replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")));

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
}

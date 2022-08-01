/*
 * Copyright (c) 2022 Xann97
 */

package me.xann.purpurspy.events;

import me.xann.purpurspy.PurpurCommand;
import me.xann.purpurspy.PurpurSpy;
import me.xann.purpurspy.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Objects;

public class CommandEvent implements Listener {

    private final Plugin plugin = PurpurSpy.getPlugin(PurpurSpy.class);

    @EventHandler()
    public void onCommand(PlayerCommandPreprocessEvent e) {
        String pName = e.getPlayer().getName();
        String command = e.getMessage();

        for (Player admin : Bukkit.getServer().getOnlinePlayers()) {
            if (PurpurCommand.commandList.contains(admin.getName())) {
                String msg = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Command.Message-on-command"))).replace("%player%", pName).replace("%command%", command).replace("\\n", "\n").replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")));
                String hover = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Command.Hover-message"))).replace("%player%", pName).replace("%command%", command).replace("\\n", "\n").replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")));

                if (!(hover.isEmpty())) {
                    admin.spigot().sendMessage(Util.getFormattedHoverMessage(msg, hover));
                } else {
                    admin.sendMessage(msg);
                }
            }
        }
    }
}

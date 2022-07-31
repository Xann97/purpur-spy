package me.xann.purpurspy.events;

import me.xann.purpurspy.PurpurSpy;
import me.xann.purpurspy.PurpurCommand;
import me.xann.purpurspy.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class BookEvent implements Listener {

    private final Plugin plugin = PurpurSpy.getPlugin(PurpurSpy.class);

    @EventHandler()
    public void onBook(PlayerEditBookEvent e) {
        String pName = e.getPlayer().getName();

        Location loc = e.getPlayer().getLocation();
        String world = e.getPlayer().getWorld().getName();
        int blockX = loc.getBlockX();
        int blockY = loc.getBlockY();
        int blockZ = loc.getBlockZ();

        String text = e.getNewBookMeta().getPages().toString();
        String title = e.getNewBookMeta().getTitle();
        if (!e.isSigning())
            title = plugin.getConfig().getString(ChatColor.translateAlternateColorCodes('&', "Book.No-title"));

        for (Player admin : Bukkit.getServer().getOnlinePlayers()) {
            if (PurpurCommand.bookList.contains(admin.getName())) {
                if (plugin.getConfig().getBoolean("Book.Ignore-empty") && text.isEmpty()) {
                    return;
                } else {
                    String msg = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Book.Message-on-edit"))).replace("%player%", pName).replace("%world%", world).replace("%blockX%", Integer.toString(blockX)).replace("%blockY%", Integer.toString(blockY)).replace("%blockZ%", Integer.toString(blockZ)).replace("%text%", String.valueOf(text)).replace("%title%", title).replace("\\n", "\n").replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")));

                    String hover = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Book.Hover-message"))).replace("%player%", pName).replace("%world%", world).replace("%blockX%", Integer.toString(blockX)).replace("%blockY%", Integer.toString(blockY)).replace("%blockZ%", Integer.toString(blockZ)).replace("%text%", String.valueOf(text)).replace("%title%", title).replace("\\n", "\n").replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")));

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

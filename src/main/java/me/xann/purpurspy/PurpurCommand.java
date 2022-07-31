package me.xann.purpurspy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PurpurCommand implements CommandExecutor {

    private final Plugin plugin = PurpurSpy.getPlugin(PurpurSpy.class);

    public static List<String> signList = new ArrayList<>();
    public static List<String> bookList = new ArrayList<>();
    public static List<String> anvilList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5PurpurSpy &7| Help\n&f/purpurspy &8(&fhelp&8) &f- Sends this message.\n&f/purpurspy reload - Reloads config.\n&f/purpurspy sign - Enables or disables sign spy.\n&f/purpurspy book - Enables or disables book spy.\n&f/purpurspy anvil - Enables or disables anvil spy."));
        } else if (args[0].equalsIgnoreCase("reload")) {
            if (sender instanceof Player) {
                Player s = (Player) sender;
                if (s.hasPermission("purpurspy.reload") || s.isOp()) {
                    plugin.getConfig().options().copyDefaults(true);
                    plugin.reloadConfig();
                    s.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.Reload")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                } else {
                    s.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.No-permission")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                }
            } else {
                plugin.getConfig().options().copyDefaults(true);
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.Reload")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
            }
        } else if (args[0].equalsIgnoreCase("sign")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("purpurspy.sign") || sender.isOp()) {
                    if (signList.contains(sender.getName())) {
                        signList.remove(sender.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Sign.Message-on-disable")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                    } else {
                        signList.add(sender.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Sign.Message-on-enable")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.No-permission")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.Not-a-player")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
            }
        } else if (args[0].equalsIgnoreCase("book")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("purpurspy.book") || sender.isOp()) {
                    if (bookList.contains(sender.getName())) {
                        bookList.remove(sender.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Book.Message-on-disable")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                    } else {
                        bookList.add(sender.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Book.Message-on-enable")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.No-permission")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.Not-a-player")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
            }
        } else if (args[0].equalsIgnoreCase("anvil")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("purpurspy.anvil") || sender.isOp()) {
                    if (anvilList.contains(sender.getName())) {
                        anvilList.remove(sender.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Anvil.Message-on-disable")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                    } else {
                        anvilList.add(sender.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Anvil.Message-on-enable")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.No-permission")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                }

            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.Not-a-player")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
            }
        }
        else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.Unknown-command")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
        }
        return true;
    }
}

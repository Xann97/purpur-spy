/*
 * Copyright (c) 2022 Xann97
 */

package me.xann.purpurspy.command;

import me.xann.purpurspy.PurpurSpy;
import me.xann.purpurspy.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PurpurCommand implements CommandExecutor, TabExecutor {

    private final Plugin plugin = PurpurSpy.getPlugin(PurpurSpy.class);

    public static List<String> signList = new ArrayList<>();
    public static List<String> bookList = new ArrayList<>();
    public static List<String> anvilList = new ArrayList<>();
    public static List<String> commandList = new ArrayList<>();
    public static List<String> allList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            String msg = ChatColor.translateAlternateColorCodes('&', "&5PurpurSpy &8| &5Help\n&f/purpurspy &8(&fhelp&8) &f- Sends this message.\n&f/purpurspy reload - Reloads config.\n&f/purpurspy all - Enables or disables sign, book, anvil and command spy.\n&f/purpurspy sign - Enables or disables sign spy.\n&f/purpurspy book - Enables or disables book spy.\n&f/purpurspy anvil - Enables or disables anvil spy.\n&f/purpurspy command - Enables or disables command spy.");
            String hover = ChatColor.translateAlternateColorCodes('&', "&7Plugin author: &5Purpur97 &8(&5Xann97&8)\n&7Plugin version: &5v0.3");
            sender.spigot().sendMessage(Util.getFormattedHoverMessage(msg, hover));

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
        } else if (args[0].equalsIgnoreCase("all")) {
            if (sender instanceof Player) {
                Player s = (Player) sender;
                if (s.hasPermission("purpurspy.all") || s.isOp()) {
                    if (allList.contains(sender.getName())) {
                        allList.remove(sender.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.Disabled-all")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                    } else {
                        allList.add(sender.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.Enabled-all")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.No-permission")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Other-messages.Not-a-player")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
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
        } else if (args[0].equalsIgnoreCase("command")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("purpurspy.command") || sender.isOp()) {
                    if (commandList.contains(sender.getName())) {
                        commandList.remove(sender.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Command.Message-on-disable")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
                    } else {
                        commandList.add(sender.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Command.Message-on-enable")).replace("%prefix%", Objects.requireNonNull(plugin.getConfig().getString("Prefix")))));
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("help");
            if (sender.hasPermission("purpurspy.reload")) {
                arguments.add("reload");
            }
            if (sender.hasPermission("purpurspy.information")) {
                arguments.add("information");
            }
            if (sender.hasPermission("purpurspy.all")) {
                arguments.add("all");
            }
            if (sender.hasPermission("purpurspy.sign")) {
                arguments.add("sign");
            }
            if (sender.hasPermission("purpurspy.book")) {
                arguments.add("book");
            }
            if (sender.hasPermission("purpurspy.anvil")) {
                arguments.add("anvil");
            }
            if (sender.hasPermission("purpurspy.command")) {
                arguments.add("command");
            }
            return arguments;
        }
        return null;
    }

}

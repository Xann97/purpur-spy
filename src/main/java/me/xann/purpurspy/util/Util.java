package me.xann.purpurspy.util;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

public class Util {

    public static TextComponent getFormattedHoverMessage(String message, String hoverMessage) {
        TextComponent formattedMessage = new TextComponent(ChatColor.translateAlternateColorCodes('&', message));
        formattedMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverMessage).create()));
        return formattedMessage;
    }

}

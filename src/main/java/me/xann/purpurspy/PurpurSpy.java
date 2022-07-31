package me.xann.purpurspy;

import me.xann.purpurspy.events.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class PurpurSpy extends JavaPlugin {

    public static PurpurSpy plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        Objects.requireNonNull(getCommand("purpurspy")).setExecutor(new PurpurCommand());

        plugin.getServer().getPluginManager().registerEvents(new SignEvent(), this);
        plugin.getServer().getPluginManager().registerEvents(new BookEvent(), this);
        plugin.getServer().getPluginManager().registerEvents(new AnvilEvent(), this);
    }

}
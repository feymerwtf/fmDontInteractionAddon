package ru.feymer.fmdontinteractionaddon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.feymer.fmdontinteractionaddon.listeners.EventListener;
import ru.feymer.fmdontinteractionaddon.utils.Config;
import ru.feymer.fmdontinteractionaddon.utils.Hex;
import ru.feymer.fmdontinteractionaddon.utils.Updater;

public final class FmDontInteractionAddon extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(Hex.color(""));
        Bukkit.getConsoleSender().sendMessage(Hex.color("&9» &fПлагин &9" + getPlugin(FmDontInteractionAddon.class).getName() + " &fвключился&f!"));
        Bukkit.getConsoleSender().sendMessage(Hex.color("&9» &fВерсия: &9v" + getPlugin(FmDontInteractionAddon.class).getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Hex.color(""));

        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Config.loadYamlFile(this);
        Updater updater = new Updater(this);
        updater.start();

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Hex.color(""));
        Bukkit.getConsoleSender().sendMessage(Hex.color("&9» &fПлагин &9" + getPlugin(FmDontInteractionAddon.class).getName() + " &fвыключился&f!"));
        Bukkit.getConsoleSender().sendMessage(Hex.color("&9» &fВерсия: &9v" + getPlugin(FmDontInteractionAddon.class).getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Hex.color(""));
    }
}

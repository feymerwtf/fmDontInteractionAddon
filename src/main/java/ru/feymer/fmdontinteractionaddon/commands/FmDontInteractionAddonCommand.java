package ru.feymer.fmdontinteractionaddon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.feymer.fmdontinteractionaddon.FmDontInteractionAddon;
import ru.feymer.fmdontinteractionaddon.utils.Utils;

public class FmDontInteractionAddonCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("fmdontinteractionaddon.reload")) {
                    FmDontInteractionAddon.getInstance().reloadConfig();
                    Utils.sendMessage(sender, Utils.getString("messages.reload"));
                } else {
                    Utils.sendMessage(sender, Utils.getString("messages.no-permission"));
                }
                return true;
            } else {
                Utils.sendMessage(sender, Utils.getString("messages.no-args"));

            }
            return true;
        } else {
            Utils.sendMessage(sender, Utils.getString("messages.no-args"));
        }
        return false;
    }
}

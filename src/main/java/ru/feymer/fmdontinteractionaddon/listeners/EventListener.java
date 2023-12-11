package ru.feymer.fmdontinteractionaddon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import ru.feymer.fmdontinteractionaddon.utils.Utils;
import ru.leymooo.antirelog.event.PvpStartedEvent;
import ru.leymooo.antirelog.event.PvpStoppedEvent;
import ru.leymooo.antirelog.event.PvpTimeUpdateEvent;

import java.util.ArrayList;

public class EventListener implements Listener {

    private final ArrayList playersInRelog = new ArrayList();

    @EventHandler
    public void onAttackFirst(PvpStartedEvent event) {
        Player attacker = event.getAttacker();
        Player defender = event.getDefender();
        this.addPlayer(attacker.getName());

        if (defender != null) {
            this.addPlayer(defender.getName());
        }
    }

    @EventHandler
    public void onAttackUpdate(PvpTimeUpdateEvent event) {
        Player attacker = event.getPlayer();
        Player defender = event.getDamagedPlayer();
        this.addPlayer(attacker.getName());

        if (defender != null) {
            this.addPlayer(defender.getName());
        }
    }

    @EventHandler
    public void onAttackStop(PvpStoppedEvent event) {
        Player player = event.getPlayer();
        this.removePlayer(player.getName());
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("fmdontinteractionaddon.bypass")) {
            return;
        }
        if (!this.contains(player.getName())) {
            String[] args = event.getMessage().split(" ");
            if (args.length >= 2) {
                for (String commands : Utils.getStringList("commands")) {
                    if (event.getMessage().startsWith(commands)) {
                        Player player1 = Bukkit.getPlayer(args[1]);
                        if (player1 != null && this.contains(player1.getName())) {
                            Utils.sendMessage(player, Utils.getString("messages.player-in-pvp"));
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    public void addPlayer(String player) {
        playersInRelog.add(player);
    }

    public void removePlayer(String player) {
        playersInRelog.remove(player);
    }

    public boolean contains(String player) {
        return playersInRelog.contains(player);
    }
}

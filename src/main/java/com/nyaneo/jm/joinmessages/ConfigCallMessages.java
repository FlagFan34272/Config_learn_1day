package com.nyaneo.jm.joinmessages;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EventListener;

public class ConfigCallMessages extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic
        config.options().copyDefaults(true);
        saveConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        config.options().copyDefaults(true);
        saveConfig();

        e.setJoinMessage(ChatColor.RED + "===============" + "Walkers SMP" + "===============\n " + ChatColor.WHITE + "[" + ChatColor.AQUA + "+" + ChatColor.WHITE + "]"  + ChatColor.AQUA + player.getName() + ChatColor.GRAY + "님이 서버에 입장하셨어요! 모두 환영해주세요!" + ChatColor.RED + "========================================" );

    }

    public FileConfiguration getConfigFile(){
        return config;
    }

}
















































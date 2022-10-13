package com.nyaneo.jm.joinmessages;

import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class JoinMessages extends JavaPlugin implements @NotNull Listener {

    FileConfiguration config = this.getConfig();
    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(this, this);
        config.options().copyDefaults(true);
        saveConfig();
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        config.options().copyDefaults(true);
        saveConfig();

        //참고로 ConfigCallMessages 일부러 안불러옴
        if (config.getString("joinMessage-feather").equalsIgnoreCase("enabled")) {
            e.setJoinMessage(ChatColor.RED + "===============" + config.getString("server name") + "===============\n " + ChatColor.WHITE + "[" + ChatColor.AQUA + "+" + ChatColor.WHITE + "]" + ChatColor.AQUA + player.getName() + ChatColor.GRAY + config.getString("join msg(exclude nickname)") + "\n" + ChatColor.RED + config.getString("join-footer"));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        config.options().copyDefaults(true);
        saveConfig();

        if (config.getString("leaveMessage-feather").equalsIgnoreCase("enabled")) {
            e.setQuitMessage(ChatColor.RED + "===============" + config.getString("server name") + "===============\n " + ChatColor.WHITE + "[" + ChatColor.RED + "-" + ChatColor.WHITE + "]" + ChatColor.AQUA + player.getName() + ChatColor.GRAY + " " + config.getString("quit msg(exclude nickname)") + "\n" + ChatColor.RED + config.getString("quit-footer"));
        }
    }

    public FileConfiguration getConfigFile() {
        return config;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("server-owner")) {


            if (sender instanceof Player) { // 명령어를 보낸 사람이 Player 객체 이라면.
                Player p = (Player) sender;// p 변수에 보낸 사람을 담습니다.
                p.sendMessage(ChatColor.AQUA + "This server's owner is " + ChatColor.GRAY + config.getString("server owner"));
            }
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("discord")) {

            if (config.getString("cmd-discord").equalsIgnoreCase("enabled")) {
                if (sender instanceof Player) { // 명령어를 보낸 사람이 Player 객체 이라면.
                    Player p = (Player) sender;// p 변수에 보낸 사람을 담습니다.
                    p.sendMessage(ChatColor.AQUA + "Click This to enter DiscordServer! " + ChatColor.YELLOW + config.getString("discord name") + " " + ChatColor.LIGHT_PURPLE + config.getString("discord link"));
                }
            } else {
                Player p = (Player) sender;
                p.sendMessage("please enable it first!");
            }
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("info")) {
            if (config.getString("cmd-info").equalsIgnoreCase("enabled")) {


                if (sender instanceof Player) { // 명령어를 보낸 사람이 Player 객체 이라면.
                    Player p = (Player) sender;// p 변수에 보낸 사람을 담습니다.
                    p.sendMessage(ChatColor.AQUA + "This server's owner is " + ChatColor.GRAY + config.getString("server owner") + "\n" + "and This server is " + config.getString("server type"));
                }
                return false;
            } else{
                Player p = (Player) sender;
                p.sendMessage("please enable it first!");

            }

        }



        return false;
    }









    public String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}

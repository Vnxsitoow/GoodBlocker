package com.vnx.goodblocker;

import com.vnx.goodblocker.commands.GoodBlockerCommand;
import com.vnx.goodblocker.listeners.CommandListener;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class GoodBlocker extends JavaPlugin {

    private List<String> blockedCommands;
    private String blockedMessage;
    private boolean enableLogging;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfiguration();

        getCommand("goodblocker").setExecutor(new GoodBlockerCommand(this));
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);

        getLogger().info("GoodBlocker has been enabled successfully!");
        getLogger().info("Blocked commands: " + blockedCommands.size());
    }

    @Override
    public void onDisable() {
        getLogger().info("GoodBlocker has been disabled!");
    }

    public void loadConfiguration() {
        reloadConfig();
        FileConfiguration config = getConfig();

        blockedCommands = config.getStringList("blocked-commands");
        blockedMessage = ChatColor.translateAlternateColorCodes('&',
                config.getString("messages.blocked-command", "&cThis command is blocked!"));
        enableLogging = config.getBoolean("enable-logging", true);

        if (blockedCommands == null) {
            blockedCommands = new ArrayList<>();
        }
    }

    public List<String> getBlockedCommands() {
        return new ArrayList<>(blockedCommands);
    }

    public String getBlockedMessage() {
        return blockedMessage;
    }

    public boolean isLoggingEnabled() {
        return enableLogging;
    }

    public boolean isCommandBlocked(String command) {
        command = command.toLowerCase().startsWith("/") ? command.substring(1) : command;

        for (String blocked : blockedCommands) {
            String blockedCmd = blocked.toLowerCase();

            if (command.equals(blockedCmd) || command.startsWith(blockedCmd + " ")) {
                return true;
            }

            if (blockedCmd.endsWith("*")) {
                String prefix = blockedCmd.substring(0, blockedCmd.length() - 1);
                if (command.startsWith(prefix)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void addBlockedCommand(String command) {
        if (!blockedCommands.contains(command)) {
            blockedCommands.add(command);
            getConfig().set("blocked-commands", blockedCommands);
            saveConfig();
        }
    }

    public void removeBlockedCommand(String command) {
        if (blockedCommands.remove(command)) {
            getConfig().set("blocked-commands", blockedCommands);
            saveConfig();
        }
    }
}
package com.vnx.goodblocker.commands;

import com.vnx.goodblocker.GoodBlocker;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodBlockerCommand implements CommandExecutor, TabCompleter {

    private final GoodBlocker plugin;

    public GoodBlockerCommand(GoodBlocker plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("goodblocker.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                if (!sender.hasPermission("goodblocker.reload")) {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to reload the configuration!");
                    return true;
                }
                plugin.loadConfiguration();
                sender.sendMessage(ChatColor.GREEN + "Configuration reloaded successfully!");
                break;

            case "list":
                List<String> blocked = plugin.getBlockedCommands();
                if (blocked.isEmpty()) {
                    sender.sendMessage(ChatColor.YELLOW + "No commands are blocked.");
                } else {
                    sender.sendMessage(ChatColor.GREEN + "Blocked commands (" + blocked.size() + "):");
                    for (String cmd : blocked) {
                        sender.sendMessage(ChatColor.GRAY + "  - " + ChatColor.WHITE + cmd);
                    }
                }
                break;

            case "add":
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + "Usage: /goodblocker add <command>");
                    return true;
                }
                String cmdToAdd = args[1].toLowerCase();
                plugin.addBlockedCommand(cmdToAdd);
                sender.sendMessage(ChatColor.GREEN + "Command '" + cmdToAdd + "' added to the block list!");
                break;

            case "remove":
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + "Usage: /goodblocker remove <command>");
                    return true;
                }
                String cmdToRemove = args[1].toLowerCase();
                plugin.removeBlockedCommand(cmdToRemove);
                sender.sendMessage(ChatColor.GREEN + "Command '" + cmdToRemove + "' removed from the block list!");
                break;

            default:
                sendHelp(sender);
                break;
        }

        return true;
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "=== GoodBlocker Help ===");
        sender.sendMessage(ChatColor.YELLOW + "/gb reload" + ChatColor.GRAY + " - Reload the configuration");
        sender.sendMessage(ChatColor.YELLOW + "/gb list" + ChatColor.GRAY + " - List blocked commands");
        sender.sendMessage(ChatColor.YELLOW + "/gb add <command>" + ChatColor.GRAY + " - Block a command");
        sender.sendMessage(ChatColor.YELLOW + "/gb remove <command>" + ChatColor.GRAY + " - Unblock a command");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("reload", "list", "add", "remove");
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
            return new ArrayList<>(plugin.getBlockedCommands());
        }

        return new ArrayList<>();
    }
}
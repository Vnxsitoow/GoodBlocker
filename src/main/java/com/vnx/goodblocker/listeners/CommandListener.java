package com.vnx.goodblocker.listeners;

import com.vnx.goodblocker.GoodBlocker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    private final GoodBlocker plugin;

    public CommandListener(GoodBlocker plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();

        if (player.hasPermission("goodblocker.bypass")) {
            return;
        }

        String commandName = command.split(" ")[0].substring(1);

        if (plugin.isCommandBlocked(commandName)) {
            event.setCancelled(true);
            player.sendMessage(plugin.getBlockedMessage());

            if (plugin.isLoggingEnabled()) {
                plugin.getLogger().info(player.getName() + " tried to use blocked command: " + command);
            }
        }
    }
}
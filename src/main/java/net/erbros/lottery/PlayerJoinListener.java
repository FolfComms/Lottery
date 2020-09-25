package net.erbros.lottery;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoinListener implements Listener {

    private Lottery plugin;
    private LotteryConfig lConfig;
    private LotteryGame lGame;

    public PlayerJoinListener(Lottery plugin) {
        this.plugin = plugin;
        lGame = plugin.getLotteryGame();
        lConfig = plugin.getLotteryConfig();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Send the player some info about time until lottery draw?
        lGame.sendMessage(event.getPlayer(), "Welcome");
    }
}

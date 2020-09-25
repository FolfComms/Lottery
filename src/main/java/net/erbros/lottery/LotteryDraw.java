package net.erbros.lottery;

import java.util.TimerTask;


class LotteryDraw extends TimerTask {

    private final Lottery plugin;
    private final boolean draw;

    public LotteryDraw(Lottery plugin, boolean draw) {
        this.plugin = plugin;
        this.draw = draw;
    }

    public void run() {

        if (draw && plugin.isLotteryDue()) {
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, plugin::lotteryDraw);

        } else {
            plugin.extendLotteryDraw();
        }
    }
}
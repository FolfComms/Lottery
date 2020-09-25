package net.erbros.lottery;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class Utils {
    public static String getCostMessage(double cost, LotteryConfig lConfig) {
        if (lConfig.useEconomy()) {
            return lConfig.formatCurrency((formatAmount(cost, lConfig.useEconomy())));
        } else {
            return String.valueOf(
                    (int) formatAmount(cost, lConfig.useEconomy())).concat(
                    " " + formatMaterialName(lConfig.getMaterial()));
        }
    }

    public static double formatAmount(double amount, boolean usingiConomy) {
        if (usingiConomy) {
            return Math.floor(amount * 100) / 100;
        } else {
            return Math.floor(amount);
        }
    }

    public static String formatMaterialName(Material material) {
        String rawMaterialName = material.name();
        rawMaterialName = rawMaterialName.toLowerCase(Locale.ENGLISH);
        String firstLetterCapital = rawMaterialName.substring(0, 1).toUpperCase();
        rawMaterialName = firstLetterCapital + rawMaterialName.substring(1);
        return rawMaterialName.replace("_", " ");
    }


    public static String timeUntil(long time, boolean mini, LotteryConfig lConfig) {
        long timeLeft = time;
        // How many days left?
        String stringTimeLeft = "";

        if (timeLeft >= 60 * 60 * 24) {
            int days = (int) Math.floor(timeLeft / (60 * 60 * 24));
            timeLeft -= 60 * 60 * 24 * days;
            if (mini) {
                stringTimeLeft += days + "d ";
            } else {
                stringTimeLeft += days + " " + lConfig.getPlural("day", days) + ", ";
            }
        }
        if (timeLeft >= 60 * 60) {
            int hours = (int) Math.floor(timeLeft / (60 * 60));
            timeLeft -= 60 * 60 * hours;
            if (mini) {
                stringTimeLeft += hours + "h ";
            } else {
                stringTimeLeft += hours + " " + lConfig.getPlural("hour", hours) + ", ";
            }
        }
        if (timeLeft >= 60) {
            int minutes = (int) Math.floor(timeLeft / (60));
            timeLeft -= 60 * minutes;
            if (mini) {
                stringTimeLeft += minutes + "m ";

            } else {
                stringTimeLeft += minutes + " " + lConfig.getPlural("minute", minutes) + ", ";
            }
        } else {
            // Lets remove the last comma, since it will look bad with 2 days, 3
            // hours, and 14 seconds.
            if (!stringTimeLeft.equalsIgnoreCase("") && !mini) {
                stringTimeLeft = stringTimeLeft.substring(
                        0, stringTimeLeft.length() - 1);
            }
        }
        int secs = (int) timeLeft;
        if (mini) {
            stringTimeLeft += secs + "s";
        } else {
            if (!stringTimeLeft.equalsIgnoreCase("")) {
                stringTimeLeft += "and ";
            }
            stringTimeLeft += secs + " " + lConfig.getPlural("second", secs);
        }

        return stringTimeLeft;
    }

    public static Map<String, Integer> realPlayersFromList(List<String> ticketList) {
        Map<String, Integer> playerList = new HashMap<>();
        int value;
        for (String check : ticketList) {
            if (playerList.containsKey(check)) {
                value = Integer.parseInt(playerList.get(check).toString()) + 1;
            } else {
                value = 1;
            }
            playerList.put(check, value);
        }
        return playerList;
    }

    public static int parseInt(String arg) {
        int newInt = 0;
        try {
            newInt = Integer.parseInt(arg);
        } catch (NumberFormatException ignored) {
        }
        return Math.max(newInt, 0);
    }

    public static double parseDouble(String arg) {
        double newDouble = 0;
        try {
            newDouble = Double.parseDouble(arg);
        } catch (NumberFormatException ignored) {
        }
        return newDouble > 0 ? newDouble : 0;
    }
}

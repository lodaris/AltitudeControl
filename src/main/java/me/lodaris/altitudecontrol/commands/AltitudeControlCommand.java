package me.lodaris.altitudecontrol.commands;

import me.lodaris.altitudecontrol.Main;
import me.lodaris.altitudecontrol.Utils.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class AltitudeControlCommand implements CommandExecutor {
    private final Main plugin = Main.getInstance();
    private final Map<String, BiConsumer<Player, String[]>> subCommands = new HashMap<>();

    public AltitudeControlCommand() {
        subCommands.put("maxHeight", this::handleMaxHeight);
        subCommands.put("minHeight", this::handleMinHeight);
        subCommands.put("damage", this::handleDamage);
        subCommands.put("period", this::handlePeriod);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Эта команда доступна только игрокам!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            ChatUtil.sendMessage(player, plugin.getConfig().getString("messages.unknownCommandMessage"), ChatUtil.MessageType.ERROR);
            return true;
        }

        BiConsumer<Player, String[]> subCommand = subCommands.get(args[0]);
        if (subCommand != null) {
            subCommand.accept(player, args);
        } else {
            ChatUtil.sendMessage(player, plugin.getConfig().getString("messages.unknownCommandMessage"), ChatUtil.MessageType.ERROR);
        }
        return true;
    }

    private void handleMaxHeight(Player player, String[] args) {
        if (args.length == 1) {
            sendConfigValue(player, "messages.maxHeightMessage", "maxHeight");
        } else {
            updateConfigValue(player, args[1], "maxHeight", "messages.setMaxHeightMessage");
        }
    }

    private void handleMinHeight(Player player, String[] args) {
        if (args.length == 1) {
            sendConfigValue(player, "messages.minHeightMessage", "minHeight");
        } else {
            updateConfigValue(player, args[1], "minHeight", "messages.setMinHeightMessage");
        }
    }

    private void handleDamage(Player player, String[] args) {
        if (args.length == 1) {
            sendConfigValue(player, "messages.damageMessage", "damage");
        } else {
            updateConfigValue(player, args[1], "damage", "messages.setDamageMessage");
        }
    }

    private void handlePeriod(Player player, String[] args) {
        if (args.length == 1) {
            sendConfigValue(player, "messages.periodMessage", "damagePeriod");
        } else {
            try {
                long period = Long.parseLong(args[1]);
                plugin.getConfig().set("damagePeriod", period);
                plugin.saveConfig();
                plugin.getAltitudeCheckerEvent().restartChecking();
                ChatUtil.sendMessage(player, plugin.getConfig().getString("messages.setPeriodMessage") + period, ChatUtil.MessageType.SUCCESS);
            } catch (NumberFormatException e) {
                ChatUtil.sendMessage(player, "Укажите числовое значение!", ChatUtil.MessageType.ERROR);
            }
        }
    }

    private void sendConfigValue(Player player, String messageKey, String configKey) {
        String message = plugin.getConfig().getString(messageKey) + plugin.getConfig().get(configKey);
        ChatUtil.sendMessage(player, message, ChatUtil.MessageType.SUCCESS);
    }

    private void updateConfigValue(Player player, String value, String configKey, String messageKey) {
        try {
            double numericValue = Double.parseDouble(value);
            plugin.getConfig().set(configKey, numericValue);
            plugin.saveConfig();
            plugin.getAltitudeCheckerEvent().restartChecking();
            String message = plugin.getConfig().getString(messageKey) + numericValue;
            ChatUtil.sendMessage(player, message, ChatUtil.MessageType.SUCCESS);
        } catch (NumberFormatException e) {
            ChatUtil.sendMessage(player, "Укажите числовое значение!", ChatUtil.MessageType.ERROR);
        }
    }
}

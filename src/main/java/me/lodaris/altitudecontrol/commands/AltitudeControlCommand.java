package me.lodaris.altitudecontrol.commands;

import me.lodaris.altitudecontrol.Main;
import me.lodaris.altitudecontrol.Utils.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AltitudeControlCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Эта команда доступна только игрокам!");
            return true;
        }

        Player p = (Player) sender;


        if(args.length == 1) {

            if(args[0].equals("maxHeight")) {

                ChatUtil.sendMessage(p, Main.getInstance().getConfig().getString("messages.maxHeightMessage")
                        + Main.getInstance().getConfig().getDouble("maxHeight"), ChatUtil.MessageType.SUCCESS);
                return true;
            } else if(args[0].equals("damage")) {
                ChatUtil.sendMessage(p, Main.getInstance().getConfig().getString("messages.damageMessage")
                        + Main.getInstance().getConfig().getDouble("damage"), ChatUtil.MessageType.SUCCESS);
                return true;
            } else {
                ChatUtil.sendMessage(p, Main.getInstance().getConfig().getString("messages.unknownCommandMessage"), ChatUtil.MessageType.ERROR);
                return true;
            }

        }

        if(args.length == 2) {
            if(args[0].equals("maxHeight")) {
                double maxHeight = 0.0;
                try {
                    String maxHeightString = args[1];
                    maxHeight = Double.parseDouble(maxHeightString);
                } catch (NumberFormatException e) {
                    ChatUtil.sendMessage(p, "Укажите числовое значение высоты!", ChatUtil.MessageType.ERROR);
                    return true;
                }

                Main.getInstance().getConfig().set("maxHeight", maxHeight);
                ChatUtil.sendMessage(p, Main.getInstance().getConfig().getString("messages.setMaxHeightMessage") + maxHeight, ChatUtil.MessageType.SUCCESS);

            } else if(args[0].equals("damage")) {
                double damage = 0.0;
                try {
                    String damageString = args[1];
                    damage = Double.parseDouble(damageString);
                } catch (NumberFormatException e) {
                    ChatUtil.sendMessage(p, "Укажите числовое значение урона!", ChatUtil.MessageType.ERROR);
                    return true;
                }

                Main.getInstance().getConfig().set("damage", damage);
                ChatUtil.sendMessage(p, Main.getInstance().getConfig().getString("messages.setDamageMessage") + damage, ChatUtil.MessageType.SUCCESS);

            } else {
                ChatUtil.sendMessage(p, Main.getInstance().getConfig().getString("messages.unknownCommandMessage"), ChatUtil.MessageType.ERROR);
                return true;
            }
        }

        return true;
    }
}

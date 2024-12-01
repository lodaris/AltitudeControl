package me.lodaris.altitudecontrol.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtil {

    private static final String PREFIX = ChatColor.translateAlternateColorCodes('&', "&7[&4AC&7] &7");

    public enum MessageType {
        ERROR(ChatColor.RED),
        SUCCESS(ChatColor.GREEN),
        INFO(ChatColor.GOLD);

        private final ChatColor color;

        MessageType(ChatColor color) {
            this.color = color;
        }

        public ChatColor getColor() {
            return color;
        }
    }

    public static void sendMessage(Player player, String msg, MessageType type) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',PREFIX + type.getColor() + msg));
    }
}

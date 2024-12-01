package me.lodaris.altitudecontrol.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    private static final List<String> MAIN_COMMANDS = Arrays.asList("maxHeight", "minHeight", "damage", "period");
    private static final List<String> DEFAULT_SUGGESTIONS = Collections.singletonList("0");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return filterSuggestions(MAIN_COMMANDS, args[0]);
        } else if (args.length == 2) {
            return DEFAULT_SUGGESTIONS;
        }
        return Collections.emptyList();
    }

    /**
     * Фильтрует список команд на основе введенного пользователем префикса
     *
     * @param suggestions Список всех возможных команд
     * @param prefix      Введенный пользователем префикс
     * @return Отфильтрованный список предложений
     */

    private List<String> filterSuggestions(List<String> suggestions, String prefix) {
        if (prefix.isEmpty()) return suggestions;
        List<String> filtered = new ArrayList<>();
        for (String suggestion : suggestions) {
            if (suggestion.toLowerCase().startsWith(prefix.toLowerCase())) {
                filtered.add(suggestion);
            }
        }
        return filtered;
    }
}

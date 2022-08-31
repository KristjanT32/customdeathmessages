package krisapps.customdeathmessages.logging;

import org.bukkit.ChatColor;

public enum LoggingLevel {

    INFO("/INFO", ChatColor.WHITE),
    WARNING("/WARN", ChatColor.YELLOW),
    ERROR("/ERR", ChatColor.RED),
    FATAL("/FATAL", ChatColor.DARK_RED)

    ;

    private String label;
    private ChatColor color;
    LoggingLevel(String label, ChatColor color){
        this.label = label;
        this.color = color;
    }

}

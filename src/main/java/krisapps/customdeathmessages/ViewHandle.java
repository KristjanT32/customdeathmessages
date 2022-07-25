package krisapps.customdeathmessages;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ViewHandle implements CommandExecutor {

    CustomDeathMessages main;
    HandleManager hm;

    public ViewHandle(CustomDeathMessages main){
        this.main = main;
        hm = new HandleManager(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //Syntax: /viewhandle <handlePath>
            hm.showHandle(args[0], args[1], sender);
        return true;
    }
}

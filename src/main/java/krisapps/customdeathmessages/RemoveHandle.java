package krisapps.customdeathmessages;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class RemoveHandle implements CommandExecutor {

    CustomDeathMessages main;
    HandleManager hm;

    public RemoveHandle(CustomDeathMessages main){
        this.main = main;
        hm = new HandleManager(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //Syntax: /removehandle <handlePath>
            hm.removeHandle(args[0], args[1], sender);
        return true;
    }
}

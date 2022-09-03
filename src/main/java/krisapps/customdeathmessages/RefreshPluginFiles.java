package krisapps.customdeathmessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class RefreshPluginFiles implements CommandExecutor {

    CustomDeathMessages main;
    public RefreshPluginFiles(CustomDeathMessages main){
        this.main = main;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //Syntax: /refpf

        if (main.refreshDataFiles()){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&aCustomDeathMessages&e]: &bSuccessfully refreshed plugin files!"));
        }else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[&aCustomDeathMessages&e]: &cSomething went wrong while refreshing the files. Please check the logs for more info."));
        }

        return true;
    }
}

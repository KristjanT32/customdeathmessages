package krisapps.customdeathmessages.commands;

import krisapps.customdeathmessages.CustomDeathMessages;
import krisapps.customdeathmessages.enums.HandleAction;
import krisapps.customdeathmessages.enums.HandleTrigger;
import krisapps.customdeathmessages.managers.HandleManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

@SuppressWarnings("ALL")
public class AddHandle implements CommandExecutor {

    final CustomDeathMessages main;
    final HandleManager hc;

    public AddHandle(CustomDeathMessages main){
        this.main = main;
        hc = new HandleManager(main);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //Syntax: /addhandle <trigger> [item] <action_type> <command | message_to_broadcast>
        ItemStack item;
        int level;
        StringBuilder value = new StringBuilder();

        if (sender instanceof Player) {
            if (args.length >= 4){
                switch (HandleTrigger.valueOf(args[0])){
                    case ITEM_IN_HAND:
                    case HAS_ITEM_IN_INVENTORY:
                    case ARMOR_BOOTS:
                    case ARMOR_LEGGINS:
                    case ARMOR_CHESTPLATE:
                    case ARMOR_HELMET:
                        for (int i = 3; i < args.length; i++){
                            value.append(args[i]).append(" ");
                        }
                        value = new StringBuilder(value.substring(0, value.length() - 1));
                        try{
                            item = new ItemStack(Material.getMaterial(args[1].toUpperCase(Locale.ROOT).replace("minecraft:", " ")));
                            hc.addHandle(HandleTrigger.valueOf(args[0]), item, HandleAction.valueOf(args[2]), value.toString(), sender);
                        }catch (IllegalArgumentException e){
                            sender.sendMessage(ChatColor.RED + "Invalid parameter: " + ChatColor.YELLOW + args[1] + ChatColor.RED + "\nShould be an item.");
                        }
                        break;
                    case PLAYER_LEVEL_EQUALTO:
                    case PLAYER_LEVEL_OVER:
                    case PLAYER_LEVEL_UNDER:
                        for (int i = 3; i < args.length; i++){
                            value.append(args[i]).append(" ");
                        }
                        try {
                            Integer.parseInt(args[1]);
                        }catch (NumberFormatException e){
                            sender.sendMessage(ChatColor.RED + "Invalid parameter: " + args[1] + ".\nShould be an integer.");
                             return false;
                        }
                            level = Integer.parseInt(args[1]);
                        value = new StringBuilder(value.substring(0, value.length() - 1));
                        hc.addHandle(HandleTrigger.valueOf(args[0]), level, HandleAction.valueOf(args[2]), value.toString(), sender);
                        break;
                }
            }else{
                sender.sendMessage(ChatColor.RED + "Invalid syntax.");
                return false;
            }
        }else{
            sender.sendMessage(ChatColor.RED + "Sorry, but this command can only be executed by a player.");
        }
        return true;
    }
}

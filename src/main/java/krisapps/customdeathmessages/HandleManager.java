package krisapps.customdeathmessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Optional;

public class HandleManager {

    CustomDeathMessages main;
    FileConfiguration dataFile;

    public HandleManager(CustomDeathMessages main){
        this.main = main;
        dataFile = main.data;
    }

    String param = "";
    public <T> void addHandle(HandleTrigger trigger, T trigger_parameter, HandleAction action, String value, CommandSender sender){
        if (trigger_parameter instanceof ItemStack) {
            dataFile.set("handles." + trigger.name() + "." + ((ItemStack)trigger_parameter).getType() + ".action", action.name());
            dataFile.set("handles." + trigger.name() + "." + ((ItemStack)trigger_parameter).getType() + ".actionValue", value);
            dataFile.set("handles." + trigger.name() + "." + ((ItemStack)trigger_parameter).getType() + ".conditionParameter", ((ItemStack) trigger_parameter).getType());
            param = ((ItemStack) trigger_parameter).getType().name();
        }else if (trigger_parameter instanceof Integer){
            dataFile.set("handles." + trigger.name() + "." + trigger_parameter + ".action", action.name());
            dataFile.set("handles." + trigger.name() + "." + trigger_parameter + ".actionValue", value);
            dataFile.set("handles." + trigger.name() + "." + trigger_parameter + ".conditionParameter", trigger_parameter);
            param = String.valueOf((int)trigger_parameter);
        }else{
            sender.sendMessage(ChatColor.RED + "Invalid parameter.");
        }
            try {
                dataFile.save(main.dataFile);
                sender.sendMessage(ChatColor.YELLOW + "============================================");
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lSuccessfully set the following handle&r&a: " + "\n&eOccur if  &a" + trigger.name() + " &b" + param + "\n&eThen do &a" + action.name() + "\n&eWith value: &a" + value));
                sender.sendMessage(ChatColor.YELLOW + "============================================");
            } catch (IOException e) {
                sender.sendMessage(ChatColor.YELLOW + "============================================");
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cFailed to add the following handle: \n&eTrigger: &a" + trigger.name() + "\n&eTrigger Parameter: &a" + trigger_parameter + "\n&eAction: &a" + action.name() + "\n&eAction Value: &a" + value));
                sender.sendMessage(ChatColor.YELLOW + "============================================");
            }
    }

    public void removeHandle(String trigger, String handle, CommandSender sender) {
        dataFile.set("handles." + trigger + "." + handle, null);
        try {
            dataFile.save(main.dataFile);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully removed handle &e'&d" + "handles." + trigger + "." + handle + "&e'&a."));
        } catch (IOException e) {
            sender.sendMessage(ChatColor.YELLOW + "============================================");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cFailed to remove handle &e'&d" + "handles." + trigger + "." + handle + "&e'&a."));
            sender.sendMessage(ChatColor.YELLOW + "============================================");
            e.printStackTrace();
        }
    }

    public void showHandle(String trigger, String handle, CommandSender sender){
            if (main.data.get("handles." + trigger) != null){
                StringBuilder finalString = new StringBuilder();
                finalString.append("&e===========================================");
                finalString.append("\n&bHandle Path: &a" + "handles." + trigger + "." + handle + "\n");
                finalString.append("&bHandle Trigger: &a" + trigger + "\n");
                finalString.append("&bHandle Condition: &a" + main.data.getString("handles." + trigger + "." + handle + ".conditionParameter") + "\n");
                finalString.append("&bHandle Action: &a" + main.data.getString("handles." + trigger + "." + handle + ".action") + "\n");
                finalString.append("&bHandle Action Value: &a" + main.data.getString("handles." + trigger + "." + handle + ".actionValue") + "\n");
                finalString.append("&e===========================================");
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', finalString.toString()));
            }else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCould not find handle &e'&d" + trigger + "." + handle + "&e'&a."));
            }
    }

}

package krisapps.customdeathmessages.handlers;

import krisapps.customdeathmessages.CustomDeathMessages;
import krisapps.customdeathmessages.enums.HandleAction;
import krisapps.customdeathmessages.enums.HandleTrigger;
import krisapps.customdeathmessages.exceptions.TargetPlayerNullException;
import krisapps.customdeathmessages.logging.Logger;
import krisapps.customdeathmessages.logging.LoggingLevel;
import krisapps.customdeathmessages.managers.HandleManager;
import krisapps.customdeathmessages.managers.VanillaDeathMessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class OnPlayerDeath implements Listener {

    CustomDeathMessages main;
    HandleManager hm;
    VanillaDeathMessageManager vdhm;
    Logger log;

    public OnPlayerDeath(CustomDeathMessages main){
        this.main = main;
        hm = new HandleManager(main);
        vdhm = new VanillaDeathMessageManager(this.main);
        log = new Logger(main);
    }


    @EventHandler
    public void OnPlayerDied(PlayerDeathEvent e){
        Player p = e.getEntity().getPlayer();

        log.log("Start death handler for " + p.getDisplayName(), LoggingLevel.INFO);
        if (main.config.getBoolean("configuration.general.interceptVanillaDeathMessages")) {
            log.log("[VanillaDeathMessageManager]: Interception enabled, proceeding...", LoggingLevel.INFO);
            String message = "";
            try {
                message = vdhm.handlePlayerDeath(p.getUniqueId(), e);
            }catch (TargetPlayerNullException exception){
                log.log("Error caught: player null", LoggingLevel.INFO);
            }
            log.log("[VanillaDeathMessageManager]: Replacing original message: " + e.getDeathMessage() + "\nwith:" + message, LoggingLevel.INFO);
            e.setDeathMessage(message);
        }else{
            log.log("[VanillaDeathMessageManager]: Interception disabled, skipping...", LoggingLevel.WARNING);
        }

        if (main.data.getConfigurationSection("handles") != null) {
            log.log("Applying handlers [...]", LoggingLevel.INFO);
            for (String key : main.data.getConfigurationSection("handles").getKeys(false)) {
                log.log("Checking handling conditions for " + key + " [...]", LoggingLevel.INFO);
                switch (HandleTrigger.valueOf(key)){
                    case ITEM_IN_HAND:
                        for (String _case: main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)) {
                            if (p.getInventory().getItemInMainHand().getType() == Material.matchMaterial(_case)){
                                switch (HandleAction.valueOf(main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".action"))){
                                    case RUN_COMMAND:
                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".actionValue"));
                                        break;
                                    case BROADCAST_MESSAGE:
                                        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".actionValue")));
                                        break;
                                }
                            }
                        }
                        break;
                    case ARMOR_HELMET:
                        if (p.getInventory().getHelmet() != null) {
                            for (String _case : main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)) {
                                if (p.getInventory().getHelmet().getType() == Material.valueOf(main.data.getObject("handles." + HandleTrigger.valueOf(key) + "." + _case + ".conditionParameter", Material.class).toString())) {
                                    switch (HandleAction.valueOf(main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".action"))) {
                                        case RUN_COMMAND:
                                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".actionValue"));
                                            break;
                                        case BROADCAST_MESSAGE:
                                            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".actionValue")));
                                            break;
                                    }
                                }
                            }
                        }
                        break;
                    case ARMOR_CHESTPLATE:
                        if (p.getInventory().getChestplate() != null) {
                            for (String _case : main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)) {
                                if (p.getInventory().getChestplate().getType() == Material.valueOf(main.data.getObject("handles." + HandleTrigger.valueOf(key) + "." + _case + ".conditionParameter", Material.class).toString())) {
                                    switch (HandleAction.valueOf(main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".action"))) {
                                        case RUN_COMMAND:
                                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".actionValue"));
                                            break;
                                        case BROADCAST_MESSAGE:
                                            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".actionValue")));
                                            break;
                                    }
                                }
                            }
                        }
                        break;
                    case ARMOR_LEGGINS:
                        if (p.getInventory().getLeggings() != null) {
                            for (String _case : main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)) {
                                if (p.getInventory().getLeggings().getType() == Material.valueOf(main.data.getObject("handles." + HandleTrigger.valueOf(key) + "." + _case + ".conditionParameter", Material.class).toString())) {
                                    switch (HandleAction.valueOf(main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".action"))) {
                                        case RUN_COMMAND:
                                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".actionValue"));
                                            break;
                                        case BROADCAST_MESSAGE:
                                            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".actionValue")));
                                            break;
                                    }
                                }
                            }
                        }
                        break;
                    case ARMOR_BOOTS:
                        if (p.getInventory().getBoots() != null) {
                            for (String _case : main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)) {
                                if (p.getInventory().getBoots().getType() == Material.valueOf(main.data.getObject("handles." + HandleTrigger.valueOf(key) + "." + _case + ".conditionParameter", Material.class).toString())) {
                                    switch (HandleAction.valueOf(main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".action"))) {
                                        case RUN_COMMAND:
                                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".actionValue"));
                                            break;
                                        case BROADCAST_MESSAGE:
                                            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + _case + ".actionValue")));
                                            break;
                                    }
                                }
                            }
                        }
                        break;
                    case HAS_ITEM_IN_INVENTORY:
                            for (String item : main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)) {
                                main.getLogger().info(Material.matchMaterial(main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + item + ".conditionParameter").toUpperCase(Locale.ROOT)).toString());
                                if (p.getInventory().contains(new ItemStack(Material.matchMaterial(main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + item + ".conditionParameter"))))) {
                                    switch (HandleAction.valueOf(main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + item + ".action"))) {
                                        case RUN_COMMAND:
                                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + item + ".actionValue"));
                                            break;
                                        case BROADCAST_MESSAGE:
                                            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + item + ".actionValue")));
                                            break;
                                    }
                                }
                            }
                        break;
                    case PLAYER_LEVEL_EQUALTO:
                        for (String condition: main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)){
                            if (p.getLevel() == Integer.parseInt(condition)){
                                switch (HandleAction.valueOf(main.data.get("handles." + HandleTrigger.valueOf(key) + "." + condition + ".action").toString())){
                                    case RUN_COMMAND:
                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + condition + ".actionValue"));
                                        break;
                                    case BROADCAST_MESSAGE:
                                        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + condition + ".actionValue")));
                                        break;
                                }
                            }
                        }
                        break;
                    case PLAYER_LEVEL_OVER:
                        for (String condition: main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)){
                            if (p.getLevel() > Integer.parseInt(condition)){
                                switch (HandleAction.valueOf(main.data.get("handles." + HandleTrigger.valueOf(key) + "." + condition + ".action").toString())){
                                    case RUN_COMMAND:
                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + condition + ".actionValue"));
                                        break;
                                    case BROADCAST_MESSAGE:
                                        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + condition + ".actionValue")));
                                        break;
                                }
                            }
                        }
                        break;

                    case PLAYER_LEVEL_UNDER:
                        for (String condition: main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)){
                            if (p.getLevel() < Integer.parseInt(condition)){
                                switch (HandleAction.valueOf(main.data.get("handles." + HandleTrigger.valueOf(key) + "." + condition + ".action").toString())){
                                    case RUN_COMMAND:
                                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + condition + ".actionValue"));
                                        break;
                                    case BROADCAST_MESSAGE:
                                        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', main.data.getString("handles." + HandleTrigger.valueOf(key) + "." + condition + ".actionValue")));
                                        break;
                                }
                            }
                        }
                }
            }
            log.log("Handles successfully applied [/]", LoggingLevel.INFO);
        }
    }

}

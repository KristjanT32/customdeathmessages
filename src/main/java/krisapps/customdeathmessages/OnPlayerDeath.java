package krisapps.customdeathmessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class OnPlayerDeath implements Listener {

    CustomDeathMessages main;
    HandleManager hm;
    public OnPlayerDeath(CustomDeathMessages main){
        this.main = main;
        hm = new HandleManager(main);
    }


    @EventHandler
    public void OnPlayerDied(PlayerDeathEvent e){
        Player p = e.getEntity();

        if (main.data.getConfigurationSection("handles") != null) {
            main.getLogger().info("Apply handles.");
            for (String key : main.data.getConfigurationSection("handles").getKeys(false)) {
                main.getLogger().info("Check conditions for: " + key);
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
                        for (String _case: main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)) {
                            if (p.getInventory().getHelmet().getType() == Material.valueOf(main.data.getObject("handles." + HandleTrigger.valueOf(key) + "." + _case + ".conditionParameter", ItemStack.class).getType().toString())){
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
                    case ARMOR_CHESTPLATE:
                        for (String _case: main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)) {
                            if (p.getInventory().getChestplate().getType() == Material.valueOf(main.data.getObject("handles." + HandleTrigger.valueOf(key) + "." + _case + ".conditionParameter", ItemStack.class).getType().toString())){
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
                    case ARMOR_LEGGINS:
                        for (String _case: main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)) {
                            if (p.getInventory().getLeggings().getType() == Material.valueOf(main.data.getObject("handles." + HandleTrigger.valueOf(key) + "." + _case + ".conditionParameter", ItemStack.class).getType().toString())){
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
                    case ARMOR_BOOTS:
                        for (String _case: main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)) {
                            if (p.getInventory().getBoots().getType() == Material.valueOf(main.data.getObject("handles." + HandleTrigger.valueOf(key) + "." + _case + ".conditionParameter", ItemStack.class).getType().toString())){
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
                    case HAS_ITEM_IN_INVENTORY:
                        for (String item: main.data.getConfigurationSection("handles." + HandleTrigger.valueOf(key)).getKeys(false)){
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
        }
    }

}

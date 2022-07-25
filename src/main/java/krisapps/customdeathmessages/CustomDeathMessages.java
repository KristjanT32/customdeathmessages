package krisapps.customdeathmessages;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.text.View;
import java.io.File;
import java.io.IOException;

public final class CustomDeathMessages extends JavaPlugin {

     File dataFile = new File(getDataFolder(), "custom_messages.yml");
     File configFile = new File(getDataFolder(), "config.yml");

     FileConfiguration data;
     FileConfiguration config;

    @Override
    public void onEnable() {
        startup();
    }

    void startup() {
        //Check files
        data = new YamlConfiguration();
        config = new YamlConfiguration();

        getLogger().info("Preparing plugin files...");
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
            if (!dataFile.getParentFile().exists() || !dataFile.exists()){
                try {
                    dataFile.createNewFile();
                    data.save(dataFile);
                    getLogger().info("Successfully saved data file at: " + data.getCurrentPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                getLogger().info("Data File [OK]");
            }

            if (!configFile.getParentFile().exists() || !configFile.exists()){
                getLogger().warning("No configuration file was found, generating a new one.");
                try {
                    configFile.createNewFile();
                    config.save(configFile);
                }catch (IOException e){
                    getLogger().warning("Failed to create config file. Here's some info: \n");
                    e.printStackTrace();
                }
                getLogger().info("Successfully saved config file at: " + config.getCurrentPath());
                saveResource("config.yml", true);
            }else{
                getLogger().info("Config File [OK]");
            }
            try {
                data.load(dataFile);
                config.load(configFile);
            }catch (IOException | InvalidConfigurationException e){
                getLogger().warning("Failed to complete file initialization. Here's some info: \n");
                e.printStackTrace();
            }
        }
        //Register Commands

        //Commands
        getCommand("addhandle").setExecutor(new AddHandle(this));
        getCommand("removehandle").setExecutor(new RemoveHandle(this));
        getCommand("viewhandle").setExecutor(new ViewHandle(this));


        //Tab Completion
        getCommand("addhandle").setTabCompleter(new AddHandleTab());
        getCommand("removehandle").setTabCompleter(new ViewHandleTab(this));
        getCommand("viewhandle").setTabCompleter(new ViewHandleTab(this));

        Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerDeath(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
    }
}

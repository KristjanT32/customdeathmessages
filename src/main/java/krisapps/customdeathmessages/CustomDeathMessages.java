package krisapps.customdeathmessages;
import krisapps.customdeathmessages.logging.Logger;
import krisapps.customdeathmessages.logging.LoggingLevel;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.text.View;
import java.io.File;
import java.io.IOException;

public class CustomDeathMessages extends JavaPlugin {

     public File dataFile = new File(getDataFolder(), "custom_messages.yml");
     File configFile = new File(getDataFolder(), "config.yml");
     File deathMessagesFile = new File(getDataFolder(), "death_messages.yml");

     public FileConfiguration data;
     public FileConfiguration config;
     public FileConfiguration deathMessages;

     Logger log = new Logger(this);

     private static CustomDeathMessages instance;

    @Override
    public void onEnable() {
        startup();
    }

    void startup() {
        //Check files
        data = new YamlConfiguration();
        config = new YamlConfiguration();
        deathMessages = new YamlConfiguration();

        log.log("Preparing plugin files [...]", LoggingLevel.INFO);
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }
            if (!dataFile.getParentFile().exists() || !dataFile.exists()){
                try {
                    dataFile.createNewFile();
                    data.save(dataFile);
                    log.log("Created new data file [" + dataFile.getPath() + "]", LoggingLevel.INFO);
                } catch (IOException e) {
                    log.log("An error was encountered while creating the data file [!]", LoggingLevel.ERROR);
                    log.log(e.getMessage(), LoggingLevel.ERROR);
                    e.printStackTrace();
                }
            }else{
                log.log("Data file already exists, skipping step [-]", LoggingLevel.INFO);
            }

            if (!deathMessagesFile.getParentFile().exists() || !deathMessagesFile.exists()){
                try {
                    deathMessagesFile.createNewFile();
                    deathMessages.save(deathMessagesFile);

                    saveResource("death_messages.yml", true);
                    log.log("Created new death messages file [" + deathMessagesFile.getPath() + "]", LoggingLevel.INFO);
                } catch (IOException e) {
                    log.log("An error was encountered while creating the death messages file [!]", LoggingLevel.ERROR);
                    log.log(e.getMessage(), LoggingLevel.ERROR);
                    e.printStackTrace();
                }
            }else{
                log.log("'death_messages.yml' already exists, skipping step [-]", LoggingLevel.INFO);
            }

            if (!configFile.getParentFile().exists() || !configFile.exists()){
                log.log("Configuration file not found, generating a new one...", LoggingLevel.WARNING);
                try {
                    configFile.createNewFile();
                    config.save(configFile);
                }catch (IOException e){
                    log.log("An error occurred while creating the configuration file.", LoggingLevel.ERROR);
                    log.log(e.getMessage(), LoggingLevel.ERROR);
                    e.printStackTrace();
                }
                saveResource("config.yml", true);
                log.log("Created new configuration file [" + configFile.getPath() + "]", LoggingLevel.INFO);
            }else{
                log.log("'config.yml' already exists, skipping step [-]", LoggingLevel.INFO);

            }
            try {
                data.load(dataFile);
                config.load(configFile);
                deathMessages.load(deathMessagesFile);
                log.log("All files loaded successfully [/]", LoggingLevel.INFO);
            }catch (IOException | InvalidConfigurationException e){
                log.log("An error was encountered while loading the files [!]", LoggingLevel.FATAL);
                log.log(e.getMessage(), LoggingLevel.FATAL);
                e.printStackTrace();
            }
        getLogger().info("Loading process finished [/]");
        getLogger().info("Registering components [...]");
        //Register Commands

        //Commands
        getCommand("addhandle").setExecutor(new AddHandle(this));
        getCommand("removehandle").setExecutor(new RemoveHandle(this));
        getCommand("viewhandle").setExecutor(new ViewHandle(this));
        getCommand("refpf").setExecutor(new RefreshPluginFiles(this));


        //Tab Completion
        getCommand("addhandle").setTabCompleter(new AddHandleTab());
        getCommand("removehandle").setTabCompleter(new ViewHandleTab(this));
        getCommand("viewhandle").setTabCompleter(new ViewHandleTab(this));

        Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerDeath(this), this);

        getLogger().info("Registering complete [/]");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
        instance = null;
    }

    public boolean refreshDataFiles() {
        log.log("Refreshing plugin data file content [...]", LoggingLevel.WARNING);
        try {
            data.load(dataFile);
            config.load(configFile);
            deathMessages.load(deathMessagesFile);
            log.log("All files have been refreshed successfully [+]", LoggingLevel.INFO);
            return true;
        }catch (IOException | InvalidConfigurationException e){
            log.log("An error was encountered while refreshing the files [!]", LoggingLevel.FATAL);
            log.log(e.getMessage(), LoggingLevel.FATAL);
            e.printStackTrace();
            return false;
        }
    }
}

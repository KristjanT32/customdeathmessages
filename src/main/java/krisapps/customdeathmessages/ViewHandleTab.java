package krisapps.customdeathmessages;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ViewHandleTab implements TabCompleter {

    CustomDeathMessages main;
    public ViewHandleTab(CustomDeathMessages main) {
        this.main = main;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1){
            if (main.data.getConfigurationSection("handles") != null) {
                completions.addAll(main.data.getConfigurationSection("handles").getKeys(false));
            }
        }
        if (args.length == 2){
            if (main.data.getConfigurationSection("handles." + args[0]) != null) {
                completions.addAll(main.data.getConfigurationSection("handles." + args[0]).getKeys(false));
            }
        }


        return completions;
    }
}

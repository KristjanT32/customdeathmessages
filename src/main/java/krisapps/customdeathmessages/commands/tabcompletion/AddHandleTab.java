package krisapps.customdeathmessages.commands.tabcompletion;
import krisapps.customdeathmessages.enums.HandleAction;
import krisapps.customdeathmessages.enums.HandleTrigger;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddHandleTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1){
            for (HandleTrigger value: HandleTrigger.values()){
                completions.add(value.name());
            }
        }
        if (args.length == 2){
            if (!args[0].startsWith("PLAYER_LEVEL")){
                for (Material material: Material.values()){
                    completions.add((material.name()).toLowerCase(Locale.ROOT));
                }
            }else{
                completions.add("<level>");
            }
        }

        if (args.length == 3){
            for (HandleAction value: HandleAction.values()){
                completions.add(value.name());
            }
        }

        return completions;
    }
}

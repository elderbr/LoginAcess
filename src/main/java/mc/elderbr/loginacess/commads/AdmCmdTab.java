package mc.elderbr.loginacess.commads;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdmCmdTab implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("addadm")){
            List<String> listPlayer = new ArrayList<>();
            for(Player player : Bukkit.getServer().getOnlinePlayers()){
                listPlayer.add(player.getName());
            }
            return listPlayer;
        }
        return Arrays.asList();
    }
}

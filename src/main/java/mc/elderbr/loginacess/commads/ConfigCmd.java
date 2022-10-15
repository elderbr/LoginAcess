package mc.elderbr.loginacess.commads;

import mc.elderbr.loginacess.interfaces.Comando;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ConfigCmd implements CommandExecutor, Comando {
    private Player myPlayer;
    private String myCommand;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player){
            myPlayer = player;
            myCommand = command.getName().toLowerCase();

            switch (myCommand){
                case "noItem":
                    noItem();
            }
        }

        return false;
    }

    private void noItem() {
    }
}

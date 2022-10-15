package mc.elderbr.loginacess.commads;

import mc.elderbr.loginacess.controllers.ConfigController;
import mc.elderbr.loginacess.interfaces.Comando;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemCmd implements CommandExecutor, TabCompleter, Comando {
    private Player myPlayer;
    private String name;

    private String myCommand;
    private ConfigController configController;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player){
            myPlayer = player;
            name = getCommand(args);
            myCommand = command.getName().toLowerCase();

            switch (myCommand){
                case "additem":
                    return addItem();
            }
        }

        return false;
    }


    private boolean addItem() {
        configController = new ConfigController();
        try {
            configController.addNotItem(myPlayer, name);
            Msg.Player(myPlayer, "$3O item "+ name+" adicionado com sucesso!!!");
            return true;
        } catch (Exception e) {
            Msg.Player(myPlayer, e.getMessage());
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player) {
            myPlayer = player;
            name = getCommand(args);
        }
        return null;
    }
}

package mc.elderbr.loginacess.commads;

import mc.elderbr.loginacess.controllers.ItemController;
import mc.elderbr.loginacess.interfaces.Comando;
import mc.elderbr.loginacess.interfaces.ItemInterface;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemCmd implements CommandExecutor, TabCompleter, Comando, ItemInterface {
    private Player myPlayer;
    private String name;

    private String myCommand;
    private ItemController itemController;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            myPlayer = player;
            name = getCommand(args);
            myCommand = command.getName().toLowerCase();

            switch (myCommand) {
                case "additem":
                    return addItem();
                case "removeitem":
                    return remove();
            }
        }

        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            myPlayer = player;
            name = getCommand(args);

            if (command.getName().equalsIgnoreCase("additem")) {
                List<String> lista = new ArrayList<>();
                for (String names : ITEM_LISTA) {
                    if (names.contains(name)) {
                        lista.add(names);
                    }
                }
                return lista;
            }
        }
        return null;
    }

    private boolean addItem() {
        itemController = new ItemController();
        try {
            itemController.insert(myPlayer, name);
            Msg.PlayerAll("$f$lO item $a" + name + "$r$f$l foi adicionado como restrito!!!");
            return true;
        } catch (Exception e) {
            Msg.Player(myPlayer, e.getMessage());
        }
        return false;
    }

    private boolean remove(){
        itemController = new ItemController();
        try {
            itemController.delete(myPlayer, name);
            Msg.PlayerAll("$f$lO item $a" + name + "$r$f$l foi removido os itens restritos!!!");
            return true;
        } catch (Exception e) {
            Msg.Player(myPlayer, e.getMessage());
        }
        return false;
    }


}

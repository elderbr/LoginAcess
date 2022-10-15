package mc.elderbr.loginacess.commads;

import mc.elderbr.loginacess.controllers.ConfigController;
import mc.elderbr.loginacess.interfaces.Comando;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ConfigCmd implements CommandExecutor, Comando {
    private Player myPlayer;
    private String myCommand;

    private String itemName;
    private ConfigController configController;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            myPlayer = player;
            myCommand = command.getName().toLowerCase();
            itemName = getCommand(args).toLowerCase();

            switch (myCommand) {
                case "additem":
                    noItem();
                    break;
                case "addadm":
                    addAdm();
                    break;
            }
        }

        return false;
    }

    private void addAdm(){
        configController = new ConfigController();
        try {
            configController.addAdm(myPlayer, itemName);
            Msg.PlayerAll("Novo adm do LoginAcess "+ itemName);
        } catch (Exception e) {
            Msg.Player(myPlayer, e.getMessage());
        }
    }

    private void noItem() {
        configController = new ConfigController();
        try {
            configController.addNotItem(myPlayer, itemName);
            Msg.Player(myPlayer, "$3O item "+ itemName+" adicionado com sucesso!!!");
        } catch (Exception e) {
            Msg.Player(myPlayer, e.getMessage());
        }
    }
}

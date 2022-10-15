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

    private String name;
    private ConfigController configController;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            myPlayer = player;
            myCommand = command.getName().toLowerCase();
            name = getCommand(args).toLowerCase();

            switch (myCommand) {
                case "additem":
                    noItem();
                    break;
                case "addadm":
                    addAdm();
                    break;
                case "removeadm":
                    return removeAdm();
            }
        }

        return false;
    }

    private void addAdm(){
        configController = new ConfigController();
        try {
            configController.addAdm(myPlayer, name);
            Msg.PlayerAll("$bNovo adm do LoginAcess $l"+ name);
        } catch (Exception e) {
            Msg.Player(myPlayer, e.getMessage());
        }
    }
    
    private boolean removeAdm(){
        configController = new ConfigController();
        try {
            configController.removeAdm(myPlayer, name);
            Msg.PlayerAll("$6O jogador "+ name + " deixou de ser Adm do LoginAcess!!!");
            return true;
        } catch (Exception e) {
            Msg.Player(myPlayer, "$c"+ e.getMessage());
        }
        return false;
    }

    private void noItem() {
        configController = new ConfigController();
        try {
            configController.addNotItem(myPlayer, name);
            Msg.Player(myPlayer, "$3O item "+ name+" adicionado com sucesso!!!");
        } catch (Exception e) {
            Msg.Player(myPlayer, e.getMessage());
        }
    }
}

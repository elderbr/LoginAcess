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
            name = getCommand(args);

            switch (myCommand) {
                case "addadm":
                    return addAdm();
                case "removeadm":
                    return removeAdm();
            }
        }

        return false;
    }

    private boolean addAdm(){
        configController = new ConfigController();
        try {
            configController.addAdm(myPlayer, name);
            Msg.PlayerAll("$fNovo adm do LoginAcess $l$e"+ name);
            return true;
        } catch (Exception e) {
            Msg.Player(myPlayer, e.getMessage());
        }
        return false;
    }
    
    private boolean removeAdm(){
        configController = new ConfigController();
        try {
            configController.removeAdm(myPlayer, name);
            Msg.PlayerAll("$fO jogador $c"+ name + "$f deixou de ser Adm do LoginAcess!!!");
            return true;
        } catch (Exception e) {
            Msg.Player(myPlayer, "$c"+ e.getMessage());
        }
        return false;
    }
}

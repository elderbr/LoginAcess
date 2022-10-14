package mc.elderbr.loginacess.commads;

import mc.elderbr.loginacess.controllers.EsperaController;
import mc.elderbr.loginacess.exceptions.JogadorException;
import mc.elderbr.loginacess.interfaces.Comando;
import mc.elderbr.loginacess.interfaces.JogadorInterface;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class EsperaCmd implements CommandExecutor, Comando, JogadorInterface {

    private Player myPlayer;
    private String nome;
    private String myCommand;
    private EsperaController esperaController;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            myPlayer = player;
            nome = getCommand(args);
            myCommand = command.getName().toLowerCase();

            switch (myCommand) {
                case "limparespera":
                    return limparEspera();
            }
        }
        return false;
    }

    private boolean limparEspera() {
        esperaController = new EsperaController();
        try {
            esperaController.delete(myPlayer);
            return true;
        } catch (IOException e) {
            Msg.Player(myPlayer, "O arquivo não foi localizado!!!");
        } catch (JogadorException e) {
            Msg.Player(myPlayer, e.toString());
        }
        return false;
    }
}

package mc.elderbr.loginacess.commads;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.controllers.AmigoController;
import mc.elderbr.loginacess.exceptions.JogadorException;
import mc.elderbr.loginacess.interfaces.Comando;
import mc.elderbr.loginacess.interfaces.JogadorInterface;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AmigoCmd implements CommandExecutor, Comando, JogadorInterface {

    private String nome;
    private String myCommand;

    private Player myPlayer;
    private String[] myArgs;
    private AmigoController amigoController;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        nome = getCommand(args);
        myArgs = args;
        myCommand = command.getName().toLowerCase();

        if (sender instanceof Player player) {

            myPlayer = player;

            cadastro();// Se for amigo e não for cadastrado tem que fazer

            login();// Se for amigo precisa fazer o login

            add();// Adicionar novo amigo

            remover();// Remover amigo
        }
        return false;
    }

    private boolean cadastro() {
        if (myCommand.equalsIgnoreCase("cadastro")) {
            amigoController = new AmigoController();
            try {
                amigoController.cadastro(myPlayer, myArgs);
                Msg.Player(myPlayer, "Cadastro bem sucessedido!!!");
                return true;
            } catch (JogadorException e) {
                Msg.Player(myPlayer, "$l" + e);
            }
        }
        return false;
    }

    public boolean login() {
        if (myCommand.equalsIgnoreCase("login")) {
            amigoController = new AmigoController();
            try {
                amigoController.login(myPlayer, nome);
                Msg.Player(myPlayer, "$l$3Login bem sucessedido!!!");
                return true;
            } catch (JogadorException e) {
                Msg.Player(myPlayer, "$l" + e);
            }
        }
        return false;
    }

    public boolean add() {
        if (myCommand.equals("addamigo")) {
            amigoController = new AmigoController();
            try {
                amigoController.insert(myPlayer, nome);
                Msg.PlayerAll(String.format("O jogador %s agora é um novo amigo!!!", nome));
                return true;
            } catch (JogadorException e) {
                Msg.Player(myPlayer, e.toString());
            }
        }
        return false;
    }

    public boolean remover() {
        if (myCommand.equals("removeamigo")) {
            try {
                amigoController = new AmigoController();
                amigoController.remover(myPlayer, nome);
                Msg.PlayerAll(String.format("O jogador %s agora não é mais amigo, removido por %s!!!", nome, myPlayer.getName()));
                return true;
            } catch (JogadorException e) {
                Msg.Player(myPlayer, e.toString());
            }
        }
        return false;
    }
}

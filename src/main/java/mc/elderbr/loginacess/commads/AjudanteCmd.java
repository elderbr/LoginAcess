package mc.elderbr.loginacess.commads;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.controllers.AjudanteController;
import mc.elderbr.loginacess.controllers.AmigoController;
import mc.elderbr.loginacess.exceptions.JogadorException;
import mc.elderbr.loginacess.interfaces.Comando;
import mc.elderbr.loginacess.interfaces.JogadorInterface;
import mc.elderbr.loginacess.model.Ajudante;
import mc.elderbr.loginacess.model.Amigo;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class AjudanteCmd implements CommandExecutor, JogadorInterface, Comando {

    private Player player;
    private String comands;

    private Amigo amigo;
    private Ajudante ajudante;
    private AjudanteController ajudanteController;

    private AmigoController amigoController;
    private String nome;
    private Jogador jogador;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        nome = getCommand(args);
        comands = command.getName().toLowerCase();

        amigoController = new AmigoController();

        if (sender instanceof Player player) {
            this.player = player;
            switch (comands) {
                case "addseguidor":
                    return addSeguidor();
                case "removeseguidor":
                    return removeSeguidor();
                case "removeseguidorAll":
                    return removeAll();
            }
        }
        return false;
    }


    private boolean addSeguidor() {
        try {
            amigoController.addAjudante(player, nome);
            Msg.Player(player, String.format("O ajudante %s foi adicionado com sucesso!", nome));
            return true;
        } catch (JogadorException e) {
            Msg.Player(player, e.toString());
        }
        return false;
    }

    private boolean removeSeguidor() {
        ajudanteController = new AjudanteController();
        try {
            ajudanteController.remove(player, nome);
            Msg.Player(player, String.format("O seguidor %s foi removido com sucesso!!!", nome));
            return true;
        } catch (JogadorException e) {
            Msg.Player(player, "Erro ao remover o seguidor!!!");
        }
        return false;
    }

    private boolean removeAll() {
        try {
            AjudanteController.RemoveAll(player);
            Msg.PlayerAll("Todos os ajudantes foram removidos por " + player.getName() + "!");
            return true;
        } catch (JogadorException e) {
            Msg.Player(player, e.toString());
        }
        return false;
    }

}

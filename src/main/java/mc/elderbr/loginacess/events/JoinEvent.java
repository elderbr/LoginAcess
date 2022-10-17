package mc.elderbr.loginacess.events;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.controllers.AjudanteController;
import mc.elderbr.loginacess.controllers.AmigoController;
import mc.elderbr.loginacess.controllers.EsperaController;
import mc.elderbr.loginacess.exceptions.JogadorException;
import mc.elderbr.loginacess.interfaces.JogadorInterface;
import mc.elderbr.loginacess.model.Ajudante;
import mc.elderbr.loginacess.model.Amigo;
import mc.elderbr.loginacess.model.Espera;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener, JogadorInterface {

    private Jogador jogador;
    private Espera espera;
    private EsperaController esperaController = new EsperaController();
    private Player player;
    private AmigoController amigoController = new AmigoController();
    private Amigo amigo;

    private Ajudante ajudante;
    private AjudanteController ajudanteController = new AjudanteController();

    @EventHandler
    public void preLogin(AsyncPlayerPreLoginEvent event) {
        jogador = newJogador(event);
        try {
            if(esperaController.insert(jogador)){
                event.setKickMessage("Ops, você não está na lista de amigo!!!");
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_FULL);
            }
        } catch (JogadorException e) {
            Msg.Erro(e.getMessage(), "preLogin(AsyncPlayerPreLoginEvent event)", getClass(), e);
            event.setKickMessage("Ops, você não está na lista de amigo, fale com o administrador!!!");
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_FULL);
        }
    }

    @EventHandler
    public void joinPlayer(PlayerJoinEvent event) {
        event.setJoinMessage("");
        player = event.getPlayer();
        try {
            amigoController.join(player);
        } catch (JogadorException e) {
            Msg.Erro("Erro ao verificar o jogador que entrou no servidor!!!", "joinPlayer(PlayerJoinEvent event)", getClass(), e);
            Msg.Player(player, e.toString());
        }

    }

}

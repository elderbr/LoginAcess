package mc.elderbr.loginacess.events;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.controllers.AmigoController;
import mc.elderbr.loginacess.exceptions.JogadorException;
import mc.elderbr.loginacess.interfaces.JogadorInterface;
import mc.elderbr.loginacess.model.Amigo;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener, JogadorInterface {

    private Player player;
    private Jogador jogador;
    private AmigoController amigoController;

    @EventHandler
    public void movePlayer(PlayerMoveEvent event){
        player = event.getPlayer();
        amigoController = new AmigoController();
        try {
            amigoController.move(player);
        } catch (JogadorException e) {
            Msg.Player(player, "$4 "+e);
            player.teleport(player.getLocation());
        }


    }

}

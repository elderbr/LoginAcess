package mc.elderbr.loginacess.events;

import mc.elderbr.loginacess.controllers.AjudanteController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    private Player player;
    private AjudanteController ajudanteController;

    @EventHandler
    public void quitPlayer(PlayerQuitEvent event){
        player = event.getPlayer();
        ajudanteController = new AjudanteController();
        ajudanteController.quit(player);
    }

}

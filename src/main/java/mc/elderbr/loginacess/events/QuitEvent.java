package mc.elderbr.loginacess.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    private Player player;

    public void quitPlayer(PlayerQuitEvent event){
        player = event.getPlayer();
    }

}

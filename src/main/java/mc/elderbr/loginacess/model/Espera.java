package mc.elderbr.loginacess.model;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.exceptions.JogadorException;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Espera extends Jogador {

    public Espera() {
    }

    public Espera(Player player) {
        super(player);
    }

    public Espera(Jogador jogador) {
        super(jogador);
    }

    public Espera(AsyncPlayerPreLoginEvent event) {
        super(event);
    }

    public Espera(PlayerJoinEvent event) {
        super(event);
    }
}

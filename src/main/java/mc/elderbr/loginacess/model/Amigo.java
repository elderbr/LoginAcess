package mc.elderbr.loginacess.model;

import mc.elderbr.loginacess.abstracts.Jogador;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class Amigo extends Jogador {

    private List<Ajudante> listAjudante = new ArrayList<>();

    public Amigo() {
    }

    public Amigo(Player player) {
        super(player);
    }

    public Amigo(Jogador jogador) {
        super(jogador);
    }

    public Amigo(AsyncPlayerPreLoginEvent event) {
        super(event);
    }

    public Amigo(PlayerJoinEvent event) {
        super(event);
    }

    public List<Ajudante> getListAjudante() {
        return listAjudante;
    }

    public Amigo addAjudante(Ajudante ajudante) {
        listAjudante.add(ajudante);
        return this;
    }

    public Amigo removeAjudante(Ajudante ajudante){
        listAjudante.remove(ajudante);
        return this;
    }
}

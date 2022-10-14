package mc.elderbr.loginacess.model;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.exceptions.JogadorException;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class Ajudante extends Jogador {

    private Amigo amigo;

    public Ajudante() {
    }

    public Ajudante(Player player) {
        super(player);
    }

    public Ajudante(Jogador jogador) {
        super(jogador);
    }

    public Ajudante(AsyncPlayerPreLoginEvent event) {
        super(event);
    }

    public Ajudante(PlayerJoinEvent event) {
        super(event);
    }

    public Amigo getAmigo() {
        return amigo;
    }

    public void setAmigo(Amigo amigo) throws JogadorException {
        if (amigo == null) {
            throw new JogadorException("O ajudante não tem amigo!!!");
        }
        this.amigo = amigo;
    }

    public void setAmigo(String nome) throws JogadorException {
        if (JOGADOR_MAP.get(nome) instanceof Amigo player) {
            amigo = player;
        }else{
            throw new JogadorException("O ajudante "+ this.getNome() +" não tem amigo!!!");
        }
    }
}

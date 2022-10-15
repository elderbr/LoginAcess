package mc.elderbr.loginacess.interfaces;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.exceptions.JogadorException;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface JogadorInterface extends Global{

    Map<String, Jogador> JOGADOR_MAP = new HashMap<>();
    List<String> LISTA_AMIGO = new ArrayList<>();
    List<String> LISTA_AJUDANTE = new ArrayList<>();
    List<String> LISTA_ESPERA = new ArrayList<>();

    default Jogador newJogador(Player player) {
        return new Jogador() {
            @Override
            public String getNome() {
                return player.getName();
            }

            @Override
            public String getUuid() {
                return player.getUniqueId().toString();
            }

            @Override
            public String getIp() {
                return player.getAddress().getHostName();
            }
        };
    }

    default Jogador newJogador(AsyncPlayerPreLoginEvent event) {
        return new Jogador() {
            @Override
            public String getNome() {
                return event.getName();
            }

            @Override
            public String getUuid() {
                return event.getUniqueId().toString();
            }

            @Override
            public String getIp() {
                return event.getAddress().getHostName();
            }
        };
    }

    default void removeMap(String nome) throws JogadorException {
        if (nome == null || nome.isEmpty()) {
            throw new JogadorException("Digite o nome do jogador!!!");
        }
        Jogador jogador = JOGADOR_MAP.get(nome);
        if (jogador == null || !jogador.getType().equals("Amigo")) {
            throw new JogadorException(String.format("O jogador %s não existe!!!", nome));
        }
        JOGADOR_MAP.remove(nome);
    }
}

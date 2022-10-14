package mc.elderbr.loginacess.controllers;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.dao.JogadorDao;
import mc.elderbr.loginacess.exceptions.JogadorException;
import mc.elderbr.loginacess.model.Ajudante;
import mc.elderbr.loginacess.model.Amigo;
import org.bukkit.entity.Player;

import static mc.elderbr.loginacess.interfaces.JogadorInterface.JOGADOR_MAP;
import static mc.elderbr.loginacess.interfaces.JogadorInterface.LISTA_AJUDANTE;

public class AjudanteController {

    private Ajudante ajudante;
    private JogadorDao jogadorDao;

    public void join(Player player) throws JogadorException {
        if (JOGADOR_MAP.get(player.getName()) instanceof Ajudante ajudante) {
            if (ajudante.getAmigo() == null) {
                throw new JogadorException("Ops, tem algo errado, cadê o seu amigo");
            }
        }
    }

    public static void SelectAll() {
        new JogadorDao(new Ajudante()).selectAll();
        LISTA_AJUDANTE.clear();
        for (Jogador jogador : JOGADOR_MAP.values()) {
            if (jogador instanceof Ajudante) {
                LISTA_AJUDANTE.add(jogador.getNome());
            }
        }
    }

    public void remove(Player player, String nome) throws JogadorException {

        if (!(JOGADOR_MAP.get(player.getName()) instanceof Amigo amigo)) {
            throw new JogadorException("Você não tem permissão!!!");
        }
        if (nome == null || nome.isEmpty()) {
            throw new JogadorException("O nome do ajudante não poder ser vazio!!!");
        }
        if (JOGADOR_MAP.get(nome) == null) {
            throw new JogadorException("O ajudante " + nome + " não existe!!!");
        }
        if (JOGADOR_MAP.get(nome) instanceof Ajudante ajudante) {
            jogadorDao = new JogadorDao(ajudante);
            jogadorDao.remove();
            LISTA_AJUDANTE.remove(nome);
        } else {
            throw new JogadorException("O jogador " + nome + " não é um ajudante!!!");
        }
    }

    public static void Remove(String nome) throws JogadorException {
            Ajudante ajudante = new Ajudante();
            ajudante.setNome(nome);
            new JogadorDao(ajudante).remove();
    }
}

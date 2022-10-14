package mc.elderbr.loginacess.controllers;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.dao.JogadorDao;
import mc.elderbr.loginacess.exceptions.JogadorException;
import mc.elderbr.loginacess.interfaces.JogadorInterface;
import mc.elderbr.loginacess.model.Ajudante;
import mc.elderbr.loginacess.model.Amigo;
import mc.elderbr.loginacess.model.Espera;
import mc.elderbr.loginacess.utils.Msg;

public class EsperaController implements JogadorInterface {

    private Jogador jogador;
    private JogadorDao jogadorDao;

    public EsperaController() {
        jogadorDao = new JogadorDao(new Espera());
        jogadorDao.selectAll();
    }

    public boolean insert(Jogador jogador) throws JogadorException {
        if(jogador == null){
            throw new JogadorException("Erro ao adicionar o jogador na fila de espera!!!");
        }
        this.jogador = JOGADOR_MAP.get(jogador.getNome());
        if(this.jogador == null || !(this.jogador instanceof Amigo) && !(this.jogador instanceof Ajudante)){
            jogadorDao = new JogadorDao(new Espera(jogador));
            LISTA_ESPERA.add(jogador.getNome());
            return jogadorDao.insert();
        }
        return false;
    }
    public static void SelectAll() {
        new JogadorDao(new Espera()).selectAll();
        LISTA_ESPERA.clear();
        for(String nome : JOGADOR_MAP.keySet()){
            if(JOGADOR_MAP.get(nome) instanceof Espera) {
                LISTA_ESPERA.add(nome);
            }
        }
    }

    public static void Remove(String nome) throws JogadorException {
        if (nome == null || nome.isEmpty()){
            throw new JogadorException("Digite o nome do jogador!!!");
        }
        if (JOGADOR_MAP.get(nome) != null) {
            Espera espera = new Espera(JOGADOR_MAP.get(nome));
            LISTA_ESPERA.remove(espera.getNome());
            new JogadorDao(espera).remove();
        }
    }
}

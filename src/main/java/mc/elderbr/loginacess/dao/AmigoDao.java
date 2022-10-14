package mc.elderbr.loginacess.dao;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.interfaces.Global;

public class AmigoDao extends JogadorDao implements Global {

    public AmigoDao(Jogador jogador) {
        super(jogador);
    }

}

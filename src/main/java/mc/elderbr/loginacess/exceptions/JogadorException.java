package mc.elderbr.loginacess.exceptions;

import mc.elderbr.loginacess.abstracts.Jogador;

public class JogadorException extends Exception {

    private final long serialVersionUID = 1L;

    private Jogador jogador;
    private String msg = null;

    public JogadorException(String message) {
        super(message);
        msg = message;
    }

    public JogadorException(Jogador jogador) {
        this.jogador = jogador;
        if (jogador == null) {
            msg = "O jogador não existe!!!";
        }else
        if (jogador.getNome() == null || jogador.getNome().isEmpty()) {
            msg = "Digite o nome do jogador!!!";
        }else
        if (jogador.getUuid() == null || jogador.getUuid().isEmpty()) {
            msg = "O UUID não foi localizado!!!";
        }else
        if (jogador.getIp() == null || jogador.getIp().isEmpty()) {
            msg = "O IP não foi localizado!!!";
        }
    }

    @Override
    public String getMessage(){
        return msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}

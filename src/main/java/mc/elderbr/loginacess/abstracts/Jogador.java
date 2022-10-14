package mc.elderbr.loginacess.abstracts;

import mc.elderbr.loginacess.exceptions.JogadorException;
import mc.elderbr.loginacess.interfaces.JogadorInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public abstract class Jogador implements JogadorInterface {

    private String nome;
    private String uuid;
    private String ip;
    private String senha;
    private boolean move;

    public Jogador() {
    }

    public Jogador(Player player) {
        nome = player.getName();
        uuid = player.getUniqueId().toString();
        ip = player.getAddress().getHostName();
    }

    public Jogador(Jogador jogador){
        nome = jogador.getNome();
        uuid = jogador.getUuid();
        ip = jogador.getIp();
        senha = jogador.getSenha();
    }

    public Jogador(AsyncPlayerPreLoginEvent event) {
        nome = event.getName();
        uuid = event.getUniqueId().toString();
        ip = event.getAddress().getHostName();
    }

    public Jogador(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        nome = player.getName();
        uuid = player.getUniqueId().toString();
        ip = player.getAddress().getHostName();
    }

    public String getNome() {
        return nome;
    }

    public Jogador setNome(String nome) throws JogadorException {
        if (nome == null || nome.isEmpty()) {
            throw new JogadorException("O nome do jogador não pode ser vazio!!!");
        }
        this.nome = nome;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public Jogador setUuid(String uuid) throws JogadorException {
        if (uuid == null || uuid.isEmpty()) {
            throw new JogadorException("O UUID do jogador não foi reconhecido!!!");
        }
        this.uuid = uuid;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public Jogador setIp(String ip) throws JogadorException {
        if (ip == null || ip.isEmpty()) {
            throw new JogadorException("O ip do jogador não foi reconhecido!!!");
        }
        this.ip = ip;
        return this;
    }

    public Jogador setIp(Player player) throws JogadorException {
        ip = player.getAddress().getHostName();
        if (ip == null || ip.isEmpty()) {
            throw new JogadorException("O ip do jogador não foi reconhecido!!!");
        }
        this.ip = ip;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Jogador setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getType() {
        return getClass().getSimpleName();
    }

    public String toFile() {
        return getType().concat(".yml");
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", uuid='" + uuid + '\'' +
                ", ip='" + ip + '\'' +
                ", senha='" + senha + '\'' +
                ", isMove='"+move+'\''+
                ", type='"+ getType() +'}';
    }
}

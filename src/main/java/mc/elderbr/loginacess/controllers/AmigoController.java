package mc.elderbr.loginacess.controllers;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.dao.AmigoDao;
import mc.elderbr.loginacess.dao.JogadorDao;
import mc.elderbr.loginacess.exceptions.JogadorException;
import mc.elderbr.loginacess.interfaces.JogadorInterface;
import mc.elderbr.loginacess.model.Ajudante;
import mc.elderbr.loginacess.model.Amigo;
import mc.elderbr.loginacess.model.Espera;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AmigoController implements JogadorInterface {


    private AmigoDao amigoDao;
    private Ajudante ajudante;
    private Jogador jogador;
    private JogadorException exception;
    private Amigo amigo;
    private JogadorDao jogadorDao;


    public AmigoController() {
        amigoDao = new AmigoDao(new Amigo());
    }

    public boolean insert(Jogador jogador) throws JogadorException {
        JogadorException jogExc = new JogadorException(jogador);
        if (jogExc.toString() == null) {
            this.jogador = new Amigo(jogador);
            amigoDao = new AmigoDao(this.jogador);
            amigoDao.insert();
        }
        return false;
    }

    public Jogador select(String nome) throws JogadorException {
        if (nome == null || nome.isEmpty()) {
            throw new JogadorException("Digite o nome do jogador!!!");
        }
        jogador = new Amigo();
        amigoDao = new AmigoDao(jogador);
        jogador = amigoDao.select(nome);
        if (jogador == null) {
            throw new JogadorException("O jogador não existe!!!");
        }
        return jogador;
    }

    public static void SelectAll() {
        new AmigoDao(new Amigo()).selectAll();
        LISTA_AMIGO.clear();
        for (String nome : JOGADOR_MAP.keySet()) {
            Jogador jogador = JOGADOR_MAP.get(nome);
            if (jogador instanceof Amigo) {
                LISTA_AMIGO.add(nome);
            }
        }
    }

    public void update(Jogador jogador) throws JogadorException {
        exception = new JogadorException(jogador);
        if (jogador instanceof Amigo) {
            jogador.setMove(true);
            amigoDao = new AmigoDao(jogador);
            if (!amigoDao.update()) {
                throw new JogadorException("Erro ao adicionar amigo!!!");
            }
        }
    }

    public void join(Player player) throws JogadorException {
        if (JOGADOR_MAP.get(player.getName()) instanceof Amigo jogador) {
            if (jogador.getSenha() == null || jogador.getSenha().isEmpty()) {
                throw new JogadorException("Faça o cadastro!!!");
            }
            if (!jogador.getIp().equals(player.getAddress().getHostName())) {
                throw new JogadorException("Faça o login!!!");
            }
            if(!jogador.getUuid().equals(player.getUniqueId().toString())){
                throw new JogadorException("Tem algo errado com sua conta faça o login!!!");
            }
            // DEIXAR OPCIONAL NO CONFIG
            jogador.setMove(true);
            JOGADOR_MAP.put(jogador.getNome(), jogador);
        }
    }

    public void move(Player player) throws JogadorException {
        if (JOGADOR_MAP.get(player.getName()) instanceof Amigo amigo) {
            if (amigo.getSenha() == null || amigo.getSenha().isEmpty()) {
                throw new JogadorException("Faça o cadastro!!!");
            }
            if (!amigo.isMove()) {
                throw new JogadorException("Faça o login!!!");
            }
        }
    }

    public void addAjudante(Player player, String nome) throws JogadorException {

        if (nome == null || nome.isEmpty()) {
            throw new JogadorException("$cDigite o nome do ajudante!!!");
        }

        if (JOGADOR_MAP.get(player.getName()) instanceof Amigo amigo) {
            if (JOGADOR_MAP.get(nome) instanceof Espera espera) {



                // Adicionando ajudante
                ajudante = new Ajudante(espera);
                ajudante.setAmigo(amigo);

                // Removendo o jogador da espera
                EsperaController.Remove(ajudante.getNome());

                LISTA_AJUDANTE.add(ajudante.getNome());
                jogadorDao = new JogadorDao(ajudante);
                jogadorDao.insert();
                JOGADOR_MAP.put(ajudante.getNome(), ajudante);


            }else {
                throw new JogadorException("$eO jogador não está na fila de espera!");
            }
        } else {
            throw new JogadorException("$cVocê não tem permissão!!!");
        }
    }

    public void insert(Player player, String nome) throws JogadorException {
        if (!player.isOp()) {
            throw new JogadorException("Você não tem permissão!!!");
        }
        if (JOGADOR_MAP.get(nome) instanceof Espera espera) {
            amigo = new Amigo(espera);
            amigoDao = new AmigoDao(amigo);
            amigoDao.insert();
            EsperaController.Remove(nome);
            LISTA_ESPERA.remove(espera);
        } else if (JOGADOR_MAP.get(nome) instanceof Ajudante ajudante) {
            amigo = new Amigo(ajudante);
            amigoDao = new AmigoDao(amigo);
            amigoDao.insert();
            AjudanteController.Remove(nome);
            LISTA_AJUDANTE.remove(nome);
        } else {
            throw new JogadorException("O jogador não está na lista de espera!!!");
        }

    }

    public void login(Player player, String password) throws JogadorException {
        jogador = JOGADOR_MAP.get(player.getName());
        if (jogador instanceof Amigo) {
            if (jogador.getSenha().equals(password)) {
                jogador.setIp(player);
                amigoDao = new AmigoDao(jogador);
                amigoDao.update();
                jogador.setMove(true);
                JOGADOR_MAP.put(jogador.getNome(), jogador);
            } else {
                throw new JogadorException("Senha invalida!!!");
            }
        }
    }

    public void cadastro(Player player, String[] args) throws JogadorException {

        if (JOGADOR_MAP.get(player.getName()) instanceof Amigo amigo) {

            if (args.length != 2) {
                throw new JogadorException("Digite a senha e a confirmação!!!");
            }

            if (args[0] == null || args[0].isEmpty()) {
                throw new JogadorException("Digite a senha!!!");
            }

            if (args[1] == null || args[1].isEmpty()) {
                throw new JogadorException("Digite a confirmação da senha!!!");
            }

            if (args[0].equalsIgnoreCase(amigo.getNome())) {
                throw new JogadorException("Senha não pode ser igual ao seu nome!!!");
            }

            if (!args[0].equals(args[1])) {
                throw new JogadorException("Senha não confere, digite novamente!!!");
            }

            amigo.setSenha(args[0]);
            amigo.setMove(true);
            amigoDao = new AmigoDao(amigo);
            if (!amigoDao.update()) {
                throw new JogadorException("Erro ao fazer o cadastro!!!");
            }
        }
    }

    public void remover(Player player, String nome) throws JogadorException {
        if (!player.isOp()) {
            throw new JogadorException("$cVocê não tem permissão!!!");
        }
        if (JOGADOR_MAP.get(nome) instanceof Amigo amigo) {
            amigoDao = new AmigoDao(amigo);
            amigoDao.remove();
            LISTA_AMIGO.remove(nome);
            Player amigoPlayer = Bukkit.getPlayer(amigo.getUuid());
            if (amigoPlayer != null && amigoPlayer.isOnline()) {
                amigoPlayer.kickPlayer("Você deixou de ser um amigo, mas ainda pode ser um ajudante!!!");
            }
        }else{
            throw new JogadorException(String.format("$cO jogador %s não existe!!!", nome));
        }
    }
}

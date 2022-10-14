package mc.elderbr.loginacess.dao;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.interfaces.Global;
import mc.elderbr.loginacess.interfaces.JogadorInterface;
import mc.elderbr.loginacess.model.Ajudante;
import mc.elderbr.loginacess.model.Amigo;
import mc.elderbr.loginacess.model.Espera;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JogadorDao implements JogadorInterface {

    private String nome;
    private String type;
    private Jogador jogador;
    private File file;
    private YamlConfiguration config;

    private JogadorDao() {
    }

    public JogadorDao(Jogador jogador) {
        nome = jogador.getNome();
        this.jogador = jogador;
        type = jogador.getType();
        create();
    }

    public boolean insert() {
        nome = jogador.getNome();
        config.set(nome.concat(".nome"), nome);
        config.set(nome.concat(".uuid"), jogador.getUuid());
        config.set(nome.concat(".ip"), jogador.getIp());
        if (jogador instanceof Amigo) {
            config.set(nome.concat(".senha"), jogador.getSenha());
        }
        JOGADOR_MAP.put(nome, jogador);
        return save();
    }

    public Jogador select(String nome) {
        jogador = new Jogador() {
            @Override
            public String getNome() {
                return config.getString(nome.concat(".nome"));
            }

            @Override
            public String getUuid() {
                return config.getString(nome.concat(".uuid"));
            }

            @Override
            public String getIp() {
                return config.getString(nome.concat(".ip"));
            }

            @Override
            public String getSenha() {
                return config.getString(nome.concat(".senha"));
            }

            @Override
            public String getType() {
                return type;
            }
        };
        if (type.equals("Amigo")) {
            return new Amigo(jogador);
        }
        if (type.equals("Ajudante")) {
            return new Ajudante(jogador);
        }
        if (type.equals("Espera")) {
            return new Espera(jogador);
        }
        return jogador;
    }

    public List<String> selectAll() {
        List<String> lista = new ArrayList<>();
        for (String nome : config.getValues(false).keySet()) {
            Jogador player = select(nome);
            JOGADOR_MAP.put(nome, player);
            lista.add(nome);
        }
        return lista;
    }

    public boolean remove(){
        config.set(jogador.getNome(), null);
        JOGADOR_MAP.remove(jogador.getNome());
        return save();
    }

    public boolean delete() throws IOException {
        try {
            file.delete();
            file.createNewFile();
            return true;
        }catch (IOException e){
            throw new IOException(e);
        }
    }

    public boolean update() {
        config.set(nome.concat(".nome"), nome);
        config.set(nome.concat(".uuid"), jogador.getUuid());
        config.set(nome.concat(".ip"), jogador.getIp());
        if (jogador instanceof Amigo) {
            config.set(nome.concat(".senha"), jogador.getSenha());
        }
        JOGADOR_MAP.put(nome, jogador);
        return save();
    }

    public void create() {
        file = new File(Global.pathConfig.getAbsoluteFile(), jogador.toFile());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar o arquivo " + jogador.toFile());
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public boolean save() {
        try {
            config.save(file);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public YamlConfiguration getConfig(){
        create();
        return config;
    }


}

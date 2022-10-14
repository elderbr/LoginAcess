package mc.elderbr.loginacess.dao;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.interfaces.Global;
import mc.elderbr.loginacess.model.Amigo;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class AmigoDao extends JogadorDao implements Global {

    private File file;
    private YamlConfiguration config;
    private Amigo amigo;

    public AmigoDao(Jogador jogador) {
        super(new Amigo(jogador));
    }

}

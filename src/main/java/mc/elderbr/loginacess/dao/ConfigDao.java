package mc.elderbr.loginacess.dao;

import mc.elderbr.loginacess.interfaces.Global;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConfigDao implements Global {

    private static final File myFile = new File(pathConfig.getAbsoluteFile(), "config.yml");
    private static final YamlConfiguration myConfig = YamlConfiguration.loadConfiguration(myFile);

    public ConfigDao() {
    }

    public static String getMessege(String command) {
        return myConfig.getString(command);
    }

    public static String selectAdm(String player) {
        for(Object adm : myConfig.getList("adm")){
            if(adm.equals(player)){
                return adm.toString();
            }
        }
        return null;
    }

    public static List<String> selectAll() {
        ADM_LISTA.clear();
        for (Object names : myConfig.getList("adm")) {
            ADM_LISTA.add(names.toString());
        }
        return ADM_LISTA;
    }

    public static void addAdm(String nome) throws IOException {
        List<String> lista = (List<String>) myConfig.getList("adm");
        lista.add(nome);
        Collections.sort(lista);
        myConfig.set("adm", lista);
        save();
    }

    public static void removeAdm(String nome) throws IOException {
        List<String> lista = (List<String>) myConfig.getList("adm");
        lista.remove(nome);
        Collections.sort(lista);
        myConfig.set("adm", lista);
        save();
    }



    public static void CreateDefault() {
        if (myConfig.getString("LoginAcess") == null) {
            myConfig.set("LoginAcess", "Controle de acesso");
            myConfig.set("Version", version);

            // Administradores
            myConfig.setComments("adm", Arrays.asList("Administradores do LoginAcess"));
            myConfig.set("adm", Arrays.asList());

            // Mensagens
            myConfig.setComments("notPermission", Arrays.asList("Mensagens personalizadas para o player", "", "Sem permissão"));
            myConfig.set("notPermission", "Você não tem permissão!!!");

            myConfig.setComments("cadastro", Arrays.asList("Ao fazer o cadastro"));
            myConfig.set("cadastro", "Cadastro bem sucessedido!!!");

            myConfig.setComments("login", Arrays.asList("Ao fazer o login"));
            myConfig.set("login", "Login bem sucessedido!!!");

            // Amigo
            myConfig.setComments("newAmigo", Arrays.asList("Quando for adicionado novo amigo"));
            myConfig.set("newAmigo", "Temos um novo amigo!!!");

            myConfig.setComments("deleteAmigo", Arrays.asList("Ao remover o jogador da lista de amigo"));
            myConfig.set("deleteAmigo", "Você não é mais amigo, mas ainda pode ser um ajudante!!!");

            // Ajudante
            myConfig.setComments("addAjudante", Arrays.asList("Ao adicionar ajudante"));
            myConfig.set("addAjudante", "O jogador agora é um ajudante!!!");

            myConfig.setComments("removeAjudante", Arrays.asList("Ao remover ajudante"));
            myConfig.set("removeAjudante", "O jogador deixou de ser um ajudante!!!");

            // Espera
            myConfig.setComments("limparEspera", Arrays.asList("Quando a lista de espera for limpa"));
            myConfig.set("limparEspera", "A lista de espera foi limpa!!!");

            try {
                save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        selectAll();// Listando todos os adm
    }

    public static void save() throws IOException {
        myConfig.save(myFile);
    }


}

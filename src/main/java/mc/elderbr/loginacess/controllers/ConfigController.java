package mc.elderbr.loginacess.controllers;

import mc.elderbr.loginacess.dao.ConfigDao;
import org.bukkit.entity.Player;

public class ConfigController {

    public ConfigController() {
    }

    public void addAdm(Player player, String nome) throws Exception {
        if(!player.isOp()){
            throw new Exception("Você não tem permissão!!!");
        }
        if(nome == null || nome.isEmpty()){
            throw new Exception("Digite o nome do jogador!!!");
        }
        if(ConfigDao.selectAdm(player)!=null){
            throw new Exception("O jogador "+ nome  +" já é um Adm do LoginAcess!!!");
        }
        ConfigDao.addAdm(nome);
    }

    public void removeAdm(Player player, String nome) throws Exception {
        if(!player.isOp()){
            throw new Exception("Você não tem permissão!!!");
        }
        if(nome == null || nome.isEmpty()){
            throw new Exception("Digite o nome do jogador!!!");
        }
        ConfigDao.removeAdm(nome);
    }
}

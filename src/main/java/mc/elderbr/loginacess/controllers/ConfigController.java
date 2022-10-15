package mc.elderbr.loginacess.controllers;

import mc.elderbr.loginacess.dao.ConfigDao;
import mc.elderbr.loginacess.model.Item;
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

    public void addNotItem(Player player, String item) throws Exception {
        if(!player.isOp()){
            throw new Exception("Você não tem permissão!!!");
        }
        if(item == null || item.isEmpty()){
            throw new Exception("Escolha um item!!!");
        }
        Item items = new Item();
        items.parseItemStack(item);
        ConfigDao.AddItem(item);
    }
}

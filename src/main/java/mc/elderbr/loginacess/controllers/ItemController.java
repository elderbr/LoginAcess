package mc.elderbr.loginacess.controllers;

import mc.elderbr.loginacess.dao.ConfigDao;
import mc.elderbr.loginacess.dao.ItemDao;
import mc.elderbr.loginacess.interfaces.ItemInterface;
import mc.elderbr.loginacess.model.Item;
import org.bukkit.entity.Player;

public class ItemController implements ItemInterface {

    public void insert(Player player, String item) throws Exception {
        if(ConfigDao.selectAdm(player) == null){
            throw new Exception("Você não tem permissão!!!");
        }
        if(item == null || item.isEmpty()){
            throw new Exception("Escolha um item!!!");
        }
        Item items = new Item();
        items.parseItemStack(item);
        ItemDao.insert(item);
    }

    public void remove(Player player, String item) throws Exception {
        if(ConfigDao.selectAdm(player) == null){
            throw new Exception("Você não tem permissão!!!");
        }
        if(item == null || item.isEmpty()){
            throw new Exception("Escolha um item!!!");
        }
        Item items = new Item();
        items.parseItemStack(item);
        ItemDao.remove(item);
    }
}

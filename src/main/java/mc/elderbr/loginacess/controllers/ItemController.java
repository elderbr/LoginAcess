package mc.elderbr.loginacess.controllers;

import mc.elderbr.loginacess.dao.ItemDao;
import mc.elderbr.loginacess.interfaces.ItemInterface;
import org.bukkit.entity.Player;

public class ItemController implements ItemInterface {

    public void insert(Player player, String item) throws Exception {
        if(!ADM_LISTA.contains(player.getName())){
            throw new Exception("Você não tem permissão!!!");
        }
        if(item == null || item.isEmpty()){
            throw new Exception("Escolha um item!!!");
        }
        if(ItemDao.select(item)){
            throw new Exception("O item já foi adicionado!!!");
        }
        ItemDao.insert(item);
    }

    public void delete(Player player, String item) throws Exception {
        if(!ADM_LISTA.contains(player.getName())){
            throw new Exception("Você não tem permissão!!!");
        }
        if(item == null || item.isEmpty()){
            throw new Exception("Escolha um item!!!");
        }
        if(ItemDao.select(item)){
            throw new Exception("O item "+ item +" não está na lista!!!");
        }
        ItemDao.delete(item);
    }
}

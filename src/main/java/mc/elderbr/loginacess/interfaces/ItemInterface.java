package mc.elderbr.loginacess.interfaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ItemInterface extends Global {

    List<String> ITEM_LISTA = new ArrayList<>();
    Map<String, ItemStack> ITEM_MAP = new HashMap<>();

    default String parse(ItemStack itemStack){
        return itemStack.getType().getKey().getKey().toLowerCase().replaceAll("_"," ");
    }

    default ItemStack parseItemStack(String nome) throws Exception {
        if(nome == null || nome.isEmpty()){
            throw new Exception("Digite o nome do item!!!");
        }
        try{
            return new ItemStack(Material.getMaterial(nome.toUpperCase().replaceAll("\\s", "_")));
        }catch (Exception e){
            throw new Exception("O item não existe!!!");
        }
    }

    default void ItemSelectAll(){
        ITEM_LISTA.clear();
        ITEM_MAP.clear();
        for(Material material : Material.values()){
            if(material.isItem() || material.isBlock()) {
                ItemStack itemStack = new ItemStack(material);
                ITEM_LISTA.add(parse(itemStack));
                ITEM_MAP.put(parse(itemStack), itemStack);
            }
        }
    }

}

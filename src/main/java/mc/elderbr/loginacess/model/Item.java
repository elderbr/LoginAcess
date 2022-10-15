package mc.elderbr.loginacess.model;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static mc.elderbr.loginacess.interfaces.Global.ITEM_LISTA;
import static mc.elderbr.loginacess.interfaces.Global.ITEM_MAP;

public class Item {

    private String nome;

    public Item() {
    }

    public static void CreateItem(){
        ITEM_LISTA.clear();
        ITEM_MAP.clear();
        for(Material material : Material.values()){
            if(material.isItem()) {
                ItemStack itemStack = new ItemStack(material);
                String nome = itemStack.getType().getKey().getKey().toLowerCase().replaceAll("_", " ");
                ITEM_LISTA.add(nome);
                ITEM_MAP.put(nome, itemStack);
            }
        }
    }

    public ItemStack select(String item){
        return ITEM_MAP.get(item);
    }

    public String parse(ItemStack itemStack){
        return itemStack.getType().getKey().getKey().replaceAll("_", " ");
    }
    public ItemStack parseItemStack(String item){
        return new ItemStack(Material.getMaterial(item.toUpperCase().replaceAll("\\s","_")));
    }
}

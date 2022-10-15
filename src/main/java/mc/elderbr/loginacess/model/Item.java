package mc.elderbr.loginacess.model;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static mc.elderbr.loginacess.interfaces.Global.ITEM_LISTA;
import static mc.elderbr.loginacess.interfaces.Global.ITEM_MAP;

public class Item {

    private String nome;
    private ItemStack itemStack;

    public Item() {
    }

    public Item(ItemStack itemStack){
        nome = parse(itemStack);
        this.itemStack = itemStack;
    }

    public static void CreateItem() {
        ITEM_LISTA.clear();
        ITEM_MAP.clear();
        for (Material material : Material.values()) {
            if (material.isItem()) {
                ItemStack itemStack = new ItemStack(material);
                Item item = new Item(itemStack);
                ITEM_LISTA.add(item.getNome());
                ITEM_MAP.put(item.getNome(), item);
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public String parse(ItemStack itemStack) {
        nome = itemStack.getType().getKey().getKey().replaceAll("_", " ");
        this.itemStack = itemStack;
        return nome;
    }

    public static String Parse(ItemStack itemStack) {
        return itemStack.getType().getKey().getKey().replaceAll("_", " ");
    }

    public ItemStack parseItemStack(String item) throws Exception {
        try {
            itemStack = new ItemStack(Material.getMaterial(item.toUpperCase().replaceAll("\\s", "_")));
        }catch (Exception e){
            throw new Exception("O item não existe!!!");
        }
        return itemStack;
    }
}

package mc.elderbr.loginacess.dao;

import mc.elderbr.loginacess.interfaces.ItemInterface;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao implements ItemInterface {

    private static final File myFile = new File(pathConfig.getAbsoluteFile(), "item.yml");
    private static final YamlConfiguration myConfig = YamlConfiguration.loadConfiguration(myFile);

    public ItemDao() {
    }

    public static void insert(String item) throws IOException {
        List<String> lista = (List<String>) myConfig.getList("notItem");
        lista.add(item);
        myConfig.set("notItem", lista);
        save();
    }

    public static void remove(String item) throws IOException {
        List<String> lista = (List<String>) myConfig.getList("notItem");
        lista.remove(item);
        myConfig.set("notItem", lista);
        save();
    }

    public static boolean select(String item){
        return (myConfig.getString("notItem."+ item)==null);
    }
    public static List<String> selectAllNotItem(){
        create();
        List<String> lista = new ArrayList<>();
        for(Object key : myConfig.getList("notItem")){
            lista.add(key.toString());
        }
        return lista;
    }

    private static void create(){
        if(myFile.exists()){
            try {
                myFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void save() {
        try {
            myConfig.save(myFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

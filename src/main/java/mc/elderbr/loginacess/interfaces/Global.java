package mc.elderbr.loginacess.interfaces;

import mc.elderbr.loginacess.model.Item;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Global {

    Plugin pluginMy = Bukkit.getServer().getPluginManager().getPlugin("LoginAcess");
    File pathConfig = pluginMy.getDataFolder().getAbsoluteFile();
    String version = pluginMy.getDescription().getVersion();

    List<String> ITEM_LISTA = new ArrayList<>();
    Map<String, Item> ITEM_MAP = new HashMap<>();

}

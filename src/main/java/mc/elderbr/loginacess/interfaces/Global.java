package mc.elderbr.loginacess.interfaces;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface Global {

    Plugin pluginMy = Bukkit.getServer().getPluginManager().getPlugin("LoginAcess");
    File pathConfig = pluginMy.getDataFolder().getAbsoluteFile();
    String version = pluginMy.getDescription().getVersion();
    List<String> ADM_LISTA = new ArrayList<>();
}

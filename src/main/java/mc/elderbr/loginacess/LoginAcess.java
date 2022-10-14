package mc.elderbr.loginacess;

import mc.elderbr.loginacess.commads.AjudanteCmd;
import mc.elderbr.loginacess.commads.AjudanteCommandTab;
import mc.elderbr.loginacess.commads.AmigoCmd;
import mc.elderbr.loginacess.commads.AmigoCmdTab;
import mc.elderbr.loginacess.controllers.AjudanteController;
import mc.elderbr.loginacess.controllers.AmigoController;
import mc.elderbr.loginacess.controllers.EsperaController;
import mc.elderbr.loginacess.events.JoinEvent;
import mc.elderbr.loginacess.events.PlayerMove;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class LoginAcess extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveConfig();

        AmigoController.SelectAll();
        AjudanteController.SelectAll();
        EsperaController.SelectAll();

        events();
        commands();
    }

    private void commands() {
        // Amigo
        getCommand("cadastro").setExecutor(new AmigoCmd());
        getCommand("login").setExecutor(new AmigoCmd());
        getCommand("addamigo").setExecutor(new AmigoCmd());
        getCommand("addamigo").setTabCompleter(new AmigoCmdTab());
        getCommand("removeamigo").setExecutor(new AmigoCmd());
        getCommand("removeamigo").setTabCompleter(new AmigoCmdTab());

        // Ajudante
        getCommand("addseguidor").setExecutor(new AjudanteCmd());
        getCommand("addseguidor").setTabCompleter(new AjudanteCommandTab());

    }

    private void events() {
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

package mc.elderbr.loginacess;

import mc.elderbr.loginacess.commads.*;
import mc.elderbr.loginacess.controllers.AjudanteController;
import mc.elderbr.loginacess.controllers.AmigoController;
import mc.elderbr.loginacess.controllers.EsperaController;
import mc.elderbr.loginacess.dao.ConfigDao;
import mc.elderbr.loginacess.dao.ItemDao;
import mc.elderbr.loginacess.events.*;
import mc.elderbr.loginacess.exceptions.JogadorException;
import mc.elderbr.loginacess.interfaces.ItemInterface;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class LoginAcess extends JavaPlugin implements Listener, ItemInterface {

    @Override
    public void onEnable() {
        saveConfig();

        AmigoController.SelectAll();
        AjudanteController.SelectAll();
        EsperaController.SelectAll();
        ConfigDao.CreateDefault();
        ItemSelectAll();
        ItemDao.selectAllNotItem();

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
        getCommand("removeseguidor").setExecutor(new AjudanteCmd());
        getCommand("removeseguidorAll").setExecutor(new AjudanteCmd());

        // Espera
        getCommand("limparespera").setExecutor(new EsperaCmd());

        // Administrador
        getCommand("addAdm").setExecutor(new ConfigCmd());
        getCommand("addAdm").setTabCompleter(new AdmCmdTab());

        // Item
        getCommand("addItem").setExecutor(new ItemCmd());
        getCommand("addItem").setTabCompleter(new ItemCmd());
        getCommand("removeItem").setExecutor(new ItemCmd());
        getCommand("removeItem").setTabCompleter(new ItemCmd());

    }

    private void events() {
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new BreakBlockEvent(), this);
        getServer().getPluginManager().registerEvents(new InventorioClickEvent(), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
    }


    @Override
    public void onDisable() {
        try {
            AjudanteController.RemoveAll();
            Msg.PlayerAll("Todos os ajudantes foram removidos");
        } catch (JogadorException e) {
            Msg.PlayerAll(e.toString());
        }
    }

}

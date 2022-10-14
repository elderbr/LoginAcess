package mc.elderbr.loginacess.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Msg {

    public static void Player(Player player, String msg){
        player.sendMessage(Color(msg));
    }

    public static void PlayerAll(String msg){
        Bukkit.getServer().broadcastMessage(Color(msg));
    }

    public static void Server(@NotNull String msg, @NotNull Class classe) {
        Bukkit.getServer().getConsoleSender().sendMessage(Color(msg));
    }

    private static String Color(String msg) {
        return ChatColor.translateAlternateColorCodes('$', msg);
    }

    public static void Erro(String metodo, Class aClass, Exception e) {
        Server("\nMetodo: " + metodo
                        + "\nErro: " + e.getMessage()
                , aClass);
        Espaco(aClass);
    }
    public static void Erro(String msg, String metodo, Class aClass, Exception e) {
        Server(msg
                        + "\nMetodo: " + metodo
                        + "\nErro: " + e
                , aClass);
        Espaco(aClass);
    }

    public static void Espaco(@NotNull Class classe){
        Bukkit.getServer().getConsoleSender()
                .sendMessage("============================================================="+" - Class: "+ classe.getSimpleName()+"\n");
    }
}

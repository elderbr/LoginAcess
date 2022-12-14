package mc.elderbr.loginacess.commads;

import mc.elderbr.loginacess.controllers.AjudanteController;
import mc.elderbr.loginacess.interfaces.Comando;
import mc.elderbr.loginacess.interfaces.JogadorInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class AjudanteCommandTab implements TabCompleter, Comando, JogadorInterface {
    private Player player;
    private AjudanteController ajudanteController;

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player players) {
            player = players;

            if (command.getName().equalsIgnoreCase("addseguidor")) {
                return LISTA_ESPERA;
            }

        }
        return Arrays.asList("");
    }
}

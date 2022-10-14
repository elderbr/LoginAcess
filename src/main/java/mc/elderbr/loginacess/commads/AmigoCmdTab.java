package mc.elderbr.loginacess.commads;

import mc.elderbr.loginacess.interfaces.Comando;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static mc.elderbr.loginacess.interfaces.JogadorInterface.LISTA_AJUDANTE;
import static mc.elderbr.loginacess.interfaces.JogadorInterface.LISTA_ESPERA;

public class AmigoCmdTab implements TabCompleter, Comando {
    private String myCommand;
    private String nome;
    private List<String> lista;
    private Player myPlayer;

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        myCommand = command.getName().toLowerCase();
        nome = getCommand(args);

        if (sender instanceof Player player) {

            myPlayer = player;

            if (myCommand.equalsIgnoreCase("addamigo")) {
                lista = new ArrayList<>();
                for (String nomes : LISTA_ESPERA) {
                    if (args.length < 1 && !myPlayer.getName().equals(nomes)) {
                        lista.add(nomes);
                    } else if (nomes.contains(nome) && !myPlayer.getName().equals(nomes)) {
                        lista.add(nomes);
                    }
                }
                for (String nomes : LISTA_AJUDANTE) {
                    if (args.length < 1 && !myPlayer.getName().equals(nomes)) {
                        lista.add(nomes);
                    } else if (nomes.contains(nome) && !myPlayer.getName().equals(nomes)) {
                        lista.add(nomes);
                    }
                }
                return lista;
            }
        }
        return Arrays.asList();
    }
}

package mc.elderbr.loginacess.controllers;

import mc.elderbr.loginacess.abstracts.Jogador;
import mc.elderbr.loginacess.dao.ConfigDao;
import mc.elderbr.loginacess.dao.JogadorDao;
import mc.elderbr.loginacess.exceptions.JogadorException;
import mc.elderbr.loginacess.model.Ajudante;
import mc.elderbr.loginacess.model.Amigo;
import mc.elderbr.loginacess.model.Bau;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static mc.elderbr.loginacess.interfaces.JogadorInterface.JOGADOR_MAP;
import static mc.elderbr.loginacess.interfaces.JogadorInterface.LISTA_AJUDANTE;

public class AjudanteController {

    private Player amigoPlayer;
    private Player ajudantePlayer;

    public void quit(Player player) {
        if (JOGADOR_MAP.get(player.getName()) instanceof Ajudante ajudante) {
            ajudantePlayer = player;

            List<ItemStack> listItemStack = new ArrayList<>();
            for (ItemStack itemStack : player.getInventory()) {
                if (itemStack != null)
                    listItemStack.add(itemStack);
            }
            for (ItemStack itemStack : player.getEnderChest()) {
                if (itemStack != null)
                    listItemStack.add(itemStack);
            }
            if (Bukkit.getPlayerExact(ajudante.getAmigo().getNome()) != null) {
                amigoPlayer = Bukkit.getPlayerExact(ajudante.getAmigo().getNome());
                for (ItemStack itemStack : listItemStack) {
                    Bukkit.getWorld(ajudantePlayer.getWorld().getName()).dropItem(amigoPlayer.getLocation(), itemStack);
                }
            } else {
                Bau bau = new Bau(player);
                bau.createBau();
                bau.addItem();
            }
            ajudantePlayer.getInventory().clear();
            ajudantePlayer.getEnderChest().clear();
            try {
                Remove(ajudantePlayer.getName());
            } catch (JogadorException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void SelectAll() {
        new JogadorDao(new Ajudante()).selectAll();
        LISTA_AJUDANTE.clear();
        for (Jogador jogador : JOGADOR_MAP.values()) {
            if (jogador instanceof Ajudante) {
                LISTA_AJUDANTE.add(jogador.getNome());
            }
        }
    }

    public void remove(Player player, String nome) throws JogadorException {

        if (!(JOGADOR_MAP.get(player.getName()) instanceof Amigo amigo)) {
            throw new JogadorException("Você não tem permissão!!!");
        }
        if (nome == null || nome.isEmpty()) {
            throw new JogadorException("O nome do ajudante não poder ser vazio!!!");
        }
        if (JOGADOR_MAP.get(nome) == null) {
            throw new JogadorException("O ajudante " + nome + " não existe!!!");
        }
        if (JOGADOR_MAP.get(nome) instanceof Ajudante ajudante) {
            quit(Bukkit.getPlayerExact(nome));
            Remove(nome);
        } else {
            throw new JogadorException("O jogador " + nome + " não é um ajudante!!!");
        }
    }

    public static void Remove(String nome) throws JogadorException {
        Ajudante ajudante = new Ajudante();
        ajudante.setNome(nome);
        new JogadorDao(ajudante).remove();
        LISTA_AJUDANTE.remove(nome);
    }

    public static void RemoveAll() throws JogadorException {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (JOGADOR_MAP.get(onlinePlayer.getName()) instanceof Ajudante) {
                Remove(onlinePlayer.getName());
                onlinePlayer.kickPlayer("Obrigado por ajudar, espero você em breve!");
            }
        }
    }

    public static void RemoveAll(Player player) throws JogadorException {
        if (ConfigDao.selectAdm(player.getName()) == null) {
            throw new JogadorException("$cVocê não tem permissão!");
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (JOGADOR_MAP.get(onlinePlayer.getName()) instanceof Ajudante) {
                Remove(onlinePlayer.getName());
                onlinePlayer.kickPlayer("Obrigado por ajudar, espero você em breve!");
            }
        }
    }
}

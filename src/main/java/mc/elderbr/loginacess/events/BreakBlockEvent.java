package mc.elderbr.loginacess.events;

import mc.elderbr.loginacess.interfaces.ItemInterface;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import static mc.elderbr.loginacess.interfaces.JogadorInterface.LISTA_AJUDANTE;

public class BreakBlockEvent implements Listener, ItemInterface {

    private Player myPlayer;
    private Block block, up, down, left, right;

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {

        myPlayer = event.getPlayer();

        if(!LISTA_AJUDANTE.contains(myPlayer.getName())) return;

        block = event.getBlock();
        up = block.getRelative(BlockFace.UP);
        down = block.getRelative(BlockFace.DOWN);
        left = block.getRelative(BlockFace.WEST);
        right = block.getRelative(BlockFace.EAST);

        if (ITEM_NotItem_LISTA.contains(blockName(block))
                || ITEM_NotItem_LISTA.contains(blockName(up))
                || ITEM_NotItem_LISTA.contains(blockName(down))
                || ITEM_NotItem_LISTA.contains(blockName(left))
                || ITEM_NotItem_LISTA.contains(blockName(right))
        ) {
            Msg.Player(myPlayer, "$c$lHum, você não pode fazer isso!");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent event) {

        myPlayer = event.getPlayer();

        if(!LISTA_AJUDANTE.contains(myPlayer.getName())) return;

        block = event.getBlock();
        up = block.getRelative(BlockFace.UP);
        down = block.getRelative(BlockFace.DOWN);
        left = block.getRelative(BlockFace.WEST);
        right = block.getRelative(BlockFace.EAST);

        if (ITEM_NotItem_LISTA.contains(blockName(block))
                || ITEM_NotItem_LISTA.contains(blockName(up))
                || ITEM_NotItem_LISTA.contains(blockName(down))
                || ITEM_NotItem_LISTA.contains(blockName(left))
                || ITEM_NotItem_LISTA.contains(blockName(right))
        ) {
            Msg.Player(myPlayer, "$c$lHum, você não pode fazer isso!");
            event.setCancelled(true);
        }
    }

    private String blockName(Block block) {
        return block.getType().name().toLowerCase().replaceAll("_", " ");
    }
}

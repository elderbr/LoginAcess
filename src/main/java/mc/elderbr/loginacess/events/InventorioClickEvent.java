package mc.elderbr.loginacess.events;

import mc.elderbr.loginacess.interfaces.ItemInterface;
import mc.elderbr.loginacess.interfaces.JogadorInterface;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InventorioClickEvent  implements Listener, JogadorInterface, ItemInterface {

    private Player player;

    @EventHandler
    public void clickInventory(InventoryClickEvent event){
        player = (Player) event.getWhoClicked();

        if(!LISTA_AJUDANTE.contains(player.getName())) return;

        ItemStack itemClick = event.getCurrentItem();

        if(event.getClickedInventory().getType() != InventoryType.PLAYER) {
            if (ITEM_NotItem_LISTA.contains(parse(itemClick))) {
                event.setCancelled(true);
                player.closeInventory();
                Msg.Player(player, "$eHum, você não pode pegar esse item!!!");
            }
        }
    }
}

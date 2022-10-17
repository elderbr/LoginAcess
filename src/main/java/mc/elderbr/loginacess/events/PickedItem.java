package mc.elderbr.loginacess.events;

import mc.elderbr.loginacess.interfaces.ItemInterface;
import mc.elderbr.loginacess.interfaces.JogadorInterface;
import mc.elderbr.loginacess.model.Ajudante;
import mc.elderbr.loginacess.utils.Msg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PickedItem implements Listener, JogadorInterface, ItemInterface {

    @EventHandler
    public void pickedItems(EntityPickupItemEvent event){
       if(event.getEntity() instanceof Player player){
           if(JOGADOR_MAP.get(player.getName()) instanceof Ajudante ajudante){
               if(ITEM_NotItem_LISTA.contains(parse(event.getItem().getItemStack()))) {
                   event.setCancelled(true);
                   (new BukkitRunnable() {
                       @Override
                       public void run() {
                           Msg.Player(player, "Você não pode pegar esse item!");
                       }
                   }).runTaskTimer(pluginMy, 60,  (long) (20 * 60));
               }
           }
       }
    }
}

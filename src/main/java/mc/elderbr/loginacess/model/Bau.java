package mc.elderbr.loginacess.model;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Bau {

    private Player player;
    private List<ItemStack> listItemStack;
    private Block block1, block2;
    private Chest chestLeft, chestRight;
    private org.bukkit.block.data.type.Chest chestDataLeft;
    private org.bukkit.block.data.type.Chest chestDataRight;

    private Bau(){}

    public Bau(Player player) {
        this.player = player;
    }

    public Bau createBau() {
        block1 = player.getLocation().getBlock();
        block2 = null;

        if (block1.getRelative(BlockFace.EAST).getType() == Material.AIR) {
            block2 = block1.getRelative(BlockFace.EAST);
        } else if (block1.getRelative(BlockFace.WEST).getType() == Material.AIR) {
            block2 = block1.getRelative(BlockFace.WEST);
        }
        if (block2 == null) {
            block2 = player.getLocation().getBlock().getRelative(BlockFace.UP);
        }
        block1.setType(Material.CHEST);
        chestLeft = (Chest) block1.getState();
        chestDataLeft = (org.bukkit.block.data.type.Chest) this.chestLeft.getBlockData();
        chestDataLeft.setType(org.bukkit.block.data.type.Chest.Type.LEFT);

        block2.setType(Material.CHEST);
        chestRight = (Chest) block2.getState();
        chestDataRight = (org.bukkit.block.data.type.Chest) this.chestRight.getBlockData();
        chestDataRight.setType(org.bukkit.block.data.type.Chest.Type.RIGHT);

        block1.setBlockData(chestDataLeft, false);
        block2.setBlockData(chestDataRight, false);
        return this;
    }

    public Bau addItem(){
        listItemStack = new ArrayList<>();
        for(ItemStack itemStack : player.getInventory()){
            listItemStack.add(itemStack);
        }
        for(ItemStack itemStack : player.getEnderChest()){
            listItemStack.add(itemStack);
        }
        int position = 0;
        for(ItemStack itemStack : listItemStack) {
            if(position < 27) {
                chestLeft.getInventory().addItem(itemStack);
            }else{
                chestRight.getInventory().addItem(itemStack);
            }
            position++;
        }
        player.getInventory().clear();
        player.getEnderChest().clear();
        return this;
    }
}

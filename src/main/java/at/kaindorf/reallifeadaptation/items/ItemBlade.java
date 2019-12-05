package at.kaindorf.reallifeadaptation.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBlade extends Item {
    private int used;
    public ItemBlade(String name, int uses){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MATERIALS);
        setMaxStackSize(1);
    }

    public boolean onUse(){
        return getMaxUsage() - used == 0;
    }

    public void setUsed(int used){
        this.used = used;
    }

    public int getUsed() {
        return used;
    }

    public int getMaxUsage(){
        return 8;
    }
}

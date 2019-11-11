package at.kaindorf.reallifeadaptation.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class FuelItem extends Item {
    public FuelItem(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MATERIALS);
    }
}

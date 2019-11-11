package at.kaindorf.reallifeadaptation.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemRubberBucket extends Item {
    public ItemRubberBucket(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MATERIALS);
    }
}
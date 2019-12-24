package at.kaindorf.reallifeadaptation.items;

import at.kaindorf.reallifeadaptation.RealLifeAdaptation;
import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemOilBucket extends Item {
    public ItemOilBucket(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RealLifeAdaptation.ITEM_TAB);
    }

}

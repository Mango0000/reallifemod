package at.kaindorf.reallifeadaptation.items;

import at.kaindorf.reallifeadaptation.RealLifeAdaptation;
import net.minecraft.item.Item;

public class ItemSteelIngot extends Item {

    public ItemSteelIngot(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RealLifeAdaptation.ITEM_TAB);
    }
}

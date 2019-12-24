package at.kaindorf.reallifeadaptation.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TabArmor extends CreativeTabs {
    public TabArmor(String label){
        super("Armor");
        this.setBackgroundImageName("custom_creative_tab.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.DIAMOND_CHESTPLATE);
    }
}


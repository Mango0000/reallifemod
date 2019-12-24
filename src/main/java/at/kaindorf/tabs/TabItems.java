package at.kaindorf.tabs;

import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabItems extends CreativeTabs {


    public TabItems(String label){
        super("Items");
        this.setBackgroundImageName("custom_creative_tab.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(CommonProxy.oilbucket);
    }
}

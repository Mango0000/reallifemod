package at.kaindorf.tabs;

import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabTrees extends CreativeTabs {

    public TabTrees(String label){
        super("Trees");
        this.setBackgroundImageName("custom_creative_tab.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        ItemStack i = new ItemStack(CommonProxy.COPPER_SAPLING);
        return i;
    }
}

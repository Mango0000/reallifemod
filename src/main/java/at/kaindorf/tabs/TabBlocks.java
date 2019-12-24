package at.kaindorf.tabs;

import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabBlocks extends CreativeTabs {

    public TabBlocks(String label){
        super("Blocks");
        this.setBackgroundImageName("custom_creative_tab.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(CommonProxy.compressedcoal);
    }
}

package at.kaindorf.reallifeadaptation.tabs;

import at.kaindorf.reallifeadaptation.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabMachine extends CreativeTabs {

    public TabMachine(String label){
        super("Machines");
        this.setBackgroundImageName("custom_creative_tab.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        ItemStack i = new ItemStack(CommonProxy.CUSTOM_FURNACE);
        return i;
    }
}

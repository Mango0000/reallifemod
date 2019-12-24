package at.kaindorf.reallifeadaptation.items.armor;

import at.kaindorf.reallifeadaptation.RealLifeAdaptation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorBase extends ItemArmor {

    public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RealLifeAdaptation.ARMOR_TAB);
    }
}

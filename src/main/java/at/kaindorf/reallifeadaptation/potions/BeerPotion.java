package at.kaindorf.reallifeadaptation.potions;

import at.kaindorf.reallifeadaptation.RealLifeAdaptation;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class BeerPotion extends Potion {
    public BeerPotion(String name, boolean isBadEffectIn, int liquidColorIn, int iconIndexX, int iconIndexY) {
        super(isBadEffectIn, liquidColorIn);
        setPotionName("effect." + name);
        setIconIndex(iconIndexX, iconIndexY);
        setRegistryName(new ResourceLocation(RealLifeAdaptation.MODID + ":" + name));
    }

    @Override
    public Potion registerPotionAttributeModifier(IAttribute attribute, String uniqueId, double ammount, int operation) {
        return super.registerPotionAttributeModifier(attribute, uniqueId, ammount, operation);
    }

    @Override
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(RealLifeAdaptation.MODID + "textures/gui/potion_effects.png"));
        return true;
    }
}

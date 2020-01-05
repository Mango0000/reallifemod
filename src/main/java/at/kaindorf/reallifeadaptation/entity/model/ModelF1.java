package at.kaindorf.reallifeadaptation.entity.model;//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelF1 extends ModelBase {
	private ModelRenderer f1;
	private ModelRenderer cockpit;
	private ModelRenderer seat;
	private ModelRenderer pedals;
	private ModelRenderer front_pumper;
	private ModelRenderer front_axis;
	private ModelRenderer tire3;
	private ModelRenderer tire4;
	private ModelRenderer back_axis;
	private ModelRenderer tire;
	private ModelRenderer tire2;
	private ModelRenderer wing;

	public ModelF1() {
		textureWidth = 64;
		textureHeight = 64;

		f1 = new ModelRenderer(this);
		f1.setRotationPoint(-8.0F, 16.0F, 8.0F);

		cockpit = new ModelRenderer(this);
		cockpit.setRotationPoint(0.0F, 0.0F, 0.0F);
		f1.addChild(cockpit);
		cockpit.cubeList.add(new ModelBox(cockpit, 0, 20, 0.0F, 5.0F, -18.0F, 16, 1, 20, 0.0F, false));
		cockpit.cubeList.add(new ModelBox(cockpit, 0, 20, 0.0F, 0.0F, -18.0F, 1, 5, 20, 0.0F, false));
		cockpit.cubeList.add(new ModelBox(cockpit, 0, 20, 15.0F, 0.0F, -18.0F, 1, 5, 20, 0.0F, false));
		cockpit.cubeList.add(new ModelBox(cockpit, 0, 1, 1.0F, 0.0F, -18.0F, 14, 5, 1, 0.0F, false));
		cockpit.cubeList.add(new ModelBox(cockpit, 0, 1, 1.0F, 0.0F, 1.0F, 14, 5, 1, 0.0F, false));
		cockpit.cubeList.add(new ModelBox(cockpit, 0, 1, 7.0F, 0.0F, -17.0F, 2, 2, 1, 0.0F, false));
		cockpit.cubeList.add(new ModelBox(cockpit, 0, 1, 5.0F, -1.0F, -16.0F, 6, 4, 1, 0.0F, false));

		seat = new ModelRenderer(this);
		seat.setRotationPoint(0.0F, 0.0F, 0.0F);
		cockpit.addChild(seat);
		seat.cubeList.add(new ModelBox(seat, 0, 11, 2.0F, 4.0F, -11.0F, 12, 1, 11, 0.0F, false));
		seat.cubeList.add(new ModelBox(seat, 0, 1, 2.0F, -3.0F, -1.0F, 12, 7, 1, 0.0F, false));

		pedals = new ModelRenderer(this);
		pedals.setRotationPoint(0.0F, 0.0F, 0.0F);
		cockpit.addChild(pedals);
		pedals.cubeList.add(new ModelBox(pedals, 0, 1, 10.0F, -1.0F, -18.0F, 3, 3, 1, 0.0F, false));
		pedals.cubeList.add(new ModelBox(pedals, 0, 1, 3.0F, -1.0F, -18.0F, 3, 3, 1, 0.0F, false));

		front_pumper = new ModelRenderer(this);
		front_pumper.setRotationPoint(0.0F, 0.0F, 0.0F);
		f1.addChild(front_pumper);
		front_pumper.cubeList.add(new ModelBox(front_pumper, 0, 11, 6.0F, 2.0F, -29.0F, 4, 3, 11, 0.0F, false));
		front_pumper.cubeList.add(new ModelBox(front_pumper, 0, 5, 1.0F, 4.0F, -32.0F, 14, 2, 5, 0.0F, false));

		front_axis = new ModelRenderer(this);
		front_axis.setRotationPoint(0.0F, 0.0F, 0.0F);
		f1.addChild(front_axis);
		front_axis.cubeList.add(new ModelBox(front_axis, 0, 1, 2.0F, 4.0F, -23.0F, 12, 1, 1, 0.0F, false));
		front_axis.cubeList.add(new ModelBox(front_axis, 0, 1, 2.0F, 3.0F, -23.0F, 12, 1, 1, 0.0F, false));

		tire3 = new ModelRenderer(this);
		tire3.setRotationPoint(0.0F, 0.0F, 0.0F);
		front_axis.addChild(tire3);
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 1.0F, -23.0F, 3, 7, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 7, 12.0F, 4.0F, -26.0F, 3, 1, 7, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 2, 12.0F, 3.0F, -25.0F, 3, 1, 2, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 2, 12.0F, 5.0F, -22.0F, 3, 1, 2, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 2, 12.0F, 3.0F, -22.0F, 3, 1, 2, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 2, 12.0F, 5.0F, -25.0F, 3, 1, 2, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 2.0F, -22.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 2.0F, -24.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 6.0F, -24.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 6.0F, -22.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 1.0F, -22.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 3.0F, -20.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 5.0F, -20.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 7.0F, -24.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 7.0F, -22.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 3.0F, -26.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 5.0F, -26.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 2.0F, -25.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 2.0F, -21.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 6.0F, -21.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 6.0F, -25.0F, 3, 1, 1, 0.0F, false));
		tire3.cubeList.add(new ModelBox(tire3, 0, 1, 12.0F, 1.0F, -24.0F, 3, 1, 1, 0.0F, false));

		tire4 = new ModelRenderer(this);
		tire4.setRotationPoint(0.0F, 0.0F, 0.0F);
		front_axis.addChild(tire4);
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 1.0F, -23.0F, 3, 7, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 7, 1.0F, 4.0F, -26.0F, 3, 1, 7, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 2, 1.0F, 3.0F, -25.0F, 3, 1, 2, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 2, 1.0F, 5.0F, -22.0F, 3, 1, 2, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 2, 1.0F, 3.0F, -22.0F, 3, 1, 2, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 2, 1.0F, 5.0F, -25.0F, 3, 1, 2, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 2.0F, -22.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 2.0F, -24.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 6.0F, -24.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 6.0F, -22.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 1.0F, -22.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 3.0F, -20.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 5.0F, -20.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 7.0F, -24.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 7.0F, -22.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 3.0F, -26.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 5.0F, -26.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 2.0F, -25.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 2.0F, -21.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 6.0F, -21.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 6.0F, -25.0F, 3, 1, 1, 0.0F, false));
		tire4.cubeList.add(new ModelBox(tire4, 0, 1, 1.0F, 1.0F, -24.0F, 3, 1, 1, 0.0F, false));

		back_axis = new ModelRenderer(this);
		back_axis.setRotationPoint(0.0F, 0.0F, 0.0F);
		f1.addChild(back_axis);
		back_axis.cubeList.add(new ModelBox(back_axis, 0, 1, 4.0F, 4.0F, 6.0F, 8, 1, 1, 0.0F, false));
		back_axis.cubeList.add(new ModelBox(back_axis, 0, 1, 4.0F, 3.0F, 6.0F, 8, 1, 1, 0.0F, false));

		tire = new ModelRenderer(this);
		tire.setRotationPoint(0.0F, 0.0F, 0.0F);
		back_axis.addChild(tire);
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 1.0F, 6.0F, 3, 7, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 7, 12.0F, 4.0F, 3.0F, 3, 1, 7, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 2, 12.0F, 3.0F, 4.0F, 3, 1, 2, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 2, 12.0F, 5.0F, 7.0F, 3, 1, 2, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 2, 12.0F, 3.0F, 7.0F, 3, 1, 2, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 2, 12.0F, 5.0F, 4.0F, 3, 1, 2, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 2.0F, 7.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 2.0F, 5.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 6.0F, 5.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 6.0F, 7.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 1.0F, 7.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 3.0F, 9.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 5.0F, 9.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 7.0F, 5.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 7.0F, 7.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 3.0F, 3.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 5.0F, 3.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 2.0F, 4.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 2.0F, 8.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 6.0F, 8.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 6.0F, 4.0F, 3, 1, 1, 0.0F, false));
		tire.cubeList.add(new ModelBox(tire, 0, 1, 12.0F, 1.0F, 5.0F, 3, 1, 1, 0.0F, false));

		tire2 = new ModelRenderer(this);
		tire2.setRotationPoint(0.0F, 0.0F, 0.0F);
		back_axis.addChild(tire2);
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 1.0F, 6.0F, 3, 7, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 7, 1.0F, 4.0F, 3.0F, 3, 1, 7, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 2, 1.0F, 3.0F, 4.0F, 3, 1, 2, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 2, 1.0F, 5.0F, 7.0F, 3, 1, 2, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 2, 1.0F, 3.0F, 7.0F, 3, 1, 2, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 2, 1.0F, 5.0F, 4.0F, 3, 1, 2, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 2.0F, 7.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 2.0F, 5.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 6.0F, 5.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 6.0F, 7.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 1.0F, 7.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 3.0F, 9.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 5.0F, 9.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 7.0F, 5.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 7.0F, 7.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 3.0F, 3.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 5.0F, 3.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 2.0F, 4.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 2.0F, 8.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 6.0F, 8.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 6.0F, 4.0F, 3, 1, 1, 0.0F, false));
		tire2.cubeList.add(new ModelBox(tire2, 0, 1, 1.0F, 1.0F, 5.0F, 3, 1, 1, 0.0F, false));

		wing = new ModelRenderer(this);
		wing.setRotationPoint(0.0F, 0.0F, 0.0F);
		f1.addChild(wing);
		wing.cubeList.add(new ModelBox(wing, 0, 9, 6.0F, 1.0F, 2.0F, 4, 4, 9, 0.0F, false));
		wing.cubeList.add(new ModelBox(wing, 0, 2, 6.0F, -2.0F, 9.0F, 4, 3, 2, 0.0F, false));
		wing.cubeList.add(new ModelBox(wing, 0, 5, 0.0F, -3.0F, 8.0F, 16, 1, 5, 0.0F, false));
		wing.cubeList.add(new ModelBox(wing, 0, 1, 5.0F, -2.0F, 11.0F, 6, 1, 1, 0.0F, false));
		wing.cubeList.add(new ModelBox(wing, 0, 4, 15.0F, -4.0F, 9.0F, 1, 1, 4, 0.0F, false));
		wing.cubeList.add(new ModelBox(wing, 0, 4, 0.0F, -4.0F, 9.0F, 1, 1, 4, 0.0F, false));
		wing.cubeList.add(new ModelBox(wing, 0, 1, 1.0F, -4.0F, 12.0F, 14, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f6, float f2, float f3, float f4, float f5) {
		f1.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
package electricexpansion.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electricexpansion.client.model.ModelInsulatedWire;
import electricexpansion.common.CommonProxy;
import electricexpansion.common.ElectricExpansion;
import electricexpansion.common.cables.TileEntityInsulatedWire;
import electricexpansion.common.cables.TileEntitySwitchWire;
import electricexpansion.common.helpers.TileEntityConductorBase;

@SideOnly(Side.CLIENT)
public class RenderInsulatedWire extends TileEntitySpecialRenderer
{
	private ModelInsulatedWire model;

	public RenderInsulatedWire()
	{
		model = new ModelInsulatedWire();
	}

	public void renderAModelAt(TileEntity t, double x, double y, double z, float f)
	{
		String textureToUse = null;
		int ID = t.worldObj.getBlockId((int) x,(int) y,(int) z);
		int meta = t.worldObj.getBlockMetadata((int)x, (int)y, (int)z);
		if (meta != -1)
		{
			if (ID == ElectricExpansion.insulatedWire)
			{
				if (meta == 0)
					textureToUse = CommonProxy.ATEXTURES + "InsulatedCopperWire.png";
				else if (meta == 1)
					textureToUse = CommonProxy.ATEXTURES + "InsulatedTinWire.png";
				else if (meta == 2)
					textureToUse = CommonProxy.ATEXTURES + "InsulatedSilverWire.png";
				else if (meta == 3)
					textureToUse = CommonProxy.ATEXTURES + "InsulatedHVWire.png";
				else if (meta == 4)
					textureToUse = CommonProxy.ATEXTURES + "InsulatedSCWire.png";
			}
			else if (ID == ElectricExpansion.SwitchWire && t.getWorldObj().isBlockIndirectlyGettingPowered(t.xCoord, t.yCoord, t.zCoord))
			{
				if (meta == 0)
					textureToUse = CommonProxy.ATEXTURES + "CopperSwitchWireOn.png";
				else if (meta == 1)
					textureToUse = CommonProxy.ATEXTURES + "TinSwitchWireOn.png";
				else if (meta == 2)
					textureToUse = CommonProxy.ATEXTURES + "SilverSwitchWireOn.png";
				else if (meta == 3)
					textureToUse = CommonProxy.ATEXTURES + "HVSwitchWireOn.png";
				else if (meta == 4)
					textureToUse = CommonProxy.ATEXTURES + "SCSwitchWireOn.png";
			}
			else if (ID == ElectricExpansion.SwitchWire && !(t.getWorldObj().isBlockIndirectlyGettingPowered(t.xCoord, t.yCoord, t.zCoord)))
			{
				if (meta == 0)
					textureToUse = CommonProxy.ATEXTURES + "CopperSwitchWireOff.png";
				else if (meta == 1)
					textureToUse = CommonProxy.ATEXTURES + "TinSwitchWireOff.png";
				else if (meta == 2)
					textureToUse = CommonProxy.ATEXTURES + "SilverSwitchWireOff.png";
				else if (meta == 3)
					textureToUse = CommonProxy.ATEXTURES + "HVSwitchWireOff.png";
				else if (meta == 4)
					textureToUse = CommonProxy.ATEXTURES + "SCSwitchWireOff.png";
			}
		}

		// Texture file
		bindTextureByName(textureToUse);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);

		TileEntityConductorBase tileEntity = (TileEntityConductorBase) t;
		boolean[] connectedSides = tileEntity.visuallyConnected;

		if (tileEntity instanceof TileEntityInsulatedWire)
		{
			if (connectedSides[0])
			{
				model.renderBottom();
			}
			if (connectedSides[1])
			{
				model.renderTop();
			}
			if (connectedSides[2])
			{
				model.renderBack();
			}
			if (connectedSides[3])
			{
				model.renderFront();
			}
			if (connectedSides[4])
			{
				model.renderLeft();
			}
			if (connectedSides[5])
			{
				model.renderRight();
			}
		}
		else if (tileEntity instanceof TileEntitySwitchWire && tileEntity.getWorldObj().isBlockIndirectlyGettingPowered(t.xCoord, t.yCoord, t.zCoord))
		{
			if (connectedSides[0])
			{
				model.renderBottom();
			}
			if (connectedSides[1])
			{
				model.renderTop();
			}
			if (connectedSides[2])
			{
				model.renderBack();
			}
			if (connectedSides[3])
			{
				model.renderFront();
			}
			if (connectedSides[4])
			{
				model.renderLeft();
			}
			if (connectedSides[5])
			{
				model.renderRight();
			}
		}

		model.renderMiddle();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double var2, double var4, double var6, float var8)
	{
		this.renderAModelAt(tileEntity, var2, var4, var6, var8);
	}
}

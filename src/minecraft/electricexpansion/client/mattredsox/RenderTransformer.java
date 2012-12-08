package electricexpansion.client.mattredsox;

import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

import electricexpansion.EECommonProxy;
import electricexpansion.ElectricExpansion;
import electricexpansion.alex_hawks.blocks.BlockWireMill;
import electricexpansion.mattredsox.blocks.BlockTransformer;

public class RenderTransformer extends TileEntitySpecialRenderer
{
	private ModelTransformer model;

	public RenderTransformer()
	{
		this.model = new ModelTransformer();

	}

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var3, double var4, float var5)
	{
		bindTextureByName(EECommonProxy.MattFILE_PATH + "transformer.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float) var2 + 0.5F, (float) var3 + 1.5F, (float) var4 + 0.5F);
		switch (var1.worldObj.getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord) - BlockTransformer.meta)
		{
			case 0: GL11.glRotatef(0, 0.0F, 1.0F, 0.0F); break;
			case 1: GL11.glRotatef(180, 0.0F, 1.0F, 0.0F); break;
			case 2: GL11.glRotatef(90, 0.0F, 1.0F, 0.0F); break;
			case 3: GL11.glRotatef(270, 0.0F, 1.0F, 0.0F);	break;
		}

		GL11.glScalef(1.0F, -1F, -1F);
		this.model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GL11.glPopMatrix();

	}

}


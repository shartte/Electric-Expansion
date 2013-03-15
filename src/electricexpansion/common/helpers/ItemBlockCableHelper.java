package electricexpansion.common.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import universalelectricity.core.electricity.ElectricityDisplay;
import universalelectricity.core.electricity.ElectricityDisplay.ElectricUnit;
import electricexpansion.api.EnumWireMaterial;
import electricexpansion.api.EnumWireType;
import electricexpansion.common.ElectricExpansion;

public abstract class ItemBlockCableHelper extends ItemBlock
{
	protected HashMap<String, Icon> icons = new HashMap<String, Icon>();
	
	public ItemBlockCableHelper(int id)
	{
		super(id);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return this.getUnlocalizedName() + "." + EnumWireMaterial.values()[itemStack.getItemDamage()].name;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List par3List, boolean par4)
	{
		par3List.add("Resistance: " + ElectricityDisplay.getDisplay(EnumWireMaterial.values()[itemstack.getItemDamage()].resistance, ElectricUnit.RESISTANCE));
		par3List.add("Max Amps: " + ElectricityDisplay.getDisplay(EnumWireMaterial.values()[itemstack.getItemDamage()].maxAmps, ElectricUnit.AMPERE));
	}

	@Override
	public void func_94581_a(IconRegister par1IconRegister)
	{
		for (int i = 0; i < EnumWireMaterial.values().length - 1; i++)
		{
			icons.put(this.getUnlocalizedName(new ItemStack(this.itemID, 1, i)), par1IconRegister.func_94245_a(
					this.getUnlocalizedName(new ItemStack(this.itemID, 1, i)).replaceAll("tile.", ElectricExpansion.TEXTURE_NAME_PREFIX)));
		}
	}
	
	@Override
	public Icon getIconFromDamage(int meta)
	{
		return this.icons.get(this.getUnlocalizedName(new ItemStack(this.itemID, 1, meta)));
	}
}

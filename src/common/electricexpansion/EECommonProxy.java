package electricexpansion;

import cpw.mods.fml.common.registry.GameRegistry;

import electricexpansion.alex_hawks.cables.TileEntityInsulatedWire;
import electricexpansion.alex_hawks.cables.TileEntityRawWire;
import electricexpansion.alex_hawks.cables.TileEntitySwitchWire;
import electricexpansion.alex_hawks.cables.TileEntitySwitchWireBlock;
import electricexpansion.alex_hawks.cables.TileEntitySwitchWireBlockOff;
import electricexpansion.alex_hawks.cables.TileEntitySwitchWireOff;
import electricexpansion.alex_hawks.cables.TileEntityWireBlock;
import electricexpansion.client.mattredsox.*;
import electricexpansion.mattredsox.*;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import universalelectricity.basiccomponents.TileEntityCopperWire;

public class EECommonProxy extends universalelectricity.prefab.CommonProxy
{
	

	//Mattredsox's Textures
	public static final String MattFILE_PATH = "/electricexpansion/textures/mattredsox/";
	public static final String MattBLOCK_TEXTURE_FILE = MattFILE_PATH + "blocks.png";
	public static final String MattBLOCK1_TEXTURE_FILE = MattFILE_PATH + "blocks1.png";
	public static final String MattItem_TEXTURE_FILE = MattFILE_PATH + "items.png";
        
	//Alex_hawks' Textures
	public static String ATEXTURES = "electricexpansion/textures/alex_hawks/textures/";
	public static String AITEMS = "/electricexpansion/textures/alex_hawks/textures/items.png";
	public static String ABLOCK = "/electricexpansion/textures/alex_hawks/textures/block.png";
	
	@Override
	public void init()
	{
		GameRegistry.registerTileEntity(TileEntityRawWire.class, "TileEntityRawWire");
		GameRegistry.registerTileEntity(TileEntityInsulatedWire.class, "TileEntityInsulatedWire");
		GameRegistry.registerTileEntity(TileEntityWireBlock.class, "TileEntityWireBlock");
		GameRegistry.registerTileEntity(TileEntitySwitchWire.class, "TileEntitySwitchWire");
		GameRegistry.registerTileEntity(TileEntitySwitchWireBlock.class, "TileEntitySwitchWireBlock");
		GameRegistry.registerTileEntity(TileEntitySwitchWireOff.class, "TileEntitySwitchWireOff");
		GameRegistry.registerTileEntity(TileEntitySwitchWireBlockOff.class, "TileEntitySwitchWireBlockOff");
		//GameRegistry.registerTileEntity(TileEntityRedstoneWire.class, "TileEntityRedstoneWire");
		//GameRegistry.registerTileEntity(TileEntityRedstoneWireBlock.class, "TileEntityRedstoneWireBlock");

	}
	
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null)
        {
			switch(ID)
			{
				case 0: return new GUIAdvBatteryBox(player.inventory, ((TileEntityAdvBatteryBox)tileEntity));
				case 1: return new GuiVoltDetector(player.inventory, (TileEntityVoltDetector)tileEntity);
				case 2: return new GuiWireMill(player.inventory, world, x, y, z);

			}
        }
		
		return null;
	}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null)
        {
			switch(ID)
			{
				case 0: return new ContainerAdvBatteryBox(player.inventory, ((TileEntityAdvBatteryBox)tileEntity));
				case 1: return new ContainerVoltDetector();
				case 2: return new ContainerWireMill(player.inventory, world, x, y, z);

			}
        }
		
		return null;
	}
}
	
	


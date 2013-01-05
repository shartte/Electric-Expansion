package electricexpansion.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import electricexpansion.common.cables.TileEntityInsulatedWire;
import electricexpansion.common.cables.TileEntityLogisticsWire;
import electricexpansion.common.cables.TileEntityRawWire;
import electricexpansion.common.cables.TileEntitySwitchWire;
import electricexpansion.common.cables.TileEntitySwitchWireBlock;
import electricexpansion.common.cables.TileEntityWireBlock;
import electricexpansion.common.containers.ContainerAdvBatteryBox;
import electricexpansion.common.containers.ContainerDistribution;
import electricexpansion.common.containers.ContainerWireMill;
import electricexpansion.common.tile.TileEntityAdvancedBatteryBox;
import electricexpansion.common.tile.TileEntityDistribution;
import electricexpansion.common.tile.TileEntityMultimeter;
import electricexpansion.common.tile.TileEntityTransformer;
import electricexpansion.common.tile.TileEntityWireMill;

public class CommonProxy implements IGuiHandler
{
	public void init()
	{
		// Alex_hawks' Tile entity registrations
		GameRegistry.registerTileEntity(TileEntityRawWire.class, "TileEntityRawWire");
		GameRegistry.registerTileEntity(TileEntityInsulatedWire.class, "TileEntityInsulatedWire");
		GameRegistry.registerTileEntity(TileEntityWireBlock.class, "TileEntityWireBlock");
		GameRegistry.registerTileEntity(TileEntitySwitchWire.class, "TileEntitySwitchWire");
		GameRegistry.registerTileEntity(TileEntitySwitchWireBlock.class, "TileEntitySwitchWireBlock");
		GameRegistry.registerTileEntity(TileEntityLogisticsWire.class, "TileEntityLogisticsWire");
		GameRegistry.registerTileEntity(TileEntityWireMill.class, "TileEntityWireMill");
		// GameRegistry.registerTileEntity(TileEntityRedstoneWire.class, "TileEntityRedstoneWire");
		// GameRegistry.registerTileEntity(TileEntityRedstoneWireBlock.class,
		// "TileEntityRedstoneWireBlock");

		// Mattredsox's Tile entity registrations
		GameRegistry.registerTileEntity(TileEntityAdvancedBatteryBox.class, "TileEntityAdvBox");
		GameRegistry.registerTileEntity(TileEntityMultimeter.class, "TileEntityVoltDet");
		GameRegistry.registerTileEntity(TileEntityTransformer.class, "TileEntityTransformer");
		GameRegistry.registerTileEntity(TileEntityDistribution.class, "TileEntityDistribution");
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity != null)
		{
			switch (ID)
			{
				case 0:
					return new ContainerAdvBatteryBox(player.inventory, ((TileEntityAdvancedBatteryBox) tileEntity));
				case 2:
					return new ContainerWireMill(player.inventory, (TileEntityWireMill) tileEntity);
				case 3:
					return null;
				case 4:
					return new ContainerDistribution(player.inventory, (TileEntityDistribution) tileEntity);
			}
		}
		return null;
	}
}

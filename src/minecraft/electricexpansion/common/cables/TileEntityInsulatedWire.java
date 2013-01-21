package electricexpansion.common.cables;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.network.PacketManager;

import com.google.common.io.ByteArrayDataInput;

import electricexpansion.common.ElectricExpansion;
import electricexpansion.common.helpers.TileEntityConductorBase;

public class TileEntityInsulatedWire extends TileEntityConductorBase

{
	/*
	 * -1 = none (default) 0 - black 1 - red 2 - dark green 3 - brown 4 - blue 5 - purple 6 - cyan 7
	 * - light gray 8 - dark gray 9 - pink 10 - light green 11 - yellow 12 - light blue 13 - magenta
	 * 14 - orange 15 - white
	 */

	public byte colorByte = -1;

	// everything is in the helper class.
	// this class MUST remain existent...

	@Override
	public void handlePacketData(INetworkManager network, int type, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream)
	{
		if (this.worldObj.isRemote)
		{
			try
			{
				byte id = dataStream.readByte();

				if (id == 1)
				{
					this.visuallyConnected[0] = dataStream.readBoolean();
					this.visuallyConnected[1] = dataStream.readBoolean();
					this.visuallyConnected[2] = dataStream.readBoolean();
					this.visuallyConnected[3] = dataStream.readBoolean();
					this.visuallyConnected[4] = dataStream.readBoolean();
					this.visuallyConnected[5] = dataStream.readBoolean();
				}

				if (id == 0)
				{
					this.colorByte = dataStream.readByte();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.colorByte = nbt.getByte("colorByte");

	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setByte("colorByte", this.colorByte);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		return PacketManager.getPacket(ElectricExpansion.CHANNEL, this, (byte) 1, this.visuallyConnected[0], this.visuallyConnected[1], this.visuallyConnected[2], this.visuallyConnected[3], this.visuallyConnected[4], this.visuallyConnected[5], this.colorByte);
	}

	public byte tick = 0;

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if (!this.worldObj.isRemote)
		{
			this.tick++;

			if (tick == 20)
			{
				tick = 0;

				if (this.colorByte != -1)
				{
					PacketManager.sendPacketToClients(PacketManager.getPacket(ElectricExpansion.CHANNEL, this, (byte) 0, this.colorByte), this.worldObj, new Vector3(this), 12);
				}
			}
		}

	}
}
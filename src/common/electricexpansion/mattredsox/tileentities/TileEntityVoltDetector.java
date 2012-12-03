package electricexpansion.mattredsox.tileentities;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.electricity.ElectricInfo;
import universalelectricity.core.implement.IConductor;
import universalelectricity.core.implement.IJouleStorage;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.tile.TileEntityElectricityReceiver;
import electricexpansion.mattredsox.blocks.BlockVoltDetector;

public class TileEntityVoltDetector extends TileEntityElectricityReceiver implements IJouleStorage
{
	private double joules = 0;

	public double voltIn;
	
	private boolean isFull = false;

	private int playersUsing = 0;


	public TileEntityVoltDetector()
	{
		super();
	}

	@Override
	public boolean canConnect(ForgeDirection side)
	{
		return side == ForgeDirection.getOrientation(this.getBlockMetadata() - BlockVoltDetector.VOLT_DET_METADATA + 2) || side == ForgeDirection.getOrientation(this.getBlockMetadata() - BlockVoltDetector.VOLT_DET_METADATA + 2).getOpposite();
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if (!this.isDisabled())
		{
			if (!this.worldObj.isRemote)
			{
				ForgeDirection inputDirection = ForgeDirection.getOrientation(this.getBlockMetadata() - BlockVoltDetector.VOLT_DET_METADATA + 2).getOpposite();
				TileEntity inputTile = Vector3.getTileEntityFromSide(this.worldObj, Vector3.get(this), inputDirection);

				if (inputTile != null)
				{
					if (inputTile instanceof IConductor)
					{
						if (this.joules >= this.getMaxJoules())
						{
							((IConductor) inputTile).getNetwork().stopRequesting(this);
						}
						else
						{
							((IConductor) inputTile).getNetwork().startRequesting(this, this.getMaxJoules() - this.getJoules(), this.getVoltage());
							this.setJoules(this.joules + ((IConductor) inputTile).getNetwork().consumeElectricity(this).getWatts());
						}
					}
				}
			}
			/**
			 * Output Electricity
			 */
			if (this.joules > 0)
			{
				ForgeDirection outputDirection = ForgeDirection.getOrientation(this.getBlockMetadata() - BlockVoltDetector.VOLT_DET_METADATA + 2);
				TileEntity tileEntity = Vector3.getTileEntityFromSide(this.worldObj, Vector3.get(this), outputDirection);

				if (tileEntity != null)
				{
					TileEntity connector = Vector3.getConnectorFromSide(this.worldObj, Vector3.get(this), outputDirection);

					// Output UE electricity
					if (connector instanceof IConductor)
					{
						double joulesNeeded = ((IConductor) connector).getNetwork().getRequest().getWatts();
						double transferAmps = Math.max(Math.min(Math.min(ElectricInfo.getAmps(joulesNeeded, this.getVoltage()), ElectricInfo.getAmps(this.joules, this.getVoltage())), 80), 0);

						if (!this.worldObj.isRemote && transferAmps > 0)
						{
							((IConductor) connector).getNetwork().startProducing(this, transferAmps, this.getVoltage());
							this.setJoules(this.joules - ElectricInfo.getWatts(transferAmps, this.getVoltage()));
							System.out.println("PROD");
						}
						else
						{
							((IConductor) connector).getNetwork().stopProducing(this);
						}

					}

				}
			}
		}
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		this.joules = par1NBTTagCompound.getDouble("electricityStored");
		this.voltIn = par1NBTTagCompound.getDouble("voltIn");
	}
	

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setDouble("electricityStored", this.joules);
		par1NBTTagCompound.setDouble("voltIn", this.voltIn);
	}

	
	public String getInvName()
	{
		return "Voltage Detector";
	}


	@Override
	public double getJoules(Object... data)
	{
		return this.joules;
	}

	@Override
	public void setJoules(double joules, Object... data)
	{
		this.joules = Math.max(Math.min(joules, this.getMaxJoules()), 0);
	}

	@Override
	public double getMaxJoules(Object... data)
	{
		return 100;
	}
	
	@Override
	public double getVoltage()
	{
		return voltIn;
	}

}

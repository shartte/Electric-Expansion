package electricexpansion.mattredsox;

import ic2.api.IElectricItem;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import universalelectricity.core.implement.IItemElectric;
import universalelectricity.prefab.SlotElectricItem;
import universalelectricity.prefab.modifier.IModifier;
import universalelectricity.prefab.modifier.SlotModifier;
import electricexpansion.mattredsox.items.ItemTransformerCoil;
import electricexpansion.mattredsox.tileentities.TileEntityAdvBatteryBox;
import electricexpansion.mattredsox.tileentities.TileEntityTransformer;

public class ContainerTransformer extends Container
{
    private TileEntityTransformer tileEntity;

    public ContainerTransformer(InventoryPlayer par1InventoryPlayer, TileEntityTransformer transformer)
    {
        this.tileEntity = transformer;
 
        this.addSlotToContainer(new SlotTransformerCoil(transformer, 0, 117, 30)); //Right slot
        this.addSlotToContainer(new SlotTransformerCoil(transformer, 1, 43, 30)); //Left slot

        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
        }
        
        tileEntity.openChest();
    }
    
    public void onCraftGuiClosed(EntityPlayer entityplayer)
    {
		super.onCraftGuiClosed(entityplayer);
		tileEntity.closeChest();
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.tileEntity.isUseableByPlayer(par1EntityPlayer);
    }
    
    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1)
    {
        return null;
    }
}
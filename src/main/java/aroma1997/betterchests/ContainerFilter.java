/**
 * The code of BetterChests and all related materials like textures is copyrighted material.
 * It may only be redistributed or used for Commercial purposes with the permission of Aroma1997.
 * 
 * All Rights reserved (c) by Aroma1997
 * 
 * See https://github.com/Aroma1997/BetterChests/blob/master/LICENSE.md for more information.
 */
package aroma1997.betterchests;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import aroma1997.core.inventories.ContainerBasic;
import aroma1997.core.inventories.ContainerItem;
import aroma1997.core.inventories.ISpecialInventory;

public class ContainerFilter extends ContainerItem {

	protected ContainerFilter(InventoryPlayer playerInv, ISpecialInventory inv,
			int c) {
		super(playerInv, inv, c, true);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public GuiContainer getContainer() {
		return new GUIFilter(this);
	}
	
	@Override
	public int getAmountRows() {
		return 3;
	}
	
	@Override
	public int getAmountPerRow() {
		return 3;
	}

}

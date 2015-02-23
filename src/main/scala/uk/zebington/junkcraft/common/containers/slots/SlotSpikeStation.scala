package uk.zebington.junkcraft.common.containers.slots

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.{IInventory, Slot}
import net.minecraft.item.ItemStack

/**
 * Created by Charlotte on 23/02/2015.
 */
class SlotSpikeStation(inv: IInventory, ind: Int, x: Int, y: Int) extends Slot(inv, ind, x, y) {
  override def onPickupFromSlot(player: EntityPlayer, itemStack: ItemStack): Unit = {
    super.onPickupFromSlot(player, itemStack)
    inv.decrStackSize(0, 1)
    inv.decrStackSize(1, 1)
  }

  override def isItemValid(stack: ItemStack) = false
}

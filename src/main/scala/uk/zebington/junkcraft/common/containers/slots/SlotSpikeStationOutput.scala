package uk.zebington.junkcraft.common.containers.slots

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.{ItemArmor, ItemStack}
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation
import uk.zebington.junkcraft.init.JCItems

/**
 * Created by Charlotte on 23/02/2015.
 */
class SlotSpikeStationOutput(te: TileEntitySpikeStation, ind: Int, x: Int, y: Int) extends Slot(te, ind, x, y) {
  override def onPickupFromSlot(player: EntityPlayer, itemStack: ItemStack): Unit = {
    super.onPickupFromSlot(player, itemStack)
    if (itemStack.getItem.isInstanceOf[ItemArmor]) {
      te.decrStackSize(0, 1)
      te.decrStackSize(1, 1)
    } else if (itemStack.getItem == JCItems.Spikes) {
      te.nStored -= te.SpikyItems(te.stored)
      if (te.nStored <= 0) {
        te.stored = null
        te.nStored = 0
      }
    }
  }

  override def isItemValid(stack: ItemStack) = false
}

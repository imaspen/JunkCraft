package uk.zebington.junkcraft.common.containers.slots

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.{ItemArmor, ItemStack}
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation
import uk.zebington.junkcraft.init.JCItems

import scala.util.control.Breaks.break

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
      for (i <- 0 to 1) if (te.getStackInSlot(i) != null) {
        val stack = te.getStackInSlot(i)
        te.decrStackSize(i, if (te.SpikyItems.contains(stack.getItem)) te.SpikyItems.get(stack.getItem).asInstanceOf[Int] else 64)
        break()
      }
    }
  }

  override def isItemValid(stack: ItemStack) = false
}

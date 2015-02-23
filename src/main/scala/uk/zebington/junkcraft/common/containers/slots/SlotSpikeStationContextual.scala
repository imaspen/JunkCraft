package uk.zebington.junkcraft.common.containers.slots

import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation

/**
 * Created by Charlotte on 23/02/2015.
 */
class SlotSpikeStationContextual(te: TileEntitySpikeStation, ind: Int, x: Int, y: Int) extends Slot(te, ind, x, y) {
  override def isItemValid(stack: ItemStack) = te.mode == 0
}

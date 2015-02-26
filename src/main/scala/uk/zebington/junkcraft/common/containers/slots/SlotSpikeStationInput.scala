package uk.zebington.junkcraft.common.containers.slots

import net.minecraft.inventory.Slot
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation

/**
 * Created by Charlotte on 23/02/2015.
 */
class SlotSpikeStationInput(te: TileEntitySpikeStation, ind: Int, x: Int, y: Int) extends Slot(te, ind, x, y) {

  override def onSlotChanged(): Unit = {
    if (te.mode == 1) while (te.inv(0) != null) {
      if (te.nStored == 0) {
        te.stored = te.inv(0).getItem
        te.nStored += 1
        te.decrStackSize(0, 1)
        te.markDirty()
      } else if (te.stored == te.inv(0).getItem) {
        te.nStored += 1
        te.decrStackSize(0, 1)
        te.markDirty()
      }
    }
  }
}

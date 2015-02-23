package uk.zebington.junkcraft.common.containers

import net.minecraft.entity.player.{EntityPlayer, InventoryPlayer}
import net.minecraft.inventory._
import uk.zebington.junkcraft.common.containers.slots.SlotSpikeStation
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation

/**
 * Created by Charlotte on 22/02/2015.
 */
class ContainerSpikeStation(inv: TileEntitySpikeStation, invPlayer: InventoryPlayer) extends Container {

  addSlotToContainer(new Slot(inv, 0, 30, 35))
  addSlotToContainer(new Slot(inv, 1, 66, 35))
  addSlotToContainer(new SlotSpikeStation(inv, 2, 126, 35))

  for (i <- 0 until 3) for (j <- 0 until 9) addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18))
  for (i <- 0 until 9) addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142))

  override def canInteractWith(playerIn: EntityPlayer): Boolean = true

  override def transferStackInSlot(player: EntityPlayer, i: Int) = null

  override def detectAndSendChanges(): Unit = {
    super.detectAndSendChanges()
    inv.updateOutput()
  }
}

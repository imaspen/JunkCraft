package uk.zebington.junkcraft.common.containers

import net.minecraft.entity.player.{EntityPlayer, InventoryPlayer}
import net.minecraft.inventory._
import net.minecraft.item.ItemStack
import uk.zebington.junkcraft.common.containers.slots.{SlotSpikeStationContextual, SlotSpikeStationOutput}
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation

/**
 * Created by Charlotte on 22/02/2015.
 */
class ContainerSpikeStation(inv: TileEntitySpikeStation, invPlayer: InventoryPlayer) extends Container {

  addSlotToContainer(new Slot(inv, 0, 30, 35))
  addSlotToContainer(new SlotSpikeStationContextual(inv, 1, 66, 35))
  addSlotToContainer(new SlotSpikeStationOutput(inv, 2, 126, 35))

  for (i <- 0 until 3) for (j <- 0 until 9) addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18))
  for (i <- 0 until 9) addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142))

  override def canInteractWith(playerIn: EntityPlayer): Boolean = true

  override def transferStackInSlot(player: EntityPlayer, i: Int): ItemStack = {
    val slot = inventorySlots.get(i).asInstanceOf[Slot]
    var itemStack: ItemStack = null
    inv.mode match {
      case 0 =>
        if (slot != null && slot.getHasStack) {
          val itemStack1: ItemStack = slot.getStack
          itemStack = itemStack1.copy()
          if (i == 2) {
            if (!this.mergeItemStack(itemStack1, 3, 39, true)) {
              slot.onPickupFromSlot(player, itemStack1)
              slot.putStack(null)
              return null
            }
            slot.onSlotChange(itemStack1, itemStack)
          } else if (i > 2) {
            if (!this.mergeItemStack(itemStack1, 0, 2, false)) {
              slot.putStack(null)
              return null
            }
            slot.onSlotChange(itemStack1, itemStack)
          } else {
            if (!this.mergeItemStack(itemStack1, 3, 39, true)) {
              slot.putStack(null)
              return null
            }
          }
        }
      case 1 =>
        if (slot != null && slot.getHasStack) {
          val itemStack1: ItemStack = slot.getStack
          itemStack = itemStack1.copy()
          if (i == 2) {
            if (!this.mergeItemStack(itemStack1, 3, 39, true)) {
              slot.onPickupFromSlot(player, itemStack1)
              slot.putStack(null)
              return null
            }
            slot.onSlotChange(itemStack1, itemStack)
          } else if (i > 2) {
            if (!this.mergeItemStack(itemStack1, 0, 1, false)) {
              slot.putStack(null)
              return null
            }
            slot.onSlotChange(itemStack1, itemStack)
          } else {
            if (!this.mergeItemStack(itemStack1, 3, 39, true)) {
              slot.onPickupFromSlot(player, itemStack1)
              slot.putStack(null)
              return null
            }
          }
        }
    }
    itemStack
  }

  override def detectAndSendChanges(): Unit = {
    super.detectAndSendChanges()
    inv.updateOutput()
  }

  override def slotClick(slotId: Int, clickedButton: Int, mode: Int, playerIn: EntityPlayer): ItemStack = {
    val itemStack = super.slotClick(slotId, clickedButton, mode, playerIn)
    inv.updateOutput()
    itemStack
  }

  def switchMode(): Unit = {
    inv.switchMode()
  }
}

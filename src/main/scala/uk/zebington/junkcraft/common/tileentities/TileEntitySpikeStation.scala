package uk.zebington.junkcraft.common.tileentities

import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.{ItemArmor, Item, ItemStack}
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IChatComponent
import uk.zebington.junkcraft.init.JCItems

/**
 * Created by Charlotte on 22/02/2015.
 */
class TileEntitySpikeStation extends TileEntity with IInventory {
  val SpikyItems: List[Item] = List(JCItems.Knife)

  var inv = new Array[ItemStack](3)

  override def getSizeInventory: Int = inv.length

  override def decrStackSize(i: Int, c: Int): ItemStack = if (this.inv(i) != null) {
    var itemstack: ItemStack = null
    if (i == 2) {
      inv(0).stackSize -= 1
      inv(1).stackSize -= 1
      for (i <- 0 to 1) if (inv(0) != null & inv(0).stackSize <= 0) inv(0) == null
    }

    if (this.inv(i).stackSize <= c) {
      itemstack = this.inv(i)
      this.inv(i) = null
      markDirty()
      itemstack
    } else {
      itemstack = this.inv(i).splitStack(i)
      if (this.inv(i).stackSize == 0) this.inv(i) = null
      markDirty()
      itemstack
    }
  } else null

  override def closeInventory(playerIn: EntityPlayer) {}

  override def getInventoryStackLimit: Int = 64

  override def clear() {
    for (i <- 0 until inv.length) {
      inv(i) = null
    }
  }

  override def isItemValidForSlot(index: Int, stack: ItemStack): Boolean = index != getSizeInventory - 1

  override def getStackInSlotOnClosing(i: Int): ItemStack = inv(i)

  override def openInventory(player: EntityPlayer) {}

  override def getFieldCount: Int = 0

  override def getField(id: Int): Int = 0

  override def setInventorySlotContents(i: Int, stack: ItemStack): Unit = inv(i) = stack

  override def isUseableByPlayer(playerIn: EntityPlayer): Boolean = true

  override def getStackInSlot(i: Int): ItemStack = inv(i)

  override def setField(id: Int, value: Int): Unit = {}

  override def getDisplayName: IChatComponent = null

  override def getName: String = "Spike Station"

  override def hasCustomName: Boolean = false

  def updateOutput(): Unit = if (inv(0) != null & inv(1) != null) {
    if (SpikyItems.contains(inv(0).getItem) & inv(1).getItem.isInstanceOf[ItemArmor] | SpikyItems.contains(inv(1).getItem) & inv(0).getItem.isInstanceOf[ItemArmor]) {
      if (inv(0).getItem.isInstanceOf[ItemArmor]) inv(2) = inv(0).copy() else inv(2) = inv(1).copy()
      inv(2).addEnchantment(Enchantment.thorns, 1)
    }
  }
}

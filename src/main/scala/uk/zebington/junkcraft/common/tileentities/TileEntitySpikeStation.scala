package uk.zebington.junkcraft.common.tileentities

import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.inventory.IInventory
import net.minecraft.item.{Item, ItemArmor, ItemStack}
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IChatComponent
import net.minecraftforge.fml.common.network.NetworkRegistry
import uk.zebington.junkcraft.handlers.JCPacketHandler
import uk.zebington.junkcraft.init.JCItems
import uk.zebington.junkcraft.messages.MessageSpikeStation

/**
 * Created by Charlotte on 22/02/2015.
 */
class TileEntitySpikeStation extends TileEntity with IInventory {
  val SpikyItems: Map[Item, Int] = Map(
    JCItems.Knife -> 8,
    JCItems.Stabber -> 4,
    Items.iron_sword -> 4,
    Items.diamond_sword -> 2
  )

  var mode: Byte = 0
  var inv = new Array[ItemStack](3)
  var stored: Item = null
  var nStored: Int = 0

  override def decrStackSize(i: Int, c: Int): ItemStack = if (this.inv(i) != null) {
    var itemstack: ItemStack = null

    if (this.inv(i).stackSize <= c) {
      itemstack = this.inv(i)
      this.inv(i) = null
      markDirty()
    } else {
      itemstack = this.inv(i).splitStack(i)
      if (this.inv(i).stackSize == 0) this.inv(i) = null
      markDirty()
    }

    itemstack
  } else null

  override def closeInventory(playerIn: EntityPlayer) {}

  override def getInventoryStackLimit: Int = 64

  override def clear() {
    for (i <- 0 until inv.length) {
      inv(i) = null
    }
    markDirty()
  }

  override def isItemValidForSlot(index: Int, stack: ItemStack): Boolean = index != getSizeInventory - 1

  override def getSizeInventory: Int = inv.length

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

  def updateOutput(): Unit = {
    if (mode == 0 & inv(0) != null & inv(1) != null) {
      if (mode == 0 & inv(0).getItem == JCItems.Knife & inv(1).getItem.isInstanceOf[ItemArmor] | inv(1).getItem == JCItems.Spikes & inv(0).getItem.isInstanceOf[ItemArmor]) {
        if (inv(0).getItem.isInstanceOf[ItemArmor]) inv(2) = inv(0).copy() else inv(2) = inv(1).copy()
        inv(2).addEnchantment(Enchantment.thorns, 1)
      } else if (mode == 1) for (i <- 0 to 1) if (SpikyItems.contains(inv(i).getItem) & inv(i).stackSize >= SpikyItems(inv(i).getItem)) inv(2) = new ItemStack(JCItems.Spikes)
    }
  }

  override def getDescriptionPacket = JCPacketHandler.Instance.getPacketFrom(new MessageSpikeStation(this))

  def switchMode(): Unit = {
    mode = mode match {
      case 0 => markDirty(); 1
      case 1 => markDirty(); 0
    }
    markDirty()
  }

  override def markDirty() {
    super.markDirty()
    if (worldObj.isRemote) JCPacketHandler.Instance.sendToAllAround(new MessageSpikeStation(this), new NetworkRegistry.TargetPoint(this.worldObj.provider.getDimensionId, pos.getX, pos.getY, pos.getZ, 128d))
  }
}

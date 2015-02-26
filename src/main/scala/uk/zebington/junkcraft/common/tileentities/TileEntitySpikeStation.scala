package uk.zebington.junkcraft.common.tileentities

import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.inventory.IInventory
import net.minecraft.item.{Item, ItemArmor, ItemStack}
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.server.gui.IUpdatePlayerListBox
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{ChatComponentText, ChatComponentTranslation, IChatComponent}
import uk.zebington.junkcraft.handlers.JCPacketHandler
import uk.zebington.junkcraft.init.JCItems
import uk.zebington.junkcraft.messages.MessageSpikeStation

/**
 * Created by Charlotte on 22/02/2015.
 */
class TileEntitySpikeStation extends TileEntity with IInventory with IUpdatePlayerListBox {
  val SpikyItems: Map[Item, Int] = Map(
    JCItems.Knife -> 8,
    JCItems.Stabber -> 4,
    Items.iron_sword -> 4,
    Items.diamond_sword -> 2
  )

  var scheduleSwitchMode = false
  var mode: Byte = 0
  var inv = new Array[ItemStack](3)
  var stored: Item = null
  var nStored: Int = 0
  var flag1 = false

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

  override def getDisplayName: IChatComponent = (if (this.hasCustomName) new ChatComponentText(this.getName) else new ChatComponentTranslation(this.getName, new Array[AnyRef](0))).asInstanceOf[IChatComponent]

  override def getName: String = "Spike Station"

  override def hasCustomName: Boolean = false

  def updateOutput(): Unit = {
    if (mode == 0 & inv(0) != null & inv(1) != null) {
      if (mode == 0 & inv(0).getItem == JCItems.Spikes & inv(1).getItem.isInstanceOf[ItemArmor] | inv(1).getItem == JCItems.Spikes & inv(0).getItem.isInstanceOf[ItemArmor]) {
        if (inv(0).getItem.isInstanceOf[ItemArmor]) inv(2) = inv(0).copy() else inv(2) = inv(1).copy()
        inv(2).addEnchantment(Enchantment.thorns, 1)
      }
    }
  }

  override def getDescriptionPacket = JCPacketHandler.Instance.getPacketFrom(new MessageSpikeStation(this))

  def switchMode(): Unit = {
    mode = mode match {
      case 0 => 1
      case 1 => inv(1) = null; 0
    }
    markDirty()

    if (worldObj.isRemote) {
      JCPacketHandler.Instance.sendToServer(new MessageSpikeStation(this))
    }
  }

  override def update(): Unit = {
    if (scheduleSwitchMode) {
      if (!worldObj.isRemote)
        JCPacketHandler.Instance.sendToAll(new MessageSpikeStation(this))
      if (worldObj.isRemote) {
        scheduleSwitchMode = false
        JCPacketHandler.Instance.sendToServer(new MessageSpikeStation(this))
      }
    } else {
      var flag = false
      if (mode == 0) {
        if (inv(0) == null | inv(1) == null) {
          inv(2) = null
          markDirty()
        }
      } else if (mode == 1) {
        if (inv(0) != null && inv(0).stackSize > 0 && SpikyItems.contains(inv(0).getItem)) {
          if (stored == null) {
            stored = inv(0).getItem
            nStored = 1
            flag = true
          } else if (stored == inv(0).getItem) {
            nStored += 1
            flag = true
          }
          if (flag) {
            decrStackSize(0, 1)
            markDirty()
          }
        }

        if (stored != null) {
          inv(1) = new ItemStack(stored)
          inv(2) = if (nStored >= SpikyItems(stored)) new ItemStack(JCItems.Spikes) else null
        } else {
          for (i <- 1 to 2) inv(i) = null
        }
      }
    }
  }

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

  override def writeToNBT(compound: NBTTagCompound): Unit = {
    super.writeToNBT(compound)
    val stack: NBTTagCompound = new NBTTagCompound
    for (i <- 0 to 2) {
      if (this.inv(i) != null) {
        this.inv(i).writeToNBT(stack)
        compound.setTag(s"stack$i", stack)
      }
    }
    if (stored != null) {
      compound.setInteger("stored", Item.getIdFromItem(this.stored))
      compound.setInteger("nstored", this.nStored)
    }
    compound.setByte("mode", this.mode)
  }

  override def readFromNBT(compound: NBTTagCompound): Unit = {
    super.readFromNBT(compound)
    for (i <- 0 to 2) {
      this.inv(i) = if (compound hasKey s"stack$i") ItemStack loadItemStackFromNBT compound.getCompoundTag(s"stack$i") else null
    }
    if (compound hasKey "stored") {
      this.stored = Item getItemById (compound getInteger "stored")
      this.nStored = compound getInteger "nstored"
    } else {
      this.stored = null
      this.nStored = 0
    }
    if (compound.getByte("mode") == 1) {
      mode = 1
      scheduleSwitchMode = true
    }
  }
}
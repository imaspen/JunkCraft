package uk.zebington.junkcraft.messages

import io.netty.buffer.ByteBuf
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.BlockPos
import net.minecraftforge.fml.client.FMLClientHandler
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.network.simpleimpl.{IMessage, IMessageHandler, MessageContext}
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation

/**
 * Created by Charlotte on 22/02/2015.
 */
class MessageSpikeStation extends IMessage with IMessageHandler[MessageSpikeStation, IMessage] {
  var x, y, z: Int = 0
  var inv = new Array[ItemStack](3)
  var scheduleSwitchMode: Boolean = false
  var mode: Byte = 0
  var stored: Item = null
  var nStored: Int = 0

  def this(te: TileEntitySpikeStation) {
    this
    this.x = te.getPos.getX
    this.y = te.getPos.getY
    this.z = te.getPos.getZ

    this.inv = te.inv

    this.scheduleSwitchMode = te.scheduleSwitchMode

    this.mode = te.mode
    this.stored = te.stored
    this.nStored = te.nStored
  }

  override def fromBytes(buf: ByteBuf): Unit = {
    this.x = buf readInt()
    this.y = buf readInt()
    this.z = buf readInt()

    this.scheduleSwitchMode = buf readBoolean()

    var id = 0
    for (i <- 0 to 2) {
      id = buf readInt()
      if (id > 0) this.inv(i) = new ItemStack(Item.getItemById(id), buf.readInt(), buf.readInt())
    }

    this.mode = buf readByte()

    id = buf readInt()
    if (id > 0) {
      this.stored = Item getItemById id
      this.nStored = buf readInt()
    }
  }

  override def toBytes(buf: ByteBuf): Unit = {
    buf writeInt this.x
    buf writeInt this.y
    buf writeInt this.z

    buf writeBoolean this.scheduleSwitchMode

    for (i <- 0 to 2) {
      if (inv(i) != null) {
        buf writeInt Item.getIdFromItem(inv(i).getItem)
        buf writeInt inv(i).stackSize
        buf writeInt inv(i).getMetadata
      } else buf writeInt 0
    }

    buf writeByte this.mode

    if (stored != null) {
      buf writeInt Item.getIdFromItem(this.stored)
      buf writeInt this.nStored
    } else buf writeInt 0
  }

  override def onMessage(msg: MessageSpikeStation, ctx: MessageContext): IMessage = {
    val TE = if (ctx.side.isClient) {
      FMLClientHandler.instance().getClient.theWorld.getTileEntity(new BlockPos(msg.x, msg.y, msg.z))
    } else {
      FMLCommonHandler.instance().getMinecraftServerInstance.getEntityWorld.getTileEntity(new BlockPos(msg.x, msg.y, msg.z))
    }
    TE match {
      case te: TileEntitySpikeStation =>
        println("ahem")
        te.scheduleSwitchMode = msg.scheduleSwitchMode
        te.inv = msg.inv
        te.mode = msg.mode
        te.stored = msg.stored
        te.nStored = msg.nStored
      case _ =>
    }
    null
  }
}

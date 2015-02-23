package uk.zebington.junkcraft.messages

import io.netty.buffer.ByteBuf
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.BlockPos
import net.minecraftforge.fml.client.FMLClientHandler
import net.minecraftforge.fml.common.network.simpleimpl.{IMessage, IMessageHandler, MessageContext}
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation

/**
 * Created by Charlotte on 22/02/2015.
 */
class MessageSpikeStation extends IMessage with IMessageHandler[MessageSpikeStation, IMessage] {
  var x, y, z: Int = 0
  // 0 = itemID, 1 = stackSize, 2 = metaData
  var items: Array[Array[Int]] = Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))

  def this(te: TileEntitySpikeStation) {
    this()
    x = te.getPos.getX
    y = te.getPos.getY
    z = te.getPos.getZ

    items = Array(
      if (te.inv(0) != null) Array(Item.getIdFromItem(te.inv(0).getItem), te.inv(0).stackSize, te.inv(0).getMetadata) else Array(0, 0, 0),
      if (te.inv(0) != null) Array(Item.getIdFromItem(te.inv(1).getItem), te.inv(1).stackSize, te.inv(1).getMetadata) else Array(0, 0, 0),
      if (te.inv(0) != null) Array(Item.getIdFromItem(te.inv(2).getItem), te.inv(2).stackSize, te.inv(2).getMetadata) else Array(0, 0, 0)
    )
  }

  override def onMessage(message: MessageSpikeStation, ctx: MessageContext): IMessage = FMLClientHandler.instance().getClient.theWorld.getTileEntity(new BlockPos(message.x, message.y, message.z)) match {
    case te: TileEntitySpikeStation =>
      for (i <- 0 to 2) te.inv(i) = if (message.items(i)(0) == 0) null else new ItemStack(Item.getItemById(message.items(i)(0)), message.items(i)(1), message.items(i)(2))
      null
    case _ => null
  }

  override def fromBytes(buf: ByteBuf): Unit = {
    x = buf readInt()
    y = buf readInt()
    z = buf readInt()
    for (i <- 0 to 2) for (j <- 0 to 2) items(i)(j) = buf.readInt()
  }

  override def toBytes(buf: ByteBuf): Unit = {
    buf writeInt x
    buf writeInt y
    buf writeInt z
    for (i <- 0 to 2) for (j <- 0 to 2) buf writeInt items(i)(j)
  }
}

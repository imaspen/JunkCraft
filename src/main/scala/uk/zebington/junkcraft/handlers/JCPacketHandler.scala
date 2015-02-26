package uk.zebington.junkcraft.handlers

import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.relauncher.Side
import uk.zebington.junkcraft._
import uk.zebington.junkcraft.messages.MessageSpikeStation

/**
 * Created by Charlotte on 22/02/2015.
 */
object JCPacketHandler {
  val Instance = NetworkRegistry.INSTANCE.newSimpleChannel(Id)

  def init() {
    Instance.registerMessage(classOf[MessageSpikeStation], classOf[MessageSpikeStation], 0, Side.CLIENT)
    Instance.registerMessage(classOf[MessageSpikeStation], classOf[MessageSpikeStation], 1, Side.SERVER)
  }
}

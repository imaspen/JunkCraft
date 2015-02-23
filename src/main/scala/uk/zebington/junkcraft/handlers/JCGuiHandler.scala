package uk.zebington.junkcraft.handlers

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler
import uk.zebington.junkcraft._
import uk.zebington.junkcraft.client.gui.GuiSpikeStation
import uk.zebington.junkcraft.common.containers.ContainerSpikeStation
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation


/**
 * Created by Charlotte on 22/02/2015.
 */
class JCGuiHandler extends IGuiHandler {
  override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = ID match {
    case GSpikeStation =>
      world.getTileEntity(new BlockPos(x, y, z)) match {
        case station: TileEntitySpikeStation => new ContainerSpikeStation(station, player.inventory)
        case station => println(s"$x, $y, $z"); null
      }
    case _ => null
  }

  override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = ID match {
    case GSpikeStation =>
      world.getTileEntity(new BlockPos(x, y, z)) match {
        case station: TileEntitySpikeStation => new GuiSpikeStation(station, player.inventory)
      }
  }
}

package uk.zebington.junkcraft.proxy

import net.minecraftforge.fml.common.registry.GameRegistry
import uk.zebington.junkcraft._
import uk.zebington.junkcraft.common.tileentities.{TileEntitySpikeStation, TileEntityElectricFence}

/**
 * Created by Charlotte on 19/02/2015.
 */
class CommonProxy {
  def registerRenderers() {}

  def registerTileEntities() {
    GameRegistry registerTileEntity(classOf[TileEntityElectricFence], s"${Id}_$NElectricFence")
    GameRegistry registerTileEntity(classOf[TileEntitySpikeStation], s"${Id}_$NSpikeStation")
  }
}

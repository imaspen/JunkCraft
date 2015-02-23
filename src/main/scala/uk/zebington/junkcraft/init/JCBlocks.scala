package uk.zebington.junkcraft.init

import net.minecraftforge.fml.common.registry.GameRegistry
import uk.zebington.junkcraft._
import uk.zebington.junkcraft.common.blocks.{BlockSpikeStation, BlockElectricFence}

/**
 * Created by Charlotte on 21/02/2015.
 */
object JCBlocks {
  final val ElectricFence = new BlockElectricFence
  final val SpikeStation = new BlockSpikeStation

  def init() {
    GameRegistry registerBlock(ElectricFence, NElectricFence)
    GameRegistry registerBlock(SpikeStation, NSpikeStation)
  }
}

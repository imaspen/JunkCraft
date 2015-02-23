package uk.zebington.junkcraft.common.blocks

import net.minecraft.block.material.Material
import net.minecraft.block.{BlockPane, ITileEntityProvider}
import net.minecraft.world.World
import uk.zebington.junkcraft._
import uk.zebington.junkcraft.common.tileentities.TileEntityElectricFence

/**
 * Created by Charlotte on 21/02/2015.
 */
class BlockElectricFence extends BlockPane(Material.iron, true) with ITileEntityProvider with BlockJC {
  setUnlocalizedName(NElectricFence)

  override def createNewTileEntity(worldIn: World, meta: Int) = new TileEntityElectricFence
}

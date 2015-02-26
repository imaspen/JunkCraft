package uk.zebington.junkcraft.common.blocks

import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.block.{Block, ITileEntityProvider}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{BlockPos, EnumFacing}
import net.minecraft.world.World
import uk.zebington.junkcraft._
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation

/**
 * Created by Charlotte on 22/02/2015.
 */
class BlockSpikeStation extends Block(Material.rock) with BlockJC with ITileEntityProvider {
  setUnlocalizedName(NSpikeStation)

  override def createNewTileEntity(worldIn: World, meta: Int): TileEntity = new TileEntitySpikeStation

  override def isOpaqueCube = false

  override def isFullCube = false

  override def onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, side: EnumFacing, x: Float, y: Float, z: Float): Boolean = {
    if (player.isSneaking) return false
    if (world.isRemote) true
    else {
      val tileentity: TileEntity = world.getTileEntity(pos)
      if (tileentity.isInstanceOf[TileEntitySpikeStation]) {
        player.openGui(JunkCraft, GSpikeStation, world, pos.getX, pos.getY, pos.getZ)
      }
      true
    }
  }
}

package uk.zebington.junkcraft.common.tileentities

import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.{Entity, EntityLivingBase}
import net.minecraft.server.gui.IUpdatePlayerListBox
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{AxisAlignedBB, DamageSource}

import scala.collection.JavaConversions._

/**
 * Created by Charlotte on 22/02/2015.
 */
class TileEntityElectricFence extends TileEntity with IUpdatePlayerListBox {

  override def update(): Unit = {
    val aabb = new AxisAlignedBB(pos.getX, pos.getY, pos.getZ, pos.getX + 1, pos.getY + 1.1, pos.getZ + 1)

    worldObj.getEntitiesWithinAABB(classOf[Entity], aabb).toList.foreach {
      case e: EntityItem => e.setDead()
      case e: EntityLivingBase => e.attackEntityFrom(DamageSource.onFire, 2)
      case _ =>
    }
  }
}

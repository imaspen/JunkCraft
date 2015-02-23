package uk.zebington.junkcraft.common.items

import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.AxisAlignedBB
import net.minecraft.world.World
import scala.collection.JavaConversions._

/**
 * Created by Charlotte on 21/02/2015.
 */
trait ItemPicker extends ItemJC {
  override def onItemRightClick(itemStack: ItemStack, world: World, player: EntityPlayer) = {

    val aabb = new AxisAlignedBB(player.posX - 0.5, player.posY + 0.0, player.posZ - 0.5,
                                 player.posX + 0.5, player.posY + 1.0, player.posZ + 0.5).expand(10, 10, 10)

    world.getEntitiesWithinAABB(classOf[EntityItem], aabb).toList.foreach {
      case e: EntityItem =>
        e setPickupDelay 0
        e onCollideWithPlayer player
      case _ =>
    }
    itemStack
  }
}

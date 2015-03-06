package uk.zebington.junkcraft.common.items

import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.Item.ToolMaterial
import net.minecraft.item.{ItemStack, ItemSword}
import uk.zebington.junkcraft._

/**
 * Created by Charlotte on 06/03/2015.
 */
class ZombieArm extends ItemSword(ToolMaterial.WOOD) {
  setUnlocalizedName(NZombieArm)

  override def onEntitySwing(entityLiving: EntityLivingBase, itemStack: ItemStack): Boolean = {
    true
  }
}

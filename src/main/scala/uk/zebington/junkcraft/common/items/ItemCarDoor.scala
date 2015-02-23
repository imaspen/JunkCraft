package uk.zebington.junkcraft.common.items

import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.Item.ToolMaterial
import net.minecraft.item.{ItemStack, ItemSword}
import uk.zebington.junkcraft._

/**
 * Created by Charlotte on 21/02/2015.
 */
class ItemCarDoor extends ItemSword(ToolMaterial.STONE) with ItemJC {
  setUnlocalizedName(NCarDoor)

  override def getIsRepairable(toRepair: ItemStack, repair: ItemStack) = false

  override def hitEntity(stack: ItemStack, target: EntityLivingBase, attacker: EntityLivingBase): Boolean = {
    super.hitEntity(stack, target, attacker)

    var d1: Double = attacker.posX - target.posX
    var d0: Double = attacker.posZ - target.posZ

    while (d1 * d1 + d0 * d0 < 1.0E-4D) {
      d1 = (Math.random - Math.random) * 0.01D
      d0 = (Math.random - Math.random) * 0.01D
    }

    target.knockBack(attacker, 7, d1, d0)
    true
  }
}

package uk.zebington.junkcraft.handlers

import net.minecraft.entity.monster.{EntityMob, EntityZombie}
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import uk.zebington.junkcraft.init.JCItems

import scala.util.Random

/**
 * Created by Charlotte on 22/02/2015.
 */
class JCEventHandler {

  @SubscribeEvent def onLivingDeath(e: LivingDeathEvent) = if (!e.entity.worldObj.isRemote) {
    e.entityLiving match {
      case mob: EntityMob =>
        if (Random.nextFloat() < 0.1) mob.entityDropItem(new ItemStack(JCItems.Knife), 0)
        if (Random.nextFloat() < 0.1) mob.entityDropItem(new ItemStack(JCItems.PickerUpper), 0)
        mob match {
          case zombie: EntityZombie =>
            if (Random.nextFloat() < 0.1) zombie.entityDropItem(new ItemStack(JCItems.ZombieArm), 0)
          case _ =>
        }
      case _ =>
    }
  }
}

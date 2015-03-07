package uk.zebington.junkcraft.common.entities

import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.projectile.EntityThrowable
import net.minecraft.util.{DamageSource, MovingObjectPosition}
import net.minecraft.world.World

/**
 * Created by Charlotte on 06/03/2015.
 */
class EntityZombieArmAttack(world: World, entityLiving: EntityLivingBase, damage: Float) extends EntityThrowable(world, entityLiving) {

  override def onImpact(mvObjPos: MovingObjectPosition): Unit = {
    if (mvObjPos.entityHit != null && entityLiving.getDistance(posX, posY, posZ) <= 4) entityLiving match {
      case player: EntityPlayer => mvObjPos.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(player), damage)
      case _ => mvObjPos.entityHit.attackEntityFrom(DamageSource.causeMobDamage(entityLiving), damage)
    }

    setDead()
  }

  override def onUpdate(): Unit = {
    super.onUpdate()
    println(entityLiving.getDistance(posX, posY, posZ))
    if (entityLiving.getDistance(posX, posY, posZ) > 5) setDead()
  }
}

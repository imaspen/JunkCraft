package uk.zebington.junkcraft.init

import net.minecraftforge.fml.common.registry.GameRegistry
import uk.zebington.junkcraft._
import uk.zebington.junkcraft.common.items._

/**
 * Created by Charlotte on 20/02/2015.
 */
object JCItems {
  final val Stabber = new ItemStabber
  final val Knife = new ItemKnife
  final val PickerUpper = new ItemPickerUpper
  final val CarDoor = new ItemCarDoor
  final val Spikes = new ItemSpikes
  final val ZombieArm = new ItemZombieArm

  def init() {
    GameRegistry registerItem(Stabber, NStabber)
    GameRegistry registerItem(Knife, NKnife)
    GameRegistry registerItem(PickerUpper, NPickerUpper)
    GameRegistry registerItem(CarDoor, NCarDoor)
    GameRegistry registerItem(Spikes, NSpikes)
    GameRegistry registerItem(ZombieArm, NZombieArm)
  }
}

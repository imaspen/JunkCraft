package uk.zebington.junkcraft.init

import net.minecraftforge.fml.common.registry.GameRegistry
import uk.zebington.junkcraft._
import uk.zebington.junkcraft.common.items.{ItemCarDoor, ItemKnife, ItemPickerUpper, ItemStabber}

/**
 * Created by Charlotte on 20/02/2015.
 */
object JCItems {
  final val Stabber = new ItemStabber
  final val Knife = new ItemKnife
  final val PickerUpper = new ItemPickerUpper
  final val CarDoor = new ItemCarDoor

  def init() {
    GameRegistry registerItem(Stabber, NStabber)
    GameRegistry registerItem(Knife, NKnife)
    GameRegistry registerItem(PickerUpper, NPickerUpper)
    GameRegistry registerItem(CarDoor, NCarDoor)
  }
}

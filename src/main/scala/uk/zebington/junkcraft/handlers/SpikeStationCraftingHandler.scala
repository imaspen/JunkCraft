package uk.zebington.junkcraft.handlers

import net.minecraft.item.{Item, ItemSword}
import net.minecraftforge.fml.common.registry.GameData

import scala.collection.JavaConversions._

/**
 * Created by Charlotte on 11/03/2015.
 */
object SpikeStationCraftingHandler {
  var SpikyItems: Map[Item, Int] = Map()

  def initSpikyItems() {
    GameData.getItemRegistry.getKeys.toList.foreach(key => Item.getByNameOrId(key.toString) match {
      case sword: ItemSword =>
        sword.getToolMaterialName match {
          case "WOOD" => SpikyItems += (sword -> 64)
          case "STONE" => SpikyItems += (sword -> 48)
          case "GOLD" => SpikyItems += (sword -> 32)
          case "IRON" => SpikyItems += (sword -> 16)
          case "EMERALD" => SpikyItems += (sword -> 2)
          case _ =>
        }
      case _ =>
    })
  }
}

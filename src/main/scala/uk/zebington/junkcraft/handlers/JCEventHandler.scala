package uk.zebington.junkcraft.handlers

import net.minecraft.enchantment.Enchantment
import net.minecraft.item.ItemArmor
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent
import uk.zebington.junkcraft.JunkCraft
import uk.zebington.junkcraft.init.JCItems

/**
 * Created by Charlotte on 22/02/2015.
 */
class JCEventHandler {

  @SubscribeEvent def onItemCrafted(e: ItemCraftedEvent) {
    var f0 = false
    var f1 = false
    for (i <- 0 until e.craftMatrix.getSizeInventory) {
      val stack = e.craftMatrix getStackInSlot i
      if (stack != null) {
        if (stack.getItem == JCItems.Knife) f0 = true
        if (stack.getItem.isInstanceOf[ItemArmor]) f1 = true
      }
    }
    if (f0 & f1) e.crafting.addEnchantment(Enchantment.thorns, 1)
    println(JunkCraft.Instance)
  }
}

package uk.zebington.junkcraft.common.items

import uk.zebington.junkcraft._
import net.minecraft.item.{ItemStack, Item, ItemSword}

/**
 * Created by Charlotte on 20/02/2015.
 */
class ItemStabber extends ItemSword(Item.ToolMaterial.IRON) with ItemPicker {
  setUnlocalizedName(NStabber)

  override def getIsRepairable(toRepair: ItemStack, repair: ItemStack) = false
}

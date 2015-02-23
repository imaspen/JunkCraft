package uk.zebington.junkcraft.common.items

import net.minecraft.item.{Item, ItemStack, ItemSword}
import uk.zebington.junkcraft._

/**
 * Created by Charlotte on 20/02/2015.
 */
class ItemKnife extends ItemSword(Item.ToolMaterial.STONE) with ItemJC {
  setUnlocalizedName(NKnife)

  override def getIsRepairable(toRepair: ItemStack, repair: ItemStack) = false


}

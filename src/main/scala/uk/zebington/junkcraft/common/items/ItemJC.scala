package uk.zebington.junkcraft.common.items

import uk.zebington.junkcraft._
import net.minecraft.item.Item

/**
 * Created by Charlotte on 21/02/2015.
 */
trait ItemJC extends Item {
  setMaxStackSize(1)
  setCreativeTab(TabJC)
}

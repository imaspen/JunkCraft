package uk.zebington

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import uk.zebington.junkcraft.init.JCItems

/**
 * Created by Charlotte on 19/02/2015.
 */
package object junkcraft {
  // ModVals
  final val Id = "junkcraft"
  final val Version = "${mcversion}"
  final val Name = "JunkCraft"
  final val Group = "uk.zebington.junkcraft"

  // Items
  final val NStabber = "stabber"
  final val NKnife = "knife"
  final val NPickerUpper = "pickerupper"
  final val NCarDoor = "cardoor"
  final val NSpikes = "spikes"

  // Blocks
  final val NElectricFence = "electricfence"
  final val NSpikeStation = "spikestation"

  // GUI IDs
  final val GSpikeStation = 0

  // Creative Tab
  final val TabJC = new CreativeTabs(CreativeTabs.getNextID, Id) {
    override def getTabIconItem: Item = JCItems.Stabber
  }
}

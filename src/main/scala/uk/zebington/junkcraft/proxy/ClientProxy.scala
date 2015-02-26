package uk.zebington.junkcraft.proxy

import net.minecraft.client.Minecraft
import net.minecraft.client.resources.model.ModelResourceLocation
import net.minecraft.item.Item
import uk.zebington.junkcraft._
import uk.zebington.junkcraft.init.{JCBlocks, JCItems}

/**
 * Created by Charlotte on 19/02/2015.
 */
class ClientProxy extends CommonProxy {
  override def registerRenderers() {
    val renderer = Minecraft.getMinecraft.getRenderItem
    val modMesh = renderer.getItemModelMesher

    modMesh register(JCItems.Stabber, 0, new ModelResourceLocation(s"$Id:$NStabber", "inventory"))
    modMesh register(JCItems.Knife, 0, new ModelResourceLocation(s"$Id:$NKnife", "inventory"))
    modMesh register(JCItems.PickerUpper, 0, new ModelResourceLocation(s"$Id:$NPickerUpper", "inventory"))
    modMesh register(JCItems.CarDoor, 0, new ModelResourceLocation(s"$Id:$NCarDoor", "inventory"))
    modMesh register(JCItems.Spikes, 0, new ModelResourceLocation(s"$Id:$NSpikes", "inventory"))

    modMesh register(Item.getItemFromBlock(JCBlocks.ElectricFence), 0, new ModelResourceLocation(s"$Id:$NElectricFence", "inventory"))
    modMesh register(Item.getItemFromBlock(JCBlocks.SpikeStation), 0, new ModelResourceLocation(s"$Id:$NSpikeStation", "inventory"))
  }
}

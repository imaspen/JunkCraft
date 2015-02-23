package uk.zebington.junkcraft

import net.minecraft.item.{Item, ItemArmor, ItemStack}
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod.{EventHandler, Instance}
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLLoadCompleteEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.registry.{GameData, GameRegistry}
import net.minecraftforge.fml.common.{FMLCommonHandler, Mod, SidedProxy}
import uk.zebington.junkcraft.handlers.{JCEventHandler, JCGuiHandler, JCPacketHandler}
import uk.zebington.junkcraft.init.{JCBlocks, JCItems}
import uk.zebington.junkcraft.proxy.CommonProxy

import scala.collection.JavaConversions._

/**
 * Created by Charlotte on 19/02/2015.
 */
@Mod(modid = Id, version = Version, name = Name, modLanguage = "scala")
object JunkCraft {
  @Instance(Id)
  var Instance: JunkCraft.type = null

  @SidedProxy(serverSide = Group + ".proxy.CommonProxy", clientSide = Group + ".proxy.ClientProxy")
  var Proxy: CommonProxy = null

  @EventHandler def preInit(e: FMLPreInitializationEvent) {
    JCItems init()
    JCBlocks init()

    JCPacketHandler init()
  }

  @EventHandler def init(e: FMLInitializationEvent) {
    Proxy registerTileEntities()
    Proxy registerRenderers()
  }

  @EventHandler def postInit(e: FMLPostInitializationEvent) {
    for (id <- GameData.getItemRegistry.getKeys.toList) Item.getByNameOrId(id.toString) match {
      case item: ItemArmor =>
        GameRegistry.addShapelessRecipe(new ItemStack(item), new ItemStack(item), new ItemStack(JCItems.Knife))
        println(id)
      case _ =>
    }
    FMLCommonHandler.instance().bus().register(new JCEventHandler)
    MinecraftForge.EVENT_BUS.register(new JCEventHandler)
  }

  @EventHandler def loaded(e: FMLLoadCompleteEvent): Unit = {
    NetworkRegistry.INSTANCE.registerGuiHandler(this, new JCGuiHandler)
  }
}

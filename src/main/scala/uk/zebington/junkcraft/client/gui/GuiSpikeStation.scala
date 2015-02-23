package uk.zebington.junkcraft.client.gui

import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation
import uk.zebington.junkcraft._
import uk.zebington.junkcraft.common.containers.ContainerSpikeStation
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation

/**
 * Created by Charlotte on 22/02/2015.
 */
class GuiSpikeStation(inv: TileEntitySpikeStation, invPlayer: InventoryPlayer) extends GuiContainer(new ContainerSpikeStation(inv, invPlayer)) {
  val Texture = new ResourceLocation(s"$Id:textures/gui/spikestation.png")

  override def drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int): Unit = {
    this.mc.getTextureManager.bindTexture(Texture)
    val k: Int = (this.width - this.xSize) / 2
    val l: Int = (this.height - this.ySize) / 2
    this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize)
  }
}

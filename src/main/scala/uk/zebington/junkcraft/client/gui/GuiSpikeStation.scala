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
  val TextureArmor = new ResourceLocation(s"$Id:textures/gui/spikestationarmor.png")
  val TextureSpikes = new ResourceLocation(s"$Id:textures/gui/spikestationspikes.png")
  val SW = 30

  override def drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int): Unit = {
    val K: Int = (this.width - this.xSize) / 2
    val L: Int = (this.height - this.ySize) / 2
    inv.mode match {
      case 0 =>
        setSlotXY(0, 30, 35)
        setSlotXY(1, 66, 35)
        setSlotXY(2, 126, 35)
        this.mc.getTextureManager.bindTexture(TextureArmor)
      case 1 =>
        setSlotXY(0, 48, 35)
        setSlotXY(1, 152, 44)
        setSlotXY(2, 66, 35)
        this.mc.getTextureManager.bindTexture(TextureSpikes)
    }
    this.drawTexturedModalRect(K, L, 0, 0, this.xSize, this.ySize)
  }

  def setSlotXY(slot: Int, x: Int, y: Int): Unit = {
    inventorySlots.getSlot(slot).xDisplayPosition = x
    inventorySlots.getSlot(slot).yDisplayPosition = y
  }

  override def mouseClicked(x: Int, y: Int, button: Int): Unit = {
    val K: Int = (this.width - this.xSize) / 2
    val L: Int = (this.height - this.ySize) / 2
    val SX = K + 10
    val SY = L + 10
    super.mouseClicked(x, y, button)
    if ((SX to SX + SW contains x) & (SY to SY + SW contains y)) inv.switchMode()
  }
}

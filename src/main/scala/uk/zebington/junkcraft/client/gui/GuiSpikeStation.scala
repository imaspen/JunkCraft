package uk.zebington.junkcraft.client.gui

import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.{ResourceLocation, StatCollector}
import uk.zebington.junkcraft._
import uk.zebington.junkcraft.common.containers.ContainerSpikeStation
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation

import scala.collection.JavaConversions._

/**
 * Created by Charlotte on 22/02/2015.
 */
class GuiSpikeStation(inv: TileEntitySpikeStation, invPlayer: InventoryPlayer) extends GuiContainer(new ContainerSpikeStation(inv, invPlayer)) {
  val TextureArmor = new ResourceLocation(s"$Id:textures/gui/spikestationarmor.png")
  val TextureSpikes = new ResourceLocation(s"$Id:textures/gui/spikestationspikes.png")
  val SW = 16

  override def drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int): Unit = {
    val K: Int = (this.width - this.xSize) / 2
    val L: Int = (this.height - this.ySize) / 2
    val SX = K + 5
    val SY = L + 5
    inv.mode match {
      case 0 =>
        setSlotXY(0, 30, 35)
        setSlotXY(1, 66, 35)
        setSlotXY(2, 126, 35)
        this.mc.getTextureManager.bindTexture(TextureArmor)
      case 1 =>
        setSlotXY(0, 48, 35)
        setSlotXY(1, 152, 44)
        setSlotXY(2, 108, 35)
        this.mc.getTextureManager.bindTexture(TextureSpikes)
    }
    this.drawTexturedModalRect(K, L, 0, 0, this.xSize, this.ySize)
    this.drawTexturedModalRect(SX, SY, 176, if ((SX to SX + SW contains mouseX) & (SY to SY + SW contains mouseY)) 16 else 0, SW, SW)
  }

  def setSlotXY(slot: Int, x: Int, y: Int): Unit = {
    inventorySlots.getSlot(slot).xDisplayPosition = x
    inventorySlots.getSlot(slot).yDisplayPosition = y
  }

  override def drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int): Unit = {
    var s = inv.getDisplayName.getUnformattedText
    this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752)

    inv.mode match {
      case 0 => this.fontRendererObj.drawString(invPlayer.getDisplayName.getUnformattedText, 8, this.ySize - 96 + 2, 4210752)
      case 1 =>
        s = if (inv.stored != null) s"${StatCollector.translateToLocal(inv.stored.getUnlocalizedName + ".name")} x ${inv.nStored}" else "Empty!"
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.ySize - 98, 4210752)
    }

    val K: Int = (this.width - this.xSize) / 2
    val L: Int = (this.height - this.ySize) / 2
    val SX = K + 5
    val SY = L + 5
    if ((SX to SX + SW contains mouseX) && (SY to SY + SW contains mouseY)) {
      val str = List(if (inv.inv(0) != null | inv.inv(1) != null | inv.inv(2) != null) "All slots must be empty!" else "Switch mode!")
      this.drawHoveringText(str, mouseX - K, mouseY - L)
    }
  }

  override def mouseClicked(x: Int, y: Int, button: Int): Unit = {
    val K: Int = (this.width - this.xSize) / 2
    val L: Int = (this.height - this.ySize) / 2
    val SX = K + 5
    val SY = L + 5
    super.mouseClicked(x, y, button)
    if ((SX to SX + SW contains x) & (SY to SY + SW contains y)) {
      inv.switchMode()
    }
  }
}

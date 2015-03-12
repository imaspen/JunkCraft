package uk.zebington.junkcraft.client.gui

import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.{ResourceLocation, StatCollector}
import uk.zebington.junkcraft._
import uk.zebington.junkcraft.common.containers.ContainerSpikeStation
import uk.zebington.junkcraft.common.tileentities.TileEntitySpikeStation

import scala.collection.JavaConversions._
import uk.zebington._

/**
 * Created by Charlotte on 22/02/2015.
 */
class GuiSpikeStation(inv: TileEntitySpikeStation, invPlayer: InventoryPlayer) extends GuiContainer(new ContainerSpikeStation(inv, invPlayer)) {
  val TextureArmor = new ResourceLocation(s"$Id:textures/gui/spikestationarmor.png")
  val TextureSpikes = new ResourceLocation(s"$Id:textures/gui/spikestationspikes.png")
  val SW = 16
  val SwitchButtonID = 0

  override def initGui() {
    super.initGui()

    buttonList.asInstanceOf[java.util.ArrayList[GuiButton]].add(new GuiButton(SwitchButtonID, guiLeft + 5, guiTop + 5,
      fontRendererObj.getStringWidth(I18n.format(NGSwitchMode)) + 20, 20, I18n.format(NGSwitchMode)))
  }

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
        mc.getTextureManager.bindTexture(TextureArmor)
      case 1 =>
        setSlotXY(0, 48, 35)
        setSlotXY(1, 152, 44)
        setSlotXY(2, 108, 35)
        mc.getTextureManager.bindTexture(TextureSpikes)
    }
    drawTexturedModalRect(K, L, 0, 0, this.xSize, this.ySize)
  }

  def setSlotXY(slot: Int, x: Int, y: Int): Unit = {
    inventorySlots.getSlot(slot).xDisplayPosition = x
    inventorySlots.getSlot(slot).yDisplayPosition = y
  }

  override def drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int): Unit = {
    var s = inv.getDisplayName.getUnformattedText
    this.fontRendererObj.drawString(s, this.xSize - 5 - this.fontRendererObj.getStringWidth(s), 6, 4210752)

    inv.mode match {
      case 0 => this.fontRendererObj.drawString(invPlayer.getDisplayName.getUnformattedText, 8, this.ySize - 96 + 2, 4210752)
      case 1 =>
        s = if (inv.stored != null) s"${StatCollector.translateToLocal(inv.stored.getUnlocalizedName + ".name")} x ${inv.nStored}" else "Empty!"
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.ySize - 98, 4210752)
    }

    for (button <- buttonList.asInstanceOf[java.util.ArrayList[GuiButton]]) button.id match {
      case SwitchButtonID =>
        button.enabled = inv.isEmpty
        if (!button.enabled && button.isMouseOver)
          drawCreativeTabHoveringText(I18n.format(NGWarnNonEmpty), mouseX - guiLeft, mouseY - guiTop)
    }
  }

  override def actionPerformed(button: GuiButton): Unit = button.id match {
    case SwitchButtonID =>
      inv.switchMode()
    case _ =>
  }
}

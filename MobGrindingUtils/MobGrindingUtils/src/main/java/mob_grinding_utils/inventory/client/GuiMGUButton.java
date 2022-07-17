package mob_grinding_utils.inventory.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class GuiMGUButton extends Button {
    private static final ResourceLocation TEXTURES = new ResourceLocation("mob_grinding_utils:textures/gui/absorption_hopper_gui.png");
    private static final ResourceLocation SOLIDIFIER_TEXTURES = new ResourceLocation("mob_grinding_utils:textures/gui/solidifier_gui.png");
    public Size size;
    public int id;

    public GuiMGUButton(int x, int y, Size s, int idIn, Component title, OnPress pressedAction) {
        super(x, y, s.width, s.height, title, pressedAction);
        size = s;
        id = idIn;
    }

    @Override
    public void renderButton(@Nonnull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        Font fontrenderer = mc.font;
        if (visible) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, getTextures(size));
            RenderSystem.setShaderColor(0.75F, 0.75F, 0.75F, 0.5F);
            boolean hover = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
            if(hover)
                RenderSystem.setShaderColor(0.75F, 1F, 0.75F, 1F);
            blit(matrixStack, x, y, size.u, size.v, width, height);

            int textColour = 14737632;
            if (getFGColor() != 0)
                textColour = getFGColor();
            else if (!this.active)
                textColour = 10526880;
            else if (this.isHoveredOrFocused())
                textColour = 16777120;
            drawCenteredString(matrixStack, fontrenderer, getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, textColour);
        }
    }
    
    public ResourceLocation getTextures(Size size) {
        return switch (size) {
            case SMALL, MEDIUM, LARGE -> TEXTURES;
            case SOLIDIFIER, SOLIDIFIER_ON -> SOLIDIFIER_TEXTURES;
        };
    }

    enum Size {
        SMALL(16 , 16, 103, 228),
        MEDIUM(32, 16, 0, 228),
        LARGE(68, 16, 33, 228),
        SOLIDIFIER(34, 16, 178, 92),
        SOLIDIFIER_ON(20, 16, 178, 110);

        final int width;
        final int height;
        final int u;
        final int v;
        Size(int w, int h, int U, int V) {width = w; height = h; u = U; v = V;}
    }
}

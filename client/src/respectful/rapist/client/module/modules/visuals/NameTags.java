package respectful.rapist.client.module.modules.visuals;

import org.lwjgl.opengl.GL11;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;

public class NameTags extends Module {
    public NameTags() {
        super(49, "NameTags", "6699FF");
    }

    @Override
    public void onRender() {
        for (Object entityPlayer : Mappings.World.getPlayerEntities(Mappings.Minecraft.getTheWorld())) {
            if (!Mappings.EntityPlayer.clazz.cast(entityPlayer).equals(Mappings.Minecraft.getThePlayer())) {
                float scale = Mappings.Entity.getDistanceToEntity(Mappings.Minecraft.getThePlayer(), entityPlayer) / 150.0F;
                GL11.glPushMatrix();
                GL11.glTranslatef((float) (Mappings.Entity.getPosX(entityPlayer) - Mappings.Entity.getPosX(Mappings.Minecraft.getThePlayer())), (float) (Mappings.Entity.getPosY(entityPlayer) - Mappings.Entity.getPosY(Mappings.Minecraft.getThePlayer()) + 2.3F), (float) (Mappings.Entity.getPosZ(entityPlayer) - Mappings.Entity.getPosZ(Mappings.Minecraft.getThePlayer())));
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-Mappings.RenderManager.getPlayerViewY(Mappings.RenderManager.getInstance()), 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(Mappings.RenderManager.getPlayerViewX(Mappings.RenderManager.getInstance()), 1.0F, 0.0F, 0.0F);
                GL11.glScalef(-scale, -scale, scale);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDepthMask(false);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                Mappings.OpenGLHelper.glBlendFunc(770, 771, 1, 0);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                Mappings.Tessellator.startDrawingQuads(Mappings.Tessellator.getInstance());
                if (Mappings.Entity.isSneaking(entityPlayer)) {
                    Mappings.Tessellator.setColorRGBA_F(Mappings.Tessellator.getInstance(), 0.85098039215F, 0.09019607843F, 0.13333333333F, 0.5F);
                } else {
                    Mappings.Tessellator.setColorRGBA_F(Mappings.Tessellator.getInstance(), 0.0F, 0.0F, 0.0F, 0.5F);
                }
                String name = Mappings.EntityPlayer.getCommandSenderName(entityPlayer);
                String health = " " + Math.round((Mappings.EntityLivingBase.getHealth(entityPlayer) / 2.0F) * 10.0F) / 10.0F;
                String display = name + health;
                int x = Mappings.FontRenderer.getStringWidth(Mappings.Minecraft.getFontRenderer(), display) / 2;
                Mappings.Tessellator.addVertex(Mappings.Tessellator.getInstance(), -x - 1, -1.0D, 0.0D);
                Mappings.Tessellator.addVertex(Mappings.Tessellator.getInstance(), -x - 1, 8.0D, 0.0D);
                Mappings.Tessellator.addVertex(Mappings.Tessellator.getInstance(), x + 1, 8.0D, 0.0D);
                Mappings.Tessellator.addVertex(Mappings.Tessellator.getInstance(), x + 1, -1.0D, 0.0D);
                Mappings.Tessellator.draw(Mappings.Tessellator.getInstance());
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                String healthColor = "008E34";
                if (Mappings.EntityLivingBase.getHealth(entityPlayer) <= 3.33F) {
                    healthColor = "D91722";
                } else if (Mappings.EntityLivingBase.getHealth(entityPlayer) <= 6.67F) {
                    healthColor = "F27111";
                } else if (Mappings.EntityLivingBase.getHealth(entityPlayer) <= 10.0F) {
                    healthColor = "FFD700";
                } else if (Mappings.EntityLivingBase.getHealth(entityPlayer) <= 13.33F) {
                    healthColor = "D6E101";
                } else if (Mappings.EntityLivingBase.getHealth(entityPlayer) <= 16.67F) {
                    healthColor = "6BBE48";
                }
                Mappings.FontRenderer.drawStringWithShadow(Mappings.Minecraft.getFontRenderer(), name, -x, 0, "FFFFFF");
                Mappings.FontRenderer.drawStringWithShadow(Mappings.Minecraft.getFontRenderer(), health, Mappings.FontRenderer.getStringWidth(Mappings.Minecraft.getFontRenderer(), name) - x, 0, healthColor);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(true);
                Mappings.FontRenderer.drawStringWithShadow(Mappings.Minecraft.getFontRenderer(), name, -x, 0, "FFFFFF");
                Mappings.FontRenderer.drawStringWithShadow(Mappings.Minecraft.getFontRenderer(), health, Mappings.FontRenderer.getStringWidth(Mappings.Minecraft.getFontRenderer(), name) - x, 0, healthColor);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
            }
        }
    }
}

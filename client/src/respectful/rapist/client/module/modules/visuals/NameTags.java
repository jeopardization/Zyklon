package respectful.rapist.client.module.modules.visuals;

import org.lwjgl.opengl.GL11;
import respectful.rapist.client.EventManager;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;

import java.awt.*;

public class NameTags extends Module implements Mappings {
    public NameTags() {
        super(49, "NameTags", "6699FF");
    }

    @Override
    public void onRender() {
        for (Object entityPlayer : World.getPlayerEntities(Minecraft.getTheWorld())) {
            if (!EntityPlayer.clazz.cast(entityPlayer).equals(Minecraft.getThePlayer()) && EntityLivingBase.isEntityAlive(entityPlayer)) {
                float scale = Entity.getDistanceToEntity(Minecraft.getThePlayer(), entityPlayer) / 150.0F;
                GL11.glPushMatrix();
                GL11.glTranslatef((float) (Entity.getPosX(entityPlayer) - Entity.getPosX(Minecraft.getThePlayer())), (float) (Entity.getPosY(entityPlayer) - Entity.getPosY(Minecraft.getThePlayer()) + 2.3F), (float) (Entity.getPosZ(entityPlayer) - Entity.getPosZ(Minecraft.getThePlayer())));
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-RenderManager.getPlayerViewY(RenderManager.getInstance()), 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(RenderManager.getPlayerViewX(RenderManager.getInstance()), 1.0F, 0.0F, 0.0F);
                GL11.glScalef(-scale, -scale, scale);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_BLEND);
                OpenGLHelper.glBlendFunc(770, 771, 1, 0);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                Tessellator.startDrawingQuads(Tessellator.getInstance());
                if (Entity.isSneaking(entityPlayer)) {
                    Tessellator.setColorRGBA_F(Tessellator.getInstance(), 0.85098039215F, 0.09019607843F, 0.13333333333F, 0.5F);
                } else {
                    Tessellator.setColorRGBA_F(Tessellator.getInstance(), 0.0F, 0.0F, 0.0F, 0.5F);
                }
                String name = EntityPlayer.getCommandSenderName(entityPlayer);
                String health = " " + Math.round((EntityLivingBase.getHealth(entityPlayer) / 2.0F) * 10.0F) / 10.0F;
                String display = name + health;
                int x = FontRenderer.getStringWidth(Minecraft.getFontRenderer(), display) / 2;
                GL11.glDepthMask(false);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                Tessellator.addVertex(Tessellator.getInstance(), -x - 1, -1.0D, 0.0D);
                Tessellator.addVertex(Tessellator.getInstance(), -x - 1, 8.0D, 0.0D);
                Tessellator.addVertex(Tessellator.getInstance(), x + 1, 8.0D, 0.0D);
                Tessellator.addVertex(Tessellator.getInstance(), x + 1, -1.0D, 0.0D);
                Tessellator.draw(Tessellator.getInstance());
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                int nameColor = 0xFFFFFF;
                if (EventManager.playerManager.isFriend(name)) {
                    nameColor = 0x00C834;
                } else if (EventManager.playerManager.isEnemy(name) || (EventManager.moduleManager.aimbot.target != null && EntityPlayer.getCommandSenderName(EventManager.moduleManager.aimbot.target).equalsIgnoreCase(name)) || (EventManager.moduleManager.wTap.target != null && EntityPlayer.getCommandSenderName(EventManager.moduleManager.wTap.target).equalsIgnoreCase(name))) {
                    nameColor = 0xFF2A36;
                }
                float hp = EntityLivingBase.getHealth(entityPlayer);
                int healthColor = Color.getHSBColor(Math.min(hp / 10F, 2F) * (1F/6F), 1F, 1F).getRGB();
                FontRenderer.drawStringWithShadow(Minecraft.getFontRenderer(), name, -x, 0, nameColor);
                FontRenderer.drawStringWithShadow(Minecraft.getFontRenderer(), health, FontRenderer.getStringWidth(Minecraft.getFontRenderer(), name) - x, 0, healthColor);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(true);
                FontRenderer.drawStringWithShadow(Minecraft.getFontRenderer(), name, -x, 0, nameColor);
                FontRenderer.drawStringWithShadow(Minecraft.getFontRenderer(), health, FontRenderer.getStringWidth(Minecraft.getFontRenderer(), name) - x, 0, healthColor);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
            }
        }
    }
}

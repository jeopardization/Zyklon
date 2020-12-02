package respectful.rapist.client.module.modules.visuals;

import org.lwjgl.opengl.GL11;
import respectful.rapist.client.Events;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;

public class NameTags extends Module implements Mappings {
    public NameTags() {
        super(49, "NameTags", 0x74B9FF);
    }

    @Override
    public void onRender() {
        for (Object entityPlayer : World.getPlayerEntities(Minecraft.getTheWorld())) {
            if (!EntityPlayer.clazz.cast(entityPlayer).equals(Minecraft.getThePlayer()) && EntityLivingBase.isEntityAlive(entityPlayer)) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float) (Entity.getPosX(entityPlayer) - Entity.getPosX(Minecraft.getThePlayer())), (float) (Entity.getPosY(entityPlayer) - Entity.getPosY(Minecraft.getThePlayer()) + 2.3F), (float) (Entity.getPosZ(entityPlayer) - Entity.getPosZ(Minecraft.getThePlayer())));
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                Object renderManager = RealmsSharedConstants.getVersion().equals("1.8.9") ? Minecraft.getRenderManager() : RenderManager.getInstance();
                GL11.glRotatef(-RenderManager.getPlayerViewY(renderManager), 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(RenderManager.getPlayerViewX(renderManager), 1.0F, 0.0F, 0.0F);
                float scale = Entity.getDistanceToEntity(Minecraft.getThePlayer(), entityPlayer) / 150.0F;
                GL11.glScalef(-scale, -scale, scale);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDepthMask(false);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                OpenGLHelper.glBlendFunc(770, 771, 1, 0);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                String name = EntityPlayer.getCommandSenderName(entityPlayer), health = " " + Math.round((EntityLivingBase.getHealth(entityPlayer) / 2.0F) * 10.0F) / 10.0F;
                int x = FontRenderer.getStringWidth(Minecraft.getFontRenderer(), name + health) / 2, nameColor = 0xFFFFFF, healthColor = 0x008E34;
                float r = 0.0F, g = 0.0F, b = 0.0F;
                if (Entity.isSneaking(entityPlayer)) {
                    r = 0.85098039215F;
                    g = 0.09019607843F;
                    b = 0.13333333333F;
                }
                Object tessellator = Tessellator.getInstance();
                if (RealmsSharedConstants.getVersion().equals("1.8.9")) {
                    Object worldRenderer = Tessellator.getWorldRenderer(tessellator);
                    WorldRenderer.begin(worldRenderer, 7, DefaultVertexFormats.getPOSITION_COLOR());
                    WorldRenderer.endVertex(WorldRenderer.color(WorldRenderer.pos(worldRenderer, -x - 1.0D, -1.0D, 0.0D), r, g, b, 0.25F));
                    WorldRenderer.endVertex(WorldRenderer.color(WorldRenderer.pos(worldRenderer, -x - 1.0D, 8.0D, 0.0D), r, g, b, 0.25F));
                    WorldRenderer.endVertex(WorldRenderer.color(WorldRenderer.pos(worldRenderer, x + 1.0D, 8.0D, 0.0D), r, g, b, 0.25F));
                    WorldRenderer.endVertex(WorldRenderer.color(WorldRenderer.pos(worldRenderer, x + 1.0D, -1.0D, 0.0D), r, g, b, 0.25F));
                } else {
                    Tessellator.startDrawingQuads(tessellator);
                    Tessellator.setColorRGBA_F(tessellator, r, g, b, 0.25F);
                    Tessellator.addVertex(tessellator, -x - 1.0D, -1.0D, 0.0D);
                    Tessellator.addVertex(tessellator, -x - 1.0D, 8.0D, 0.0D);
                    Tessellator.addVertex(tessellator, x + 1.0D, 8.0D, 0.0D);
                    Tessellator.addVertex(tessellator, x + 1.0D, -1.0D, 0.0D);
                }
                Tessellator.draw(tessellator);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                if (Events.players.isFriend(name)) {
                    nameColor = 0x00B894;
                } else if (Events.players.isEnemy(name) || (Events.modules.aimbot.target != null && EntityPlayer.getCommandSenderName(Events.modules.aimbot.target).equalsIgnoreCase(name)) || (Events.modules.wTap.target != null && EntityPlayer.getCommandSenderName(Events.modules.wTap.target).equalsIgnoreCase(name))) {
                    nameColor = 0xD63031;
                }
                if (EntityLivingBase.getHealth(entityPlayer) <= 3.33F) {
                    healthColor = 0xD91722;
                } else if (EntityLivingBase.getHealth(entityPlayer) <= 6.67F) {
                    healthColor = 0xF27111;
                } else if (EntityLivingBase.getHealth(entityPlayer) <= 10.0F) {
                    healthColor = 0xFFD700;
                } else if (EntityLivingBase.getHealth(entityPlayer) <= 13.33F) {
                    healthColor = 0xD6E101;
                } else if (EntityLivingBase.getHealth(entityPlayer) <= 16.67F) {
                    healthColor = 0x6BBE48;
                }
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

package respectful.rapist.client.mapping;

import respectful.rapist.client.mapping.mappings.*;

public interface Mappings {
    GuiScreen GuiScreen = new GuiScreen();
    Minecraft Minecraft = new Minecraft();
    Entity Entity = new Entity();
    EntityPlayer EntityPlayer = new EntityPlayer();
    EntityClientPlayerMP EntityClientPlayerMP = new EntityClientPlayerMP();
    EntityLivingBase EntityLivingBase = new EntityLivingBase();
    S12PacketEntityVelocity S12PacketEntityVelocity = new S12PacketEntityVelocity();
    InventoryPlayer InventoryPlayer = new InventoryPlayer();
    ItemStack ItemStack = new ItemStack();
    Item Item = new Item();
    GameSettings GameSettings = new GameSettings();
    FontRenderer FontRenderer = new FontRenderer();
    ScaledResolution ScaledResolution = new ScaledResolution();
    GuiInventory GuiInventory = new GuiInventory();
    PlayerControllerMP PlayerControllerMP = new PlayerControllerMP();
    World World = new World();
    RenderManager RenderManager = new RenderManager();
    OpenGLHelper OpenGLHelper = new OpenGLHelper();
    Tessellator Tessellator = new Tessellator();
    KeyBinding KeyBinding = new KeyBinding();
    CPSMod CPSMod = new CPSMod();
}

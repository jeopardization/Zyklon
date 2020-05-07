package respectful.rapist.loader;

import respectful.rapist.loader.mapping.Mappings;
import respectful.rapist.loader.transformer.transformers.*;

import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class Main {
    public static URLClassLoader loader;
    public static Object launchClassLoader;
    public static Instrumentation inst;
    public static byte[] origMinecraft, origEntity, origEntityRenderer, origRender, origRendererLivingEntity, origGuiIngame, origNetHandlerPlayClient, origRenderGlobal;

    static {
        try {
            loader = new URLClassLoader(new URL[]{new URL("http://localhost:1337/client.jar")});
            launchClassLoader = ClassLoader.getSystemClassLoader().loadClass("net.minecraft.launchwrapper.Launch").getDeclaredField("classLoader").get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void agentmain(String args, Instrumentation inst) {
        try {
            Main.inst = inst;
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            addURL.invoke(launchClassLoader, Main.class.getProtectionDomain().getCodeSource().getLocation());
            transform(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void transform(boolean transforming) {
        ClassFileTransformer transformer;
        Instrumentation inst = (Instrumentation) getField("inst");
        for (Class clazz : inst.getAllLoadedClasses()) {
            switch (clazz.getName()) {
                case "net.minecraft.client.Minecraft":
                    if (transforming) {
                        transformer = new Minecraft();
                    } else {
                        transformer = (loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> (byte[]) getField("origMinecraft");
                    }
                    break;
                case "net.minecraft.entity.Entity":
                    if (transforming) {
                        transformer = new Entity();
                    } else {
                        transformer = (loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> (byte[]) getField("origEntity");
                    }
                    break;
                case "net.minecraft.client.renderer.EntityRenderer":
                    if (transforming) {
                        transformer = new EntityRenderer();
                    } else {
                        transformer = (loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> (byte[]) getField("origEntityRenderer");
                    }
                    break;
                case "net.minecraft.client.renderer.entity.Render":
                    if (transforming) {
                        transformer = new Render();
                    } else {
                        transformer = (loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> (byte[]) getField("origRender");
                    }
                    break;
                case "net.minecraft.client.renderer.RenderGlobal":
                    if (transforming) {
                        transformer = new RenderGlobal();
                    } else {
                        transformer = (loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> (byte[]) getField("origRenderGlobal");
                    }
                    break;
                case "net.minecraft.client.renderer.entity.RendererLivingEntity":
                    if (transforming) {
                        transformer = new RendererLivingEntity();
                    } else {
                        transformer = (loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> (byte[]) getField("origRendererLivingEntity");
                    }
                    break;
                case "net.minecraftforge.client.GuiIngameForge":
                    if (transforming) {
                        transformer = new GuiIngame();
                    } else {
                        transformer = (loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> (byte[]) getField("origGuiIngame");
                    }
                    break;
                case "net.minecraft.client.network.NetHandlerPlayClient":
                    if (transforming) {
                        transformer = new NetHandlerPlayClient();
                    } else {
                        transformer = (loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> (byte[]) getField("origNetHandlerPlayClient");
                    }
                    break;
                default:
                    transformer = null;
            }
            if (transformer != null) {
                try {
                    inst.addTransformer(transformer, true);
                    inst.retransformClasses(clazz);
                    inst.removeTransformer(transformer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static Object getField(String name) {
        try {
            return ClassLoader.getSystemClassLoader().loadClass(Main.class.getName()).getDeclaredField(name).get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void destroy() {
        try {
            InputStream inputStream = new URL("http://localhost:1337/setenabled/Self%20Destruct/0").openStream();
            inputStream.close();
            transform(false);
            List<URL> sources = (List<URL>) launchClassLoader.getClass().getDeclaredMethod("getSources").invoke(launchClassLoader);
            for (Object source : sources) {
                if (source.toString().toLowerCase().contains("loader.jar")) {
                    sources.remove(source);
                    break;
                }
            }
            loader = null;
            launchClassLoader = null;
            Mappings.ModuleManager = null;
            Mappings.Module = null;
            Mappings.EventManager = null;
            Mappings.NameTags = null;
            Mappings.HitBoxes = null;
            Mappings.Reach = null;
            Mappings.FakeLag = null;
            Mappings.Velocity = null;
            origMinecraft = null;
            origEntity = null;
            origEntityRenderer = null;
            origRender = null;
            origRendererLivingEntity = null;
            origGuiIngame = null;
            origNetHandlerPlayClient = null;
            origRenderGlobal = null;
            inst = null;
            for (Field field : ClassLoader.getSystemClassLoader().loadClass(Main.class.getName()).getDeclaredFields()) {
                field.set(null, null);
            }
            Object obj = new Object();
            WeakReference weakReference = new WeakReference<Object>(obj);
            obj = null;
            while (weakReference.get() != null) {
                System.gc();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

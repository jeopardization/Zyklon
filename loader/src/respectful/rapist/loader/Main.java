package respectful.rapist.loader;

import respectful.rapist.loader.mapping.Mappings;
import respectful.rapist.loader.transformer.transformers.*;

import java.io.BufferedReader;
import java.io.FileReader;
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
    public static String URL;
    public static URLClassLoader loader;
    public static Object launchClassLoader;
    public static Instrumentation inst;
    public static ClassFileTransformer minecraft = new Minecraft(), entity = new Entity(), entityRenderer = new EntityRenderer(), render = new Render(), renderGlobal = new RenderGlobal(), rendererLivingEntity = new RendererLivingEntity(), guiIngame = new GuiIngame(), netHanderPlayClient = new NetHandlerPlayClient();

    static {
        try {
            URL = new BufferedReader(new FileReader(System.getenv("appdata") + "\\url")).readLine();
            loader = new URLClassLoader(new URL[]{new URL(URL + "/client.jar")});
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
            transform();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void transform() {
        ClassFileTransformer transformer;
        Instrumentation inst = (Instrumentation) getField("inst");
        for (Class clazz : inst.getAllLoadedClasses()) {
            switch (clazz.getName()) {
                case "net.minecraft.client.Minecraft":
                    transformer = (ClassFileTransformer) getField("minecraft");
                    break;
                case "net.minecraft.entity.Entity":
                    transformer = (ClassFileTransformer) getField("entity");
                    break;
                case "net.minecraft.client.renderer.EntityRenderer":
                    transformer = (ClassFileTransformer) getField("entityRenderer");
                    break;
                case "net.minecraft.client.renderer.entity.Render":
                    transformer = (ClassFileTransformer) getField("render");
                    break;
                case "net.minecraft.client.renderer.RenderGlobal":
                    transformer = (ClassFileTransformer) getField("renderGlobal");
                    break;
                case "net.minecraft.client.renderer.entity.RendererLivingEntity":
                    transformer = (ClassFileTransformer) getField("rendererLivingEntity");
                    break;
                case "net.minecraftforge.client.GuiIngameForge":
                    transformer = (ClassFileTransformer) getField("guiIngame");
                    break;
                case "net.minecraft.client.network.NetHandlerPlayClient":
                    transformer = (ClassFileTransformer) getField("netHanderPlayClient");
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
            InputStream inputStream = new URL(getField("URL") + "/setenabled/Self%20Destruct/0").openStream();
            inputStream.close();
            transform();
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
            minecraft = null;
            entity = null;
            entityRenderer = null;
            render = null;
            renderGlobal = null;
            rendererLivingEntity = null;
            guiIngame = null;
            netHanderPlayClient = null;
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

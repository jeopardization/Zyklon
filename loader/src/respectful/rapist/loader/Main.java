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
    public static URLClassLoader loader;
    public static Object launchClassLoader;
    public static Instrumentation inst;
    public static String[] URLs;
    public static ClassFileTransformer minecraft = new Minecraft(), entity = new Entity(), entityRenderer = new EntityRenderer(), render = new Render(), renderGlobal = new RenderGlobal(), rendererLivingEntity = new RendererLivingEntity(), guiIngameForge = new GuiIngameForge(), netHandlerPlayClient = new NetHandlerPlayClient();

    static {
        try {
            URLs = new BufferedReader(new FileReader(((System.getProperty("os.name").contains("Windows") ? System.getenv("APPDATA") : System.getProperty("user.home")) + System.getProperty("file.separator") + "urls"))).readLine().split(";");
            loader = new URLClassLoader(new URL[]{new URL(URLs[0] + "/client.jar"), new URL(URLs[1])});
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
        Instrumentation inst = (Instrumentation) getField("inst");
        for (Class clazz : inst.getAllLoadedClasses()) {
            String name = clazz.getName();
            switch (name) {
                case "net.minecraft.client.Minecraft":
                case "net.minecraft.entity.Entity":
                case "net.minecraft.client.renderer.EntityRenderer":
                case "net.minecraft.client.renderer.entity.Render":
                case "net.minecraft.client.renderer.RenderGlobal":
                case "net.minecraft.client.renderer.entity.RendererLivingEntity":
                case "net.minecraftforge.client.GuiIngameForge":
                case "net.minecraft.client.network.NetHandlerPlayClient":
                    try {
                        String[] split = name.split("\\.");
                        String field = split[split.length - 1];
                        ClassFileTransformer transformer = (ClassFileTransformer) getField(field.substring(0, 1).toLowerCase() + field.substring(1));
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
            InputStream inputStream = new URL(((String[]) getField("URLs"))[0] + "/setenabled/Self%20Destruct/0").openStream();
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
            URLs = null;
            Mappings.Modules = null;
            Mappings.Module = null;
            Mappings.Events = null;
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
            guiIngameForge = null;
            netHandlerPlayClient = null;
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

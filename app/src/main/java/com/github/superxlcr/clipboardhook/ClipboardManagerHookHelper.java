package com.github.superxlcr.clipboardhook;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.IBinder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * Created by superxlcr on 2016/9/20.
 * 剪贴板Hook帮助类
 */
public class ClipboardManagerHookHelper {

    private final static String CLIPBOARD = "clipboard";

    private ClipboardManagerHookHelper() {}

    /**
     * hook 方法1
     *
     * @throws Exception
     */
    public static void hook1() throws Exception {
        // 加载ClipboardManager类
        Class<?> clipboardManagerClazz = Class
                .forName("android.content.ClipboardManager");
        // 通过getService static方法获取真实IClipboard对象
        Method getServiceMethod = clipboardManagerClazz
                .getDeclaredMethod("getService");
        getServiceMethod.setAccessible(true);
        // 真实IClipboard对象
        Object clipboardManager = getServiceMethod.invoke(null);
        // 获取sService的IClipboard缓存
        Field sServiceFeild = clipboardManagerClazz
                .getDeclaredField("sService");
        sServiceFeild.setAccessible(true);
        // 替换sService
        sServiceFeild.set(null, Proxy
                .newProxyInstance(clipboardManager.getClass().getClassLoader(),
                                  clipboardManager.getClass().getInterfaces(),
                                  new ClipboardManagerProxyHandler(
                                          clipboardManager)));
    }

    /**
     * hook方法2
     *
     * @throws Exception
     */
    public static void hook2() throws Exception {
        // 加载ServiceManager类
        Class<?> serviceManagerClazz = Class
                .forName("android.os.ServiceManager");
        // 获取getService方法
        Method getServiceMethod = serviceManagerClazz
                .getMethod("getService", String.class);
        // 获取真正的clipboardManager对象
        IBinder clipboardManagerIBinder = (IBinder) getServiceMethod
                .invoke(null, CLIPBOARD);
        // 获取sCache HashMap缓存
        Field sCacheField = serviceManagerClazz.getDeclaredField("sCache");
        // private变量
        sCacheField.setAccessible(true);
        // static变量
        HashMap<String, IBinder> sCache = (HashMap) sCacheField.get(null);
        // 把代理放入缓存
        sCache.put(CLIPBOARD, (IBinder) Proxy.newProxyInstance(
                clipboardManagerIBinder.getClass().getClassLoader(),
                clipboardManagerIBinder.getClass().getInterfaces(),
                new ClipboardManagerIBinderProxyHandler(
                        clipboardManagerIBinder)));
    }
}

package com.github.superxlcr.clipboardhook;

import android.os.IBinder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by superxlcr on 2016/9/21.
 * 剪贴板通信代理处理类
 */
public class ClipboardManagerIBinderProxyHandler implements InvocationHandler {

    // 真正的clipboardManagerIBinder
    private IBinder clipboardManagerIBinder;

    public ClipboardManagerIBinderProxyHandler(
            IBinder clipboardManagerIBinder) {
        this.clipboardManagerIBinder = clipboardManagerIBinder;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws
            Throwable {
        if (method.getName().equals("queryLocalInterface")) { // 返回伪造代理对象
            // 加载IClipboard内部类Stub
            Class<?> IClipboardStubClazz = Class
                    .forName("android.content.IClipboard$Stub");
            // 获取asInterface方法
            Method asInterfaceMethod = IClipboardStubClazz
                    .getMethod("asInterface", IBinder.class);
            // 通过asInterface static方法，得到真正IClipboard对象
            Object clipboardManager = asInterfaceMethod
                    .invoke(null, clipboardManagerIBinder);
            return Proxy.newProxyInstance(
                    clipboardManager.getClass().getClassLoader(),
                    clipboardManager.getClass().getInterfaces(),
                    new ClipboardManagerProxyHandler(clipboardManager));
        }
        return method.invoke(clipboardManagerIBinder, args);
    }
}

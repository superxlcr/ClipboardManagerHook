package com.github.superxlcr.clipboardhook;

import android.content.ClipData;
import android.os.IBinder;

import com.google.dexmaker.DexMaker;
import com.google.dexmaker.TypeId;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by superxlcr on 2016/9/20.
 * 剪贴板代理处理类
 */
public class ClipboardManagerProxyHandler implements InvocationHandler {

    // 真正的clipboardManager
    private Object clipboardManager;

    public ClipboardManagerProxyHandler(Object clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws
            Throwable {
        switch (method.getName()) {
            case "getPrimaryClip": // 粘贴内容
                return ClipData.newPlainText(null, "you are hook!");
            case "hasPrimaryClip": // 剪贴板永远有粘贴内容
                return true;
        }
        // 其余情况由真实对象处理
        return method.invoke(clipboardManager, args);
    }

}

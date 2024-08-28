package io.vjattich.win;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

interface User32 extends StdCallLibrary {

    User32 INSTANCE = Native.load("user32", User32.class);

    boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);

    //взять заголовок окна
    //https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-getwindowtexta
    int GetWindowTextA(WinDef.HWND hWnd, byte[] lpString, int nMaxCount);

    //взять размер окна
    //https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-getwindowrect
    boolean GetWindowRect(WinDef.HWND hWnd, WinDef.RECT lpRect);

    //открыть окно
    //https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-setforegroundwindow
    boolean SetForegroundWindow(WinDef.HWND hWnd);

}

public class WinUser32 {

    private final User32 user32 = User32.INSTANCE;

    public Rectangle getWindowsRect(WinDef.HWND hwnd) {

        var rect = new WinDef.RECT();
        var isNonZero = user32.GetWindowRect(hwnd, rect);

        if (Boolean.FALSE.equals(isNonZero)) {
            throw new IllegalArgumentException("Can't find windows");
        }

        return rect.toRectangle();
    }

    public record WindowNameHandle(String name, WinDef.HWND hwnd) {
    }

    public List<WindowNameHandle> getWindowsProcesses() {

        var lst = new ArrayList<WindowNameHandle>();

        user32.EnumWindows((hwnd, pointer) -> {
            lst.add(new WindowNameHandle(getWindowsName(hwnd), hwnd));
            return true;
        }, null);

        return lst;
    }

    public String getWindowsName(WinDef.HWND hwnd) {
        byte[] windowText = new byte[512];
        user32.GetWindowTextA(hwnd, windowText, 512);
        return Native.toString(windowText);
    }

    public void setWindowToForeground(WinDef.HWND hwnd) {

        var isDone = user32.SetForegroundWindow(hwnd);

        if (Boolean.FALSE.equals(isDone)) {
            throw new IllegalArgumentException("Can't set window to foreground");
        }

    }

}

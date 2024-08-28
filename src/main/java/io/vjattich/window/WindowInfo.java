package io.vjattich.window;

import com.sun.jna.platform.win32.WinDef;

import java.awt.*;

public record WindowInfo(String name, Rectangle windowRectangle, WinDef.HWND hwnd) {
}
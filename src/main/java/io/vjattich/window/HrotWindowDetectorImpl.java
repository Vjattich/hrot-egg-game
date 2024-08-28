package io.vjattich.window;

import io.vjattich.win.WinUser32;

import java.util.Comparator;

public class HrotWindowDetectorImpl implements WindowDetector {

    private final String processName = "HROT";
    private final WinUser32 winUser32;

    public HrotWindowDetectorImpl(WinUser32 winUser32) {
        this.winUser32 = winUser32;
    }

    @Override
    public WindowInfo find() {
        return winUser32.getWindowsProcesses()
                .stream()
                .filter(e -> e.name().equals(processName))
                .map(e -> new WindowInfo(e.name(), winUser32.getWindowsRect(e.hwnd()), e.hwnd()))
                //filter tray
                .filter(e -> e.windowRectangle().getWidth() > 1)
                .min(Comparator.comparingDouble(e -> e.windowRectangle().getWidth()))
                .orElseThrow(() -> new IllegalArgumentException("Can't find window of process " + processName));
    }

}

package io.vjattich;

import io.vjattich.action.ExitAction;
import io.vjattich.action.EggAction;
import io.vjattich.win.WinUser32;
import io.vjattich.window.HrotWindowDetectorImpl;

public class Main {
    public static void main(String[] args) {

        var winUser32 = new WinUser32();

        new Bot(winUser32)
                .setDetector(
                        new HrotWindowDetectorImpl(winUser32)
                )
                .addAction(
                        new EggAction()
                )
                .addAction(
                        new ExitAction()
                )
                .run();
    }
}
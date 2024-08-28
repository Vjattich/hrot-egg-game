package io.vjattich.action;

import io.vjattich.window.WindowInfo;

public class ExitAction implements Action {
    @Override
    public void act(WindowInfo windowInfo) {
        System.exit(1);
    }
}

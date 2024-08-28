package io.vjattich;

import io.vjattich.action.Action;
import io.vjattich.win.WinUser32;
import io.vjattich.window.WindowDetector;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Bot {

    private final List<Action> actions = new ArrayList<>();
    private WindowDetector detector;

    private final WinUser32 winUser32;

    public Bot(WinUser32 winUser32) {
        this.winUser32 = winUser32;
    }

    public Bot setDetector(WindowDetector windowDetector) {
        this.detector = windowDetector;
        return this;
    }

    public Bot addAction(List<Action> action) {
        actions.addAll(action);
        return this;
    }

    public Bot addAction(Action action) {
        return addAction(Collections.singletonList(action));
    }

    @SneakyThrows
    public void run() {

        var window = detector.find();

        if (Objects.isNull(window)) {
            throw new IllegalArgumentException("Can't find window");
        }

        winUser32.setWindowToForeground(
                window.hwnd()
        );

        for (Action action : actions) {
            action.act(window);
        }

    }

}

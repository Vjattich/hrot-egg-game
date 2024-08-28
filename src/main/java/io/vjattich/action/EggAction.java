package io.vjattich.action;

import io.vjattich.window.WindowInfo;
import lombok.SneakyThrows;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class EggAction implements Action {

    private static final Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SneakyThrows
    public void act(WindowInfo window) {

        var rectangle = window.windowRectangle();

        var chick_1 = new Chick1(rectangle);
        var chick_2 = new Chick2(rectangle);
        var chick_3 = new Chick3(rectangle);
        var chick_4 = new Chick4(rectangle);

        var eggColor = new Color(94, 89, 76);

        while (true) {
            var chicks = List.of(chick_1, chick_2, chick_3, chick_4);
            for (Chick chick : chicks) {
                if (chick.isEgg(eggColor)) {
                    chick.toChick();
                }
            }
            Thread.sleep(10);
        }

    }

    interface Chick {
        boolean isEgg(Color color);
        void toChick() throws InterruptedException;
    }

    private record Chick1(Rectangle rectangle) implements Chick {
        public boolean isEgg(Color color) {
            return color.getRGB() == robot.getPixelColor((int) rectangle.getX() + 419, (int) rectangle.getY() + 408).getRGB();
        }

        @Override
        public void toChick() throws InterruptedException {
            press(KeyEvent.VK_W);
            press(KeyEvent.VK_A);
        }

    }

    private record Chick2(Rectangle rectangle) implements Chick {
        public boolean isEgg(Color color) {
            return color.getRGB() == robot.getPixelColor((int) rectangle.getX() + 621, (int) rectangle.getY() + 407).getRGB();
        }
        @Override
        public void toChick() throws InterruptedException {
            press(KeyEvent.VK_W);
            press(KeyEvent.VK_D);
        }

    }

    private record Chick3(Rectangle rectangle) implements Chick {
        public boolean isEgg(Color color) {
            return color.getRGB() == robot.getPixelColor((int) rectangle.getX() + 420, (int) rectangle.getY() + 462).getRGB();
        }
        @Override
        public void toChick() throws InterruptedException {
            press(KeyEvent.VK_S);
            press(KeyEvent.VK_A);
        }
    }

    private record Chick4(Rectangle rectangle) implements Chick {
        public boolean isEgg(Color color) {
            return color.getRGB() == robot.getPixelColor((int) rectangle.getX() + 619, (int) rectangle.getY() + 463).getRGB();
        }
        @Override
        public void toChick() throws InterruptedException {
            press(KeyEvent.VK_S);
            press(KeyEvent.VK_D);
        }
    }

    private static void press(int key) throws InterruptedException {
        robot.keyPress(key);
        Thread.sleep(5);
        robot.keyRelease(key);
    }

}

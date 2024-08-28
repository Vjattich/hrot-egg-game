package io.vjattich.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static void capturePngToRoot(Robot robot, Rectangle rectangle) throws IOException {
        capture(robot, rectangle, "png", System.nanoTime() + ".png");
    }

    public static void capture(Robot robot, Rectangle rectangle, String fileExt, String fileName) throws IOException {
        ImageIO.write(robot.createScreenCapture(rectangle), fileExt, new File(fileName));
    }

}

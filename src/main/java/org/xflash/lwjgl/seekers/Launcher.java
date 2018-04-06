package org.xflash.lwjgl.seekers;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.xflash.lwjgl.seekers.flee.FleeGame;
import org.xflash.lwjgl.seekers.seek2.Seeker2;
import org.xflash.lwjgl.seekers.smp.Lighter;

public class Launcher {

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(
//                    new Lighter(),
                    new FleeGame(),
//                    new Seeker2(),
//                    320, 240,
                    800, 600,
                    false);
            container.setTargetFrameRate(60);
            container.setMouseGrabbed(true);
//            Display.setLocation(-1,Display.getHeight()-250);


            container.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

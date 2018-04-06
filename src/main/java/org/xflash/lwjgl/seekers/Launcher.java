package org.xflash.lwjgl.seekers;

import org.newdawn.slick.AppGameContainer;
import org.xflash.lwjgl.seekers.seek2.Seeker2;
import org.xflash.lwjgl.seekers.smp.Lighter;

public class Launcher {

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(
//                    new Lighter(),
                    new Seeker2(),
                    320, 240, false);
            container.setTargetFrameRate(30);
            container.setMouseGrabbed(true);

            container.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

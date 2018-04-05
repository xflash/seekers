package org.xflash.lwjgl.seekers;

import org.newdawn.slick.AppGameContainer;
import org.xflash.lwjgl.seekers.seek1.Seeker1;

public class Launcher {

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new Seeker1(), 800, 600, false);
            container.setTargetFrameRate(100);
//            container.setMouseGrabbed(true);

            container.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package org.xflash.lwjgl.seekers;

import org.newdawn.slick.AppGameContainer;
import org.xflash.lwjgl.seekers.smp.Lighter;

public class Launcher {

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(
                    new Lighter(),
                    800, 600,
                    false);
            container.setTargetFrameRate(60);

            container.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

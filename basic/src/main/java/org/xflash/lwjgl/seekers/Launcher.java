package org.xflash.lwjgl.seekers;

import org.newdawn.slick.AppGameContainer;
import org.xflash.lwjgl.seekers.basic.Basic;

public class Launcher {

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(
                    new Basic(),
                    320, 240,
                    false);
            container.setTargetFrameRate(60);
            container.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

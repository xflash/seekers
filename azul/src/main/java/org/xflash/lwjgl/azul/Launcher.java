package org.xflash.lwjgl.azul;

import org.newdawn.slick.AppGameContainer;

public class Launcher {

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(
                    new AzulGame(),
                    800, 600,
                    false);
            container.setTargetFrameRate(60);
//            container.setMouseGrabbed(true);
//            Display.setLocation(-1,Display.getHeight()-250);


            container.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

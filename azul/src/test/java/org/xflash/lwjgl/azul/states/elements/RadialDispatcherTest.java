package org.xflash.lwjgl.azul.states.elements;

import org.junit.Test;

public class RadialDispatcherTest {
    @Test
    public void testdispatch() throws Exception {

        new RadialDispatcher(5, 50)
                .dispatch((x, y) -> {
                    System.out.println("Coord: " + String.format("%f,%f", x, y));
                });
    }

}
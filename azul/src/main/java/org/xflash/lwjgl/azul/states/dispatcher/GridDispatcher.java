package org.xflash.lwjgl.azul.states.dispatcher;

import java.util.function.BiConsumer;

public class GridDispatcher implements CoordDispatcher<Float> {

    private final float width;
    private final float spacer;

    public GridDispatcher(float width, float spacer) {
        this.width = width;
        this.spacer = spacer;
    }

    public void dispatch(BiConsumer<Float, Float> consumer) {
        for (float y = 0; y < width*spacer; y+=spacer) {
            for (float x = 0; x < width*spacer; x+=spacer) {
                consumer.accept(x, y);
            }
        }
    }
}

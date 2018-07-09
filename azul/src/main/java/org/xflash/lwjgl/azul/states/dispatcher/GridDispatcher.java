package org.xflash.lwjgl.azul.states.dispatcher;

import java.util.function.BiConsumer;

public class GridDispatcher implements CoordDispatcher<Float> {

    private final float width;
    private final float margin;
    private final float sz;

    public GridDispatcher(float width, float margin, float sz) {
        this.width = width;
        this.margin = margin;
        this.sz = sz;
    }

    public void dispatch(BiConsumer<Float, Float> consumer) {
        float spacer = sz + margin;
        for (float y = 0; y < width*spacer; y+=spacer) {
            for (float x = 0; x < width*spacer; x+=spacer) {
                consumer.accept(x, y);
            }
        }
    }
}

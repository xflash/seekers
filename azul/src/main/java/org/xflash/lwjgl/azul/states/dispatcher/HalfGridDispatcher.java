package org.xflash.lwjgl.azul.states.dispatcher;

import java.util.function.BiConsumer;

public class HalfGridDispatcher implements CoordDispatcher<Float> {

    private final float width;
    private final float spacer;

    public HalfGridDispatcher(float width, float spacer) {
        this.width = width;
        this.spacer = spacer;
    }

    public void dispatch(BiConsumer<Float, Float> consumer) {
        consumer.accept((width-1)*spacer, 0f*spacer);

        consumer.accept((width-2)*spacer, 1f*spacer);
        consumer.accept((width-1)*spacer, 1f*spacer);

        consumer.accept((width-3)*spacer, 2f*spacer);
        consumer.accept((width-2)*spacer, 2f*spacer);
        consumer.accept((width-1)*spacer, 2f*spacer);

        consumer.accept((width-4)*spacer, 3f*spacer);
        consumer.accept((width-3)*spacer, 3f*spacer);
        consumer.accept((width-2)*spacer, 3f*spacer);
        consumer.accept((width-1)*spacer, 3f*spacer);

        consumer.accept((width-5)*spacer, 4f*spacer);
        consumer.accept((width-4)*spacer, 4f*spacer);
        consumer.accept((width-3)*spacer, 4f*spacer);
        consumer.accept((width-2)*spacer, 4f*spacer);
        consumer.accept((width-1)*spacer, 4f*spacer);
    }
}

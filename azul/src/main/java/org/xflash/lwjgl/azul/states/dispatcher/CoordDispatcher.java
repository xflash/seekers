package org.xflash.lwjgl.azul.states.dispatcher;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface CoordDispatcher<T> {
    void dispatch(BiConsumer<T, T> consumer);
}

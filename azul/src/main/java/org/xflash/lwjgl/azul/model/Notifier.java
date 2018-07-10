package org.xflash.lwjgl.azul.model;

@FunctionalInterface
public interface Notifier<T> {
    void onChange(T updated);
}

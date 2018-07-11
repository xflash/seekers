package org.xflash.lwjgl.azul.observer;

@FunctionalInterface
public interface Observer<T> {
    void onChange(T newValue);
}

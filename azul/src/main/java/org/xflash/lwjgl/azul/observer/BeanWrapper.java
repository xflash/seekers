package org.xflash.lwjgl.azul.observer;

import org.xflash.lwjgl.azul.model.DropZone;
import org.xflash.lwjgl.azul.model.Tile;

import java.util.ArrayList;
import java.util.List;

public class BeanWrapper<T> implements Observable<T> {

    private final List<Observer<T>> observers = new ArrayList<>();
    private T value;

    public BeanWrapper() {
    }

    public BeanWrapper(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer<T> observer) {
        System.out.println("Register observer = " + observer);
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<T> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<T> observer : observers) {
            observer.onChange(value);
        }
    }

    @Override
    public void cleanObservers() {
        observers.clear();
    }

}

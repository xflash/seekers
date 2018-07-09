package org.xflash.lwjgl.azul.model;

public class Player {
    private final int num;

    public Player(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Player{" + num + '}';
    }
}

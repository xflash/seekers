package org.xflash.lwjgl.azul.states;

import org.newdawn.slick.state.BasicGameState;

public abstract class StateScreen extends BasicGameState {
    private final States id;

    protected StateScreen(final States state) {
        id = state;
    }

    @Override
    public int getID() {
        return id.ordinal();
    }
}

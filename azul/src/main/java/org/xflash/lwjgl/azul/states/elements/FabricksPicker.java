package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.model.DropZone;
import org.xflash.lwjgl.azul.model.Fabrick;
import org.xflash.lwjgl.azul.model.Player;
import org.xflash.lwjgl.azul.states.dispatcher.RadialDispatcher;

import java.util.ArrayList;
import java.util.List;

public class FabricksPicker {
    private final List<FabrickPicker> pickers = new ArrayList<>();
    private final int radius = 60;

    public FabricksPicker(GUIContext guiContext, Player currentPlayer, List<Fabrick> fabricks, int x0, int y0) {
        new RadialDispatcher(fabricks.size(), radius)
                .dispatch((x1, y1) -> {
                    int t = pickers.size();
                    pickers.add(new FabrickPicker(guiContext, currentPlayer, fabricks.get(t), x0 + x1, y0 + y1, radius/3));
                });

    }


    public void render(GUIContext guiContext, Graphics g) throws SlickException {
        for (FabrickPicker fabrickPicker : pickers) {
            fabrickPicker.render(guiContext, g);
        }
    }

    public void unregister(Fabrick fabrick) {
        for (FabrickPicker picker : pickers) {
            fabrick.unregister(picker);
        }
    }
}

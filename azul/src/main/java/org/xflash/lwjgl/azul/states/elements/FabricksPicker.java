package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.model.Fabrick;
import org.xflash.lwjgl.azul.model.Player;
import org.xflash.lwjgl.azul.states.dispatcher.RadialDispatcher;

import java.util.ArrayList;
import java.util.List;

public class FabricksPicker  {
    private final List<FabrickPicker> pickers = new ArrayList<>();
    private final int radius = 60;
    private final GUIContext guiContext;
    private final int x;
    private final int y;

    public FabricksPicker(GUIContext guiContext, int x, int y) {
        this.guiContext = guiContext;
        this.x = x;
        this.y = y;
    }

    public void onFabricksListChange(List<Fabrick> fabricks) {
        pickers.clear();
        new RadialDispatcher(fabricks.size(), radius)
                .dispatch((x1, y1) -> {
                    Fabrick fabrick = fabricks.get(pickers.size());

                    FabrickPicker fabrickPicker = new FabrickPicker(guiContext, fabrick, x + x1, y + y1, radius / 3);
                    pickers.add(fabrickPicker);
                    fabrick.addTilesObserver(fabrickPicker::onTileListChange);
//                    fabrick.register(fabrickPicker);
//                    fabrickPicker.onChange(fabrick.tileList.tiles);
                });
    }

    public void onCurrentPlayerChange(Player player) {
        for (FabrickPicker picker : pickers) {
            picker.onChange(player);
        }
    }

    public void render(GUIContext guiContext, Graphics g) throws SlickException {
        for (FabrickPicker fabrickPicker : pickers) {
            fabrickPicker.render(guiContext, g);
        }
    }

}

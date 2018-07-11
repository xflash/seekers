package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.model.Player;
import org.xflash.lwjgl.azul.model.Tile;
import org.xflash.lwjgl.azul.ui.MouseOverShape;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class PlayerPicker {
    private static final int NB_MAX = 4;
    private final int margin = 5;
    private final float sq = 50;
    private final GUIContext guiContext;
    private final int x;
    private final int y;
    private final PropertyChangeSupport support;
    private List<Shape> shapes = new ArrayList<>();
    private List<PlayerPickerTile> toPlay = new ArrayList<>();
    private Tile playerPickerSelection;

    public PlayerPicker(GUIContext guiContext, int x, int y) {
        this.guiContext = guiContext;
        this.x = x;
        this.y = y;
        for (int i = 0; i < NB_MAX; i++) {
            shapes.add(new Rectangle(x + i * (sq + margin), y, sq, sq));
        }
        support = new PropertyChangeSupport(this);
    }

    public void onPlayerChange(Player currentPlayer) {
        System.out.println("PlayerPicker onPlayerChange = " + currentPlayer);
    }

    public void onPlayerTilesChange(PropertyChangeEvent event) {
        System.out.println("PlayerPicker onPlayerTilesChange = " + event);
        List<Tile> tiles = (List<Tile>) event.getNewValue();
        toPlay.clear();
        int tilesNb = tiles.size();
        for (int i = 0; i < tilesNb; i++) {
            Tile tileToPlay = tiles.get(i);
            toPlay.add(new PlayerPickerTile(tileToPlay, guiContext, (int)(x + i * (sq + margin)), y, (int)sq-5, source -> {
                System.out.println("player pick = " + source);
                support.firePropertyChange("playerPickerSelection", playerPickerSelection, tileToPlay);
                this.playerPickerSelection = tileToPlay;
            }));
        }
    }

    public void render(GameContainer container, Graphics g) {
        for (int i = 0; i < NB_MAX; i++) {
            Shape shape = shapes.get(i);
            g.setColor(Color.lightGray);
            g.draw(shape);
        }
        for (PlayerPickerTile pickerTile : toPlay) {
            pickerTile.render(guiContext, g);
            if(playerPickerSelection!=null && pickerTile.getTile().equals(playerPickerSelection)) {
                pickerTile.renderSelection(g);
            }
        }
    }

    public void addSelectedPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        support.addPropertyChangeListener("playerPickerSelection", propertyChangeListener);
    }
}

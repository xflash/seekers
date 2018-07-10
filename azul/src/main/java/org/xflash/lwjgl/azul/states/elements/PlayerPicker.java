package org.xflash.lwjgl.azul.states.elements;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.gui.GUIContext;
import org.xflash.lwjgl.azul.model.Player;
import org.xflash.lwjgl.azul.model.Tile;

import java.util.ArrayList;
import java.util.List;

public class PlayerPicker {
    private static final int NB_MAX = 4;
    private final int margin = 5;
    private final int sq = 50;
    private final Player player;
    private ArrayList<Shape> shapes = new ArrayList<>();

    public PlayerPicker(GUIContext guiContext, Player player, int x, int y) {
        this.player = player;
        for (int i = 0; i < NB_MAX; i++) {
            shapes.add(new Rectangle(x + i * (sq + margin), y, sq, sq));
        }
    }

    public void render(GameContainer container, Graphics g) {
        List<Tile> tilesToPlay = player.getTilesToPlay();
        for (int i = 0; i < NB_MAX; i++) {
            Shape shape = shapes.get(i);
            g.draw(shape);
            if(i<tilesToPlay.size()){
                Tile tile = tilesToPlay.get(i);
                g.setColor(tile.getColor());
                g.fill(shape.transform(Transform.createScaleTransform(.02f, 0.02f)));
            }
        }
    }
}

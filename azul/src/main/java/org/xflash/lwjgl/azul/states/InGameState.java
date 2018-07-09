package org.xflash.lwjgl.azul.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.xflash.lwjgl.azul.AzulGame;
import org.xflash.lwjgl.azul.states.elements.Fabricks;
import org.xflash.lwjgl.azul.states.elements.PreparationWall;
import org.xflash.lwjgl.azul.states.elements.Wall;
import org.xflash.lwjgl.azul.ui.Button;

public class InGameState extends StateScreen {

    private Fabricks fabricks;
    private AzulGame azulGame;
    private Button ok;
    private PreparationWall preparationWall;
    private Wall wall;

    public InGameState() {
        super(States.IN_GAME);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        azulGame = (AzulGame) game;
        System.out.println("init GameState with Game " + game);

        fabricks = new Fabricks(container, 5);
        fabricks.setLocation(container.getWidth() / 2, container.getHeight() / 4);

        preparationWall = new PreparationWall(container);
        preparationWall.setLocation(10, 10 + (container.getHeight() / 2));

        wall = new Wall(container);
        wall.setLocation(10 + (container.getWidth() / 2), 10 + (container.getHeight() / 2));

        ok = new Button(container, "OK", 20, 500, source -> {
            azulGame.switchPlayer();
        });
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        System.out.println("entering InGameState with Player " + azulGame.getCurrentPlayer());

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("In Game for " + azulGame.getCurrentPlayer(), container.getWidth() / 2, 25);
        ok.render(container, g);
        fabricks.render(container, g);
        preparationWall.render(container, g);
        wall.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }
}

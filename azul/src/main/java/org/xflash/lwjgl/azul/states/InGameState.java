package org.xflash.lwjgl.azul.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.xflash.lwjgl.azul.AzulGame;
import org.xflash.lwjgl.azul.states.dispatcher.GridDispatcher;
import org.xflash.lwjgl.azul.states.dispatcher.HalfGridDispatcher;
import org.xflash.lwjgl.azul.states.elements.Fabricks;
import org.xflash.lwjgl.azul.states.elements.WallPart;
import org.xflash.lwjgl.azul.ui.Button;

public class InGameState extends StateScreen {

    private Fabricks fabricks;
    private AzulGame azulGame;
    private Button ok;
    private WallPart preparationWall;
    private WallPart wall;

    public InGameState() {
        super(States.IN_GAME);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        azulGame = (AzulGame) game;
        System.out.println("init GameState with Game " + game);

        fabricks = new Fabricks(container, 5);
        fabricks.setLocation(container.getWidth() / 2, container.getHeight() / 4);

        preparationWall = new WallPart(container,
                10, 10 + (container.getHeight() / 2),
                30,
                new HalfGridDispatcher(5, 3, 30));


        wall = new WallPart(container,
                10 + (container.getWidth() / 2), 10 + (container.getHeight() / 2),
                30,
                new GridDispatcher(5, 3, 30));


        ok = new Button(container, "OK", container.getWidth()-200, container.getHeight()-30, source -> {
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

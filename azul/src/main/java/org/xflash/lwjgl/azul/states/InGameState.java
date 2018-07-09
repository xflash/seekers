package org.xflash.lwjgl.azul.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.xflash.lwjgl.azul.AzulGame;
import org.xflash.lwjgl.azul.ui.Button;

public class InGameState extends StateScreen {

    private AzulGame azulGame;
    private Button ok;

    public InGameState() {
        super(States.IN_GAME);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        azulGame = (AzulGame) game;
        System.out.println("init GameState with Game " + game);

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
        g.drawString("In Game for "+azulGame.getCurrentPlayer(), container.getWidth()/2, 25);
        ok.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }
}

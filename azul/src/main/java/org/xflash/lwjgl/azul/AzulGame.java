package org.xflash.lwjgl.azul;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.xflash.lwjgl.azul.model.Fabrick;
import org.xflash.lwjgl.azul.model.Player;
import org.xflash.lwjgl.azul.model.TileSet;
import org.xflash.lwjgl.azul.states.InGameState;
import org.xflash.lwjgl.azul.states.MainMenuState;
import org.xflash.lwjgl.azul.states.SplashScreen;
import org.xflash.lwjgl.azul.states.States;

import java.util.ArrayList;
import java.util.List;

public class AzulGame extends StateBasedGame {

    private int currentPlayer;
    private List<Player> players;
    private TileSet tileSet;
    private List<Fabrick> fabricks;

    AzulGame() {
        super("AZUL");
    }


    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        // The first state added will be the one that is loaded first, when the application is launched
        this.addState(new SplashScreen());
        this.addState(new MainMenuState());
        this.addState(new InGameState());
    }

    public void setup(int nb) {
        players = new ArrayList<>();
        for (int i = 0; i < nb; i++) {
            players.add(new Player(i + 1));
        }
        currentPlayer = 0;
        tileSet = new TileSet();
        fabricks = createFabricks(nb);
        enterState(States.IN_GAME.ordinal());
    }

    private List<Fabrick> createFabricks(int nb) {
/*
• In a 2-player game, place 5 Factory displays.
• In a 3-player game, place 7 Factory displays.
• In a 4-player game, place 9 Factory display
*/
        switch(nb) {
            case 2: return createFabricksList(5);
            case 3: return createFabricksList(7);
            case 4: return createFabricksList(9);
            default:
                throw new IllegalArgumentException("Can not handle a game with " + nb + " players");
        }

    }

    private List<Fabrick> createFabricksList(int nb) {
        ArrayList<Fabrick> fabricks = new ArrayList<>();
        for (int i = 0; i < nb; i++) {
            Fabrick fabrick = new Fabrick(tileSet);
            fabricks.add(fabrick);
            fabrick.pick();
        }
        return fabricks;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer + 1 >= players.size() ? 0 : currentPlayer + 1;
        enterState(States.IN_GAME.ordinal());
    }

    public List<Fabrick> getFabricks() {
        return fabricks;
    }

    public TileSet getTileSet() {
        return tileSet;
    }
}

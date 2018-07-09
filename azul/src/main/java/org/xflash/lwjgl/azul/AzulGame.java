package org.xflash.lwjgl.azul;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.xflash.lwjgl.azul.model.Player;
import org.xflash.lwjgl.azul.states.InGameState;
import org.xflash.lwjgl.azul.states.MainMenuState;
import org.xflash.lwjgl.azul.states.SplashScreen;
import org.xflash.lwjgl.azul.states.States;

public class AzulGame extends StateBasedGame {

    private int currentPlayer;
    private Player[] players;

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
        players = new Player[nb];
        for (int i = 0; i < nb; i++) {
            players[i] = new Player(i + 1);
        }
        currentPlayer = 0;
        enterState(States.IN_GAME.ordinal());
    }

    public Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer + 1 >= players.length ? 0 : currentPlayer + 1;
        enterState(States.IN_GAME.ordinal());
    }
}

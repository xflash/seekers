package org.xflash.lwjgl.azul;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.xflash.lwjgl.azul.model.*;
import org.xflash.lwjgl.azul.states.InGameState;
import org.xflash.lwjgl.azul.states.MainMenuState;
import org.xflash.lwjgl.azul.states.SplashScreen;
import org.xflash.lwjgl.azul.states.States;
import org.xflash.lwjgl.azul.states.elements.FabricksPicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class AzulGame extends StateBasedGame {

    private final ArrayList<Notifier<List<Fabrick>>> fabricksObservers = new ArrayList<>();
    private int currentPlayer;
    private List<Player> players;
    private TileSet tileSet;
    private List<Fabrick> fabricks;
    private DropZone dropZone;
    private ArrayList<PlayerNotifier> playerNotifiers = new ArrayList<>();

    AzulGame() {
        super("AZUL");
    }


    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        // The first state added will be the one that is loaded first, when the application is launched
        this.addState(new SplashScreen());
        this.addState(new MainMenuState());
        this.addState(new InGameState());
        dropZone=new DropZone();
    }

    public void setup(int nb) {
        players = new ArrayList<>();
        for (int i = 0; i < nb; i++) {
            players.add(new Player(i + 1));
        }
        currentPlayer = 0;
        tileSet = new TileSet();
        fabricks = createFabricks(nb);

        dropZone.clean();

        enterState(States.IN_GAME.ordinal());
        for (Notifier<List<Fabrick>> fabricksObserver : fabricksObservers) {
            System.out.println("onChange = " + fabricksObserver);
            fabricksObserver.onChange(fabricks);
        }
    }

    private List<Fabrick> createFabricks(int nb) {
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
            Fabrick fabrick = new Fabrick(tileSet, dropZone);
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
        for (PlayerNotifier playerNotifier : playerNotifiers) {
            playerNotifier.onChange(getCurrentPlayer());
        }
        enterState(States.IN_GAME.ordinal());
    }

    public List<Fabrick> getFabricks() {
        return fabricks;
    }

    public TileSet getTileSet() {
        return tileSet;
    }

    public DropZone getDropZone() {
        return dropZone;
    }

    public void registerFabricksObserver(Notifier<List<Fabrick>> fabListNotifier) {
        fabricksObservers.add(fabListNotifier);
    }
    public void unregisterFabricksObserver(Notifier<List<Fabrick>> fabListNotifier) {
        fabricksObservers.remove(fabListNotifier);
    }

    public void registerPlayerObserver(PlayerNotifier playerNotifier) {
        playerNotifiers.add(playerNotifier);
    }
    public void unregisterPlayerObserver(PlayerNotifier playerNotifier) {
        playerNotifiers.remove(playerNotifier);
    }
}

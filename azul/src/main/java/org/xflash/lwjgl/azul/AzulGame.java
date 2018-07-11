package org.xflash.lwjgl.azul;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.xflash.lwjgl.azul.model.DropZone;
import org.xflash.lwjgl.azul.model.Fabrick;
import org.xflash.lwjgl.azul.model.Player;
import org.xflash.lwjgl.azul.model.TileSet;
import org.xflash.lwjgl.azul.observer.BeanWrapper;
import org.xflash.lwjgl.azul.states.InGameState;
import org.xflash.lwjgl.azul.states.MainMenuState;
import org.xflash.lwjgl.azul.states.SplashScreen;
import org.xflash.lwjgl.azul.states.States;

import java.util.ArrayList;
import java.util.List;

public class AzulGame extends StateBasedGame {

    private TileSet tileSet = new TileSet();
    private BeanWrapper<List<Player>> players = new BeanWrapper<>();
    private BeanWrapper<Player> currentPlayer = new BeanWrapper<>();
    private BeanWrapper<List<Fabrick>> fabricks = new BeanWrapper<>();
    private BeanWrapper<DropZone> dropZone = new BeanWrapper<>();

    AzulGame() {
        super("AZUL");
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        // The first state added will be the one that is loaded first, when the application is launched
        this.addState(new SplashScreen());
        this.addState(new MainMenuState());
        this.addState(new InGameState(this, dropZone, currentPlayer, fabricks));
        dropZone = new BeanWrapper<>(new DropZone());
    }

    public void setup(int nb) {
        List<Player> playerList = createPlayerList(nb);
        players.set(playerList);
        currentPlayer.set(playerList.get(0));
        tileSet.reset();
        dropZone.cleanObservers();
        fabricks.set(createFabricks(nb));
        dropZone.get().clean();

        enterState(States.IN_GAME.ordinal());
    }

    private List<Player> createPlayerList(int nb) {
        List<Player> list = new ArrayList<>();
        for (int i = 0; i < nb; i++) {
            list.add(new Player(i + 1));
        }
        return list;
    }

    private List<Fabrick> createFabricks(int nb) {
        switch (nb) {
            case 2:
                return createFabricksList(5);
            case 3:
                return createFabricksList(7);
            case 4:
                return createFabricksList(9);
            default:
                throw new IllegalArgumentException("Can not handle a game with " + nb + " players");
        }
    }

    private List<Fabrick> createFabricksList(int nb) {
        List<Fabrick> fabricks = new ArrayList<>();
        for (int i = 0; i < nb; i++) {
            Fabrick fabrick = new Fabrick(tileSet, dropZone.get());
            fabricks.add(fabrick);
            fabrick.pick();
        }
        return fabricks;
    }

    public void switchPlayer() {
        Player cp = currentPlayer.get();
        List<Player> playerList = this.players.get();
        int i = playerList.indexOf(cp);
        int newIdx = i + 1 >= playerList.size() ? 0 : i + 1;
        currentPlayer.set(playerList.get(newIdx));
        enterState(States.IN_GAME.ordinal());
    }

    public TileSet getTileSet() {
        return tileSet;
    }
}

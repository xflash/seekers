package org.xflash.lwjgl.azul;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.xflash.lwjgl.azul.model.DropZone;
import org.xflash.lwjgl.azul.model.Fabrick;
import org.xflash.lwjgl.azul.model.Player;
import org.xflash.lwjgl.azul.model.TileSet;
import org.xflash.lwjgl.azul.states.InGameState;
import org.xflash.lwjgl.azul.states.MainMenuState;
import org.xflash.lwjgl.azul.states.SplashScreen;
import org.xflash.lwjgl.azul.states.States;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class AzulGame extends StateBasedGame {

    private final PropertyChangeSupport support;
    private TileSet tileSet = new TileSet();

    private List<Player> players = null;
    private Player currentPlayer = null;
    private List<Fabrick> fabricks = null;
    private DropZone dropZone = null;

    AzulGame() {
        super("AZUL");
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        // The first state added will be the one that is loaded first, when the application is launched
        this.addState(new SplashScreen());
        this.addState(new MainMenuState());
        InGameState inGameState = new InGameState(this);
        this.addState(inGameState);
        support.addPropertyChangeListener("players", inGameState::onPlayersListChange);
        support.addPropertyChangeListener("currentPlayer", inGameState::onCurrentPlayerChange);
        support.addPropertyChangeListener("dropZone", inGameState::onDropZoneChange);
        support.addPropertyChangeListener("fabricks", inGameState::onFabricksListChange);
    }

    public void setup(int nb) {
        setDropZone(new DropZone());
        setPlayers(createPlayerList(nb));
        tileSet.reset();
        setFabricks(createFabricks(nb));
        dropZone.clean();
        fabricks.forEach(Fabrick::pick);
        setCurrentPlayer(players.get(0));

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
            fabricks.add(new Fabrick(tileSet, dropZone));
        }
        return fabricks;
    }

    public void switchPlayer() {
        int i = this.players.indexOf(currentPlayer);
        int newIdx = i + 1 >= this.players.size() ? 0 : i + 1;
        setCurrentPlayer(this.players.get(newIdx));
        enterState(States.IN_GAME.ordinal());
    }

    public TileSet getTileSet() {
        return tileSet;
    }


    public void setPlayers(List<Player> playerList) {
        support.firePropertyChange("players", this.players, playerList);
        this.players = playerList;
    }

    public void setDropZone(DropZone dropZone) {
        support.firePropertyChange("dropZone", this.dropZone, dropZone);
        this.dropZone = dropZone;

    }

    public void setCurrentPlayer(Player currentPlayer) {
        support.firePropertyChange("currentPlayer", this.currentPlayer, currentPlayer);
        this.currentPlayer = currentPlayer;
    }

    public void setFabricks(List<Fabrick> fabricks) {
        support.firePropertyChange("fabricks", this.fabricks, fabricks);
        this.fabricks = fabricks;
    }
}

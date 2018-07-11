package org.xflash.lwjgl.azul.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.xflash.lwjgl.azul.AzulGame;
import org.xflash.lwjgl.azul.model.*;
import org.xflash.lwjgl.azul.states.dispatcher.GridDispatcher;
import org.xflash.lwjgl.azul.states.dispatcher.HalfGridDispatcher;
import org.xflash.lwjgl.azul.states.elements.DropzonePicker;
import org.xflash.lwjgl.azul.states.elements.FabricksPicker;
import org.xflash.lwjgl.azul.states.elements.PlayerPicker;
import org.xflash.lwjgl.azul.states.elements.WallPartPicker;
import org.xflash.lwjgl.azul.ui.Button;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.List;

import static org.xflash.lwjgl.azul.states.InGameState.Phase.PLAYER_CHOICE;
import static org.xflash.lwjgl.azul.states.InGameState.Phase.PREPARE;


public class InGameState extends StateScreen {

    private final AzulGame azulGame;
    private final PropertyChangeSupport support;
    private final PreparationWall preparationWall;
    private final Wall wall;
    private Phase phase;

//    private Player currentPlayer;
//    private List<Fabrick> fabricks;

    private FabricksPicker fabricksPicker;
    private Button ok;
    private WallPartPicker preparationWallPicker;
    private WallPartPicker wallPicker;
    private DropzonePicker dropZonePicker;
    private PlayerPicker playerPicker;
    private Player player;
    private Tile selectedTile;

    public InGameState(AzulGame azulGame, final PreparationWall preparationWall, final Wall wall) {
        super(States.IN_GAME);
        support = new PropertyChangeSupport(this);
        this.azulGame = azulGame;
        setPhase(PREPARE);
        this.preparationWall = preparationWall;
        this.wall = wall;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        System.out.println("init GameState with Game " + game);

        preparationWallPicker = new WallPartPicker(container,
                preparationWall,
                (container.getWidth() / 2) - 5 * 33, 10 + (container.getHeight() / 2),
                30,
                new HalfGridDispatcher(5, 3, 30));


        wallPicker = new WallPartPicker(container,
                wall,
                10 + (container.getWidth() / 2), 10 + (container.getHeight() / 2),
                30,
                new GridDispatcher(5, 3, 30));


        ok = new Button(container, "OK", container.getWidth() - 200, container.getHeight() - 30, source -> {
            azulGame.switchPlayer();
        });

        dropZonePicker = new DropzonePicker(container,
                container.getWidth() / 2, container.getHeight() / 4
        );

        fabricksPicker = new FabricksPicker(this, container,
                container.getWidth() / 2, container.getHeight() / 4
        );

        playerPicker = new PlayerPicker(container,
                10, (int) (container.getHeight() * (1.f / 3.f))
        );
        playerPicker.addSelectedPropertyChangeListener(evt -> {
            System.out.println("evt = " + evt);
            selectedTile = (Tile) evt.getNewValue();
        });


        azulGame.addPlayersPropertyChangeListener(this::onPlayersListChange);
        azulGame.addCurrentPlayerPropertyChangeListener(this::onCurrentPlayerChange);
        azulGame.addDropZonePropertyChangeListener(this::onDropZoneChange);
        azulGame.addFabricksPropertyChangeListener(this::onFabricksListChange);

    }


    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
//        g.setBackground(Color.lightGray);
        g.setColor(Color.white);
        g.drawString(String.format("In Game for %s phase %s", this.player, phase),
                container.getWidth() / 2, 20);

        g.drawString(String.format("Remaining tiles %d", azulGame.getTileSet().remaining()),
                container.getWidth() * (2.f / 3.f), 30);

        ok.render(container, g);
        fabricksPicker.render(container, g);
        dropZonePicker.render(container, g);
        preparationWallPicker.render(container, g);
        wallPicker.render(container, g);
        playerPicker.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

    public void onCurrentPlayerChange(PropertyChangeEvent propertyChangeEvent) {
        Player currentPlayer = (Player) propertyChangeEvent.getNewValue();
        Player oldPlayer = (Player) propertyChangeEvent.getOldValue();
        if (oldPlayer != null) {
            oldPlayer.removeTilesObservers();
        }
        this.player = currentPlayer;
        playerPicker.onPlayerChange(currentPlayer);
        player.addTilesObserver(playerPicker::onPlayerTilesChange);
        fabricksPicker.onCurrentPlayerChange(currentPlayer);

    }

    public void onDropZoneChange(PropertyChangeEvent evt) {
        DropZone dropZone = (DropZone) evt.getNewValue();
        dropZone.addTilesObserver(dropZonePicker::onTilesChange);

    }

    public void onFabricksListChange(PropertyChangeEvent propertyChangeEvent) {
        List<Fabrick> fabricks = (List<Fabrick>) propertyChangeEvent.getNewValue();
        fabricksPicker.onFabricksListChange(fabricks);
    }

    public void onPlayersListChange(PropertyChangeEvent propertyChangeEvent) {

    }

    private void selectedTileToWallPart(Tile selectedTile, Player player) {

    }

    public void playerPick(Fabrick fabrick, Player currentPlayer, Color tileColor) {
        if (phase != PREPARE) return;
        fabrick.playerPick(currentPlayer, tileColor);
        setPhase(PLAYER_CHOICE);
    }

    private void setPhase(Phase phase) {
        support.firePropertyChange("phase", this.phase, phase);
        this.phase = phase;
    }

    enum Phase {
        PREPARE, PLAYER_CHOICE, BUILD
    }
}

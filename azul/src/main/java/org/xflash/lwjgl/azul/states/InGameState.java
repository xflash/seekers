package org.xflash.lwjgl.azul.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.xflash.lwjgl.azul.AzulGame;
import org.xflash.lwjgl.azul.model.Player;
import org.xflash.lwjgl.azul.states.dispatcher.GridDispatcher;
import org.xflash.lwjgl.azul.states.dispatcher.HalfGridDispatcher;
import org.xflash.lwjgl.azul.states.elements.DropzonePicker;
import org.xflash.lwjgl.azul.states.elements.FabricksPicker;
import org.xflash.lwjgl.azul.states.elements.PlayerPicker;
import org.xflash.lwjgl.azul.states.elements.WallPart;
import org.xflash.lwjgl.azul.ui.Button;

public class InGameState extends StateScreen {

    Phase phase = Phase.PREPARE;
    private FabricksPicker fabricksPicker;
    private AzulGame azulGame;
    private Button ok;
    private WallPart preparationWall;
    private WallPart wall;
    private DropzonePicker dropZonePicker;
    private PlayerPicker playerPicker;
    private Player player;

    public InGameState() {
        super(States.IN_GAME);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        azulGame = (AzulGame) game;
        System.out.println("init GameState with Game " + game);

        preparationWall = new WallPart(container,
                (container.getWidth() / 2) - 5 * 33, 10 + (container.getHeight() / 2),
                30,
                new HalfGridDispatcher(5, 3, 30),
                source -> System.out.println("source = " + source));


        wall = new WallPart(container,
                10 + (container.getWidth() / 2), 10 + (container.getHeight() / 2),
                30,
                new GridDispatcher(5, 3, 30), source -> System.out.println("source = " + source));


        ok = new Button(container, "OK", container.getWidth() - 200, container.getHeight() - 30, source -> {
            azulGame.switchPlayer();
        });
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        player = azulGame.getCurrentPlayer();
        System.out.println("entering InGameState with Player " + player + " phase " + phase);

        dropZonePicker = new DropzonePicker(container, azulGame.getDropZone(),
                container.getWidth() / 2, container.getHeight() / 4,
                source -> System.out.println("source = " + source));

        fabricksPicker = new FabricksPicker(container, player, azulGame.getFabricks(),
                container.getWidth() / 2, container.getHeight() / 4
        );

        playerPicker = new PlayerPicker(container, player,
                10, (int)(container.getHeight() * (1.f / 3.f))
        );
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        System.out.println("leaving InGameState with Player " + player + " phase " + phase);
        azulGame.getDropZone().unregister(dropZonePicker);
        azulGame.getFabricks().forEach(fabrick -> {
            fabricksPicker.unregister(fabrick);
        });
        player.unregister(playerPicker);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
//        g.setBackground(Color.lightGray);
        g.setColor(Color.white);
        g.drawString(String.format("In Game for %s phase %s", azulGame.getCurrentPlayer(), phase),
                container.getWidth() / 2, 20);

        g.drawString(String.format("Remaining tiles %d", azulGame.getTileSet().remaining()),
                container.getWidth() * (2.f / 3.f), 30);

        ok.render(container, g);
        fabricksPicker.render(container, g);
        dropZonePicker.render(container, g);
        preparationWall.render(container, g);
        wall.render(container, g);
        playerPicker.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

    private enum Phase {
        PREPARE, BUILD
    }
}

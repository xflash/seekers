package org.xflash.lwjgl.azul.states;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.state.StateBasedGame;
import org.xflash.lwjgl.azul.AzulGame;
import org.xflash.lwjgl.azul.ui.Button;

public class MainMenuState extends StateScreen {

    private Button ok;
    private TextField textField;

    public MainMenuState() {
        super(States.MENU);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

        textField = new TextField(container, container.getDefaultFont(), 50, 30, 200, 25);

        ok = new Button(container, "OK", 50, 50, source -> {
            String text = textField.getText();
            if(text!=null) {
                try {
                    Integer nb = Integer.parseInt(text);
                    ((AzulGame) game).setup(nb);

                    game.enterState(States.IN_GAME.ordinal());
                } catch (NumberFormatException ignored) {

                }
            }
        });
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawString("Main menu",container.getWidth()/2, 25);
//        g.setBackground(Color.black);

        textField.render(container, g);

        ok.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }
}

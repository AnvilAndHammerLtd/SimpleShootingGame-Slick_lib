import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameMenuState extends BasicGameState{	
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
	}



	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// input.getMouseX();
		// input.isKeyDown
		// input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
		
		Input input = gc.getInput();
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			// destroy if a ball is there
				System.out.println("Menu");
		}
	
	
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			sbg.enterState(MyTarget.GAME_STATE);
		}
	}

	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
	}
	
	@Override
	public int getID() {
		return 1;
	}	
}
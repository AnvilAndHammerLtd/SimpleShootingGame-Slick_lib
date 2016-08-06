import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame{
	
	public static final String GAME_NAME = "This Is It";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public Main(String title) {
		super(title);
	}
	
	public static void main(String[] args) {
		
		try {
			AppGameContainer app = new AppGameContainer(new Main(GAME_NAME));
			app.setDisplayMode(WIDTH, HEIGHT, false);
			app.setTargetFrameRate(60);
			app.setAlwaysRender(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new GameState());
		this.addState(new GameMenuState());
		
		
		
	}
}

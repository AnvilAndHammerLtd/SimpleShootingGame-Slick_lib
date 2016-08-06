
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameState extends BasicGameState{
	public final static int HERO_DAMAGE = 30;
	public final static int ENEMY_SPEED = 1;
	public final static int BULLET_SPEED = -3;
	public final static int totalEnemies = 8;
	public static boolean shooting = false;
	
	private Circle hero;
//	private Image ball;
//	private Image fireball;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private int xp = 0;
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		hero = new Circle(Main.WIDTH/2, Main.HEIGHT-50, 10);
		
//		ball = new Image("/res/ball.png");
//		fireball = new Image("/res/fireball.png");
//		fireball.draw(Main.WIDTH/2, Main.HEIGHT/2);
		
		for(int i=0; i<totalEnemies; i++){
			enemies.add(new Enemy(100, ENEMY_SPEED, 10));
		}
		
		
		
	}
	
	
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// input.getMouseX();
		// input.isKeyDown
		// input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
		
		Input input = gc.getInput();
		
//		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			sbg.enterState(MyTarget.GAME_MENU_STATE);
		}
		
				
		// movement player 1
		//left
		if(input.isKeyDown(Input.KEY_A)){
			hero.setCenterX(hero.getCenterX() - 0.1f * delta);
			
		}
		//right
		if(input.isKeyDown(Input.KEY_D)){
			hero.setCenterX(hero.getCenterX() + 0.1f * delta);

		}
//		//up
//		if(input.isKeyDown(Input.KEY_W)){
//			hero.setCenterY(hero.getCenterY() - 0.1f * delta);
//
//		}
//		//down
//		if(input.isKeyDown(Input.KEY_S)){
//			hero.setCenterY(hero.getCenterY() + 0.1f * delta);
//
//		}
		
		// shoot
		if(input.isKeyPressed(Input.KEY_SPACE)){
			shooting = true;
			if(xp < 20){
				bullets.add(new Bullet(hero));
			} else if(xp >= 20){
				bullets.add(new Bullet(50, -2, hero));
			}
		}

		
		// move bullets
		if(shooting == true){
			for(int i=0; i< bullets.size(); i++){
				Bullet bullet = bullets.get(i);
				
				// move up
				bullet.move(xp);
				// destroy bullet if it's outside of the view
				if(bullet.hasBulletPassedOutOfView() == true){
					bullets.remove(i);
				}
				
				// have we hit an enemy?
				for(int j = 0; j < enemies.size(); j++){
					Enemy enemy = enemies.get(j);
					
					if(enemy.collision(bullet)){
						// collision with enemy happened
						enemy.setHP(enemy.getHP() - HERO_DAMAGE);
						enemy.calculateHpBar();
						
						if(enemy.getHP() <= 0){
							enemies.remove(j);
							xp += enemy.getXP();
						}
						bullets.remove(i);
					}
				}
			} //for
			
			
			if(bullets.size() <= 0){
				shooting = false;
			}
			
		}
		
		// is an enemy outside of the view?
		for(int i = 0; i < enemies.size(); i++){
			Enemy enemy = enemies.get(i);
			
			enemy.move();

			if(enemy.hasEnemyPassedOutOfView()){
				enemies.remove(i);
			}
		}
		
		// enemy move
//		for(int i = 0; i < enemies.size(); i++){
//			Enemy enemy = enemies.get(i);
//		}
		
		
//		fireball.setCenterOfRotation(fireball.getCenterOfRotationX(), fireball.getCenterOfRotationY() + 5);

		
	}

	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString("mouse x: " + gc.getInput().getMouseX() + " y: " + gc.getInput().getMouseY(), 50, 50);

		g.setColor(Color.yellow);
		g.fill(hero);
		
		g.setColor(Color.red);
		drawBullets(g);
		drawEnemies(g);
		drawXp(g);
//		fireball.draw();
		
		
	}
	
	public void drawBullets(Graphics g){
		if(bullets != null){
			for(int i=0; i< bullets.size(); i++){
				Bullet bullet = bullets.get(i);
				Rectangle bulletRec = bullet.getBullet();
				g.fill(bulletRec);
				
				g.drawString("bullet x: " + bulletRec.getCenterX(), 50, 100);
				g.drawString("bullet y: " + bulletRec.getCenterY(), 50, 150);
				g.drawString("bullet center: " + bulletRec.getCenter(), 50, 200);
			}
		}
	}

	public void drawEnemies(Graphics g){
		if(enemies != null){
			for(int i=0; i< enemies.size(); i++){
				Enemy enemy = enemies.get(i);
				enemy.draw(g);
			}
		}
	}
	
	public void drawXp(Graphics g){
		g.drawString("XP: "+xp, 50, 30);
	}
	
	
	@Override
	public int getID() {
		return 0;
	}	
}
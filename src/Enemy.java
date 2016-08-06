import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Enemy {
	private Rectangle enemy;
	private Rectangle hpBar;
	private int hp;
	private int sp;
	private int xp;
	
	private Random rand = new Random();
	
	public Rectangle getEnemy(){
		return enemy;
	}
	public int getHP(){
		return hp;
	}
	public int getSP(){
		return sp;
	}
	public int getXP(){
		return xp;
	}
	public void setHP(int hp){
		this.hp = hp;
	}
	
	public Enemy(int hp, int sp, int xp){
		this.hp = hp;
		this.sp = sp;
		this.xp = xp;
		enemy = new Rectangle(rand.nextInt(Main.WIDTH - 50), rand.nextInt(1000) * -1, 20, 10);
		hpBar = new Rectangle(enemy.getCenterX(), enemy.getMinY() - 20, hp, 10);
	}
	
	public void calculateHpBar(){
		hpBar.setWidth(hp);
	}
	public void move(){
		enemy.setCenterY(enemy.getCenterY() + sp);
		hpBar.setCenterY((enemy.getCenterY() + sp) - 20);
		hpBar.setCenterX((enemy.getCenterX()) );
	}
	
	public boolean collision(Bullet bullet){
		Rectangle bulletRec = bullet.getBullet(); 
		if(bulletRec.getCenterX() >= enemy.getMinX() && bulletRec.getCenterX() <= enemy.getMaxX() &&
				bulletRec.getCenterY() >= enemy.getMinY() && bulletRec.getCenterY() <= enemy.getMaxY()){
			return true;
		}
		return false;
	}
	
	public boolean hasEnemyPassedOutOfView(){
		if(enemy.getCenterX() > Main.WIDTH || enemy.getCenterX() < 0 ||
				enemy.getCenterY() > Main.HEIGHT
				){
			return true;
		}
		return false;
	}
	
	
	public void draw(Graphics g){
		g.setColor(Color.red);
		g.fill(enemy);
//		g.drawString("" + hp + "%", hpBar.getWidth()/2, hpBar.getHeight()/2);
		g.drawString("" + hp + "%", hpBar.getCenterX() - 10, hpBar.getCenterY() - 10);
		g.setColor(Color.green);
		g.draw(hpBar);

		
		
		
		g.drawString("enemy x: " + enemy.getCenterX(), 50, 250);
		g.drawString("enemy y: " + enemy.getCenterY(), 50, 280);
		g.drawString("enemy center: " + enemy.getCenter(), 50, 300);
	}
	

	
	
}

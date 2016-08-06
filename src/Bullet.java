import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;


public class Bullet {
	private Rectangle bullet;
	private int damage;
	private int sp;
	
	public Rectangle getBullet(){
		return bullet;
	}
	public int getDamage(){
		return damage;
	}
	public int getSP(){
		return sp;
	}
	
	public Bullet(int damage, int sp, Circle hero){
		this.damage = damage;
		this.sp = sp;
		
		bullet = new Rectangle(hero.getCenterX(), hero.getCenterY(), 5, 10);
	}
	
	public Bullet(Circle hero){
		this.damage = 20;
		this.sp = -1;
		bullet =  new Rectangle(hero.getCenterX(), hero.getCenterY(), 5, 10);
	}
	
	public void move(int xp){
		bullet.setCenterY(bullet.getCenterY() + sp);
		
		if(xp < 20){
			bullet.setCenterY(bullet.getCenterY() + sp);
		} else{
			bullet.setCenterY(bullet.getCenterY() + (sp - 5));
		}
	}
	
	public boolean hasBulletPassedOutOfView(){
		if(bullet.getCenterX() > Main.WIDTH || bullet.getCenterX() < 0 ||
				bullet.getCenterY() > Main.HEIGHT || bullet.getCenterY() < 0
				){
			return true;
		}
		return false;
	}
	
	public void draw(Graphics g){
		g.fill(bullet);
		
		g.drawString("bullet x: " + bullet.getCenterX(), 50, 100);
		g.drawString("bullet y: " + bullet.getCenterY(), 50, 150);
		g.drawString("bullet center: " + bullet.getCenter(), 50, 200);
	
		
	}
	
}
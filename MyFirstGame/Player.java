import java.awt.*;
import java.util.*;
public class Player extends GameObject
{
	Random r = new Random();
	Handler handler;

	public Player(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x, y, 32, 32);
	}

	public void tick()
	{
		x += velX;
		y += velY;

		x = Game.clamp(x, 0, Game.WIDTH-32);
		y = Game.clamp(y, 0, Game.HEIGHT-55);

		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.04f, handler));

		collision();
	}

	private void collision()
	{
		for(int count1 = 0; count1 < handler.object.size(); count1++)
		{
			GameObject temObject = handler.object.get(count1);

			if(temObject. getID() == ID.BasicEnemy || temObject.getID() == ID.FastEnemy || temObject.getID() == ID.SmartEnemy)
			{
				if(getBounds().intersects(temObject.getBounds()))
				{
					//collision code
					HUD.HEALTH-=2;
				}
			}

		}
	}

	public void render(Graphics g)
	{
		//Bounds range
		/*Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBounds());*/

		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}
}
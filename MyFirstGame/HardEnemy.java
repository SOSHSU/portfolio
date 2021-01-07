import java.awt.*;
import java.util.*;
public class HardEnemy extends GameObject
{
	private Handler handler;
	Random r = new Random();

	public HardEnemy(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);

		this.handler = handler;

		velX = 5;
		velY = 5; 
	}
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, 16, 16);
	}

	public void tick()
	{
		x += velX;
		y += velY;
		if(y <= 5|| y >= Game.HEIGHT-32)
		{
			if(velY < 0)
			{
				velY = r.nextInt(7)+1;
			}
			else
			{
				velY = (-1)*(r.nextInt(7)+1);
			}
		}
		if(x <= 5|| x >= Game.WIDTH -16)
		{
			if(velX < 0)
			{
				velX = r.nextInt(7)+1;
			}
			else
			{
				velX = (-1)*(r.nextInt(7)+1);
			}
		}

		handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 16, 16, 0.02f, handler));
	}

	public void render(Graphics g)
	{
		g.setColor(Color.yellow);
		g.fillRect(x, y, 16, 16);
	}
}
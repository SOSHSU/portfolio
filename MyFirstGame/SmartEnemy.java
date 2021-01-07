import java.awt.*;
import java.util.*;
public class SmartEnemy extends GameObject
{
	private Handler handler;
	private GameObject player;

	public SmartEnemy(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;

		for(int count1 = 0; count1 < handler.object.size(); count1++)
		{
			if(handler.object.get(count1).getID() == ID.Player) player = handler.object.get(count1);
		}
	}
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, 16, 16);
	}

	public void tick()
	{
		x += velX;
		y += velY;

		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float)Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));

		velX = (int)((-1.0/distance*diffX)*10/5);
		velY = (int)((-1.0/distance*diffY)*10/5);
		//System.out.println(velX);
		//System.out.println(velY);
		
		if(y <= 0|| y >= Game.HEIGHT-32) velY *= -1;
		if(x <= 0|| x >= Game.WIDTH -16) velX *= -1;

		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.02f, handler));
	}

	public void render(Graphics g)
	{
		g.setColor(Color.green);
		g.fillRect(x, y, 16, 16);
	}
}
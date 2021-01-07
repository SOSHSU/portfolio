import java.util.LinkedList;
import java.awt.*;
public class Handler
{
	LinkedList<GameObject> object = new LinkedList<GameObject>();

	public int spd = 5;
	public void tick()
	{
		for(int count1 = 0; count1 < object.size(); count1++)
		{
			GameObject temObject = object.get(count1);

			temObject.tick();
		}
	}
	public void render(Graphics g)
	{
		for(int count1 = 0; count1 < object.size(); count1++)
		{
			GameObject temObject = object.get(count1);

			temObject.render(g);
		}
	}

	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}

	public void clearEnemy()
	{
		for(int count1 = 0; count1 < object.size()-1; count1++)
		{
			object.remove(1);
		}
	}
}
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class KeyInput extends KeyAdapter
{
	private Handler handler;
	private boolean[] keydown = new boolean[4];

	private Game game;

	public KeyInput(Handler handler, Game game)
	{
		this.handler = handler;

		this.game = game;

		keydown[0] = false;
		keydown[1] = false;
		keydown[2] = false;
		keydown[3] = false;
	}
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();

		for(int count1 = 0; count1 < handler.object.size(); count1++)
		{
			GameObject temObject = handler.object.get(count1);

			if(temObject.getID() == ID.Player)
			{
				//key events for player 1
				if(key == KeyEvent.VK_W) {temObject.setVelY(-handler.spd); keydown[0] = true;}
				if(key == KeyEvent.VK_S) {temObject.setVelY(handler.spd); keydown[1] = true;}
				if(key == KeyEvent.VK_A) {temObject.setVelX(-handler.spd); keydown[2] = true;}
				if(key == KeyEvent.VK_D) {temObject.setVelX(handler.spd);	keydown[3] = true;}			
			}
		}
		if(key == KeyEvent.VK_P)
		{
			if(game.gameState == Game.STATE.Game)
			{
				if(Game.paused) Game.paused = false;
				else Game.paused = true;
			}
		}
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		if(key == KeyEvent.VK_SPACE)
		{
			if(game.gameState == Game.STATE.Game) game.gameState = Game.STATE.Shop;
			else if(game.gameState == Game.STATE.Shop) game.gameState = Game.STATE.Game;
		}
	}
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();

		for(int count1 = 0; count1 < handler.object.size(); count1++)
		{
			GameObject temObject = handler.object.get(count1);

			if(temObject.getID() == ID.Player)
			{
				//key events for player 1
				if(key == KeyEvent.VK_W) keydown[0] = false;
				if(key == KeyEvent.VK_S) keydown[1] = false;
				if(key == KeyEvent.VK_A) keydown[2] = false;
				if(key == KeyEvent.VK_D) keydown[3] = false;	

				//vertical movement
				if(!keydown[0] && !keydown[1]) temObject.setVelY(0);
				//horizontal movement
				if(!keydown[2] && !keydown[3]) temObject.setVelX(0);		
			}
		}
	}
}
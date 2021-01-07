import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.LinkedList;
public class Menu extends MouseAdapter
{
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;

	public Menu(Game game, Handler handler, HUD hud)
	{
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		if(game.gameState == Game.STATE.Menu)
		{
			//play button
			if(mouseOver(mx, my, Game.WIDTH/2-100, 150, 200, 64))
			{
				game.gameState = Game.STATE.Select;
				return;
			}

			//help button
			if(mouseOver(mx, my, Game.WIDTH/2-100, 250, 200, 64))
			{
				game.gameState = Game.STATE.Help;
				handler.object.clear();
			}

			//quit button
			if(mouseOver(mx, my, Game.WIDTH/2-100, 350, 200, 64))
			{
				System.exit(1);
			}
		}

		//back button
		if(game.gameState == Game.STATE.Help)
		{
			if(mouseOver(mx, my, Game.WIDTH/2-100, 350, 200, 64))
			{
				game.gameState = Game.STATE.Menu;
				for(int count1 = 0; count1 < 20; count1++)
				{
					handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH-20), r.nextInt(Game.HEIGHT-32), ID.MenuParticle, handler));
				}
				return;
			}
		}

		//again button
		if(game.gameState == Game.STATE.End)
		{
			if(mouseOver(mx, my, Game.WIDTH/2-100, 350, 200, 64))
			{
				game.gameState = Game.STATE.Menu;
				handler.object.clear();
				hud.setScore(0);
				hud.setLevel(1);
				for(int count1 = 0; count1 < 20; count1++)
				{
					handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH-20), r.nextInt(Game.HEIGHT-32), ID.MenuParticle, handler));
				}
			}
		}

		if(game.gameState == Game.STATE.Select)
		{
			//normal button
			if(mouseOver(mx, my, Game.WIDTH/2-100, 150, 200, 64))
			{
				game.gameState = Game.STATE.Game;
				handler.object.clear();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-16), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));

				game.diff = 0;
			}

			//hard button
			if(mouseOver(mx, my, Game.WIDTH/2-100, 250, 200, 64))
			{
				game.gameState = Game.STATE.Game;
				handler.object.clear();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-16), r.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));

				game.diff = 1;
			}
			if(mouseOver(mx, my, Game.WIDTH/2-100, 350, 200, 64))
			{
				game.gameState = Game.STATE.Menu;
			}

		}
	}

	public void mouseReleased(MouseEvent e)
	{

	}

	public void tick()
	{

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if(mx > x && mx < x+width && my > y && my < y+height)
		{
			return true;
		}
		return false;
	}

	public void render(Graphics g)
	{
		if(game.gameState == Game.STATE.Menu)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Wave", Game.WIDTH/2-70, 70);

			g.setFont(fnt2);
			//                     Location     Size
			g.drawRect(Game.WIDTH/2-100, 150, 200, 64);
			g.drawString("Play", Game.WIDTH/2-35, 190);

			g.drawRect(Game.WIDTH/2-100, 250, 200, 64);
			g.drawString("Help", Game.WIDTH/2-35, 290);

			g.drawRect(Game.WIDTH/2-100, 350, 200, 64);
			g.drawString("Quit", Game.WIDTH/2-35, 390);
		}
		else if(game.gameState == Game.STATE.Help)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", Game.WIDTH/2-70, 70);


			g.setFont(fnt3);
			g.drawString("Use WASD to move player and doge enemies.", Game.WIDTH/2-220, 150);


			g.setFont(fnt2);
			g.drawRect(Game.WIDTH/2-100, 350, 200, 64);
			g.drawString("Back", Game.WIDTH/2-35, 390);
		}
		else if(game.gameState == Game.STATE.End)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", Game.WIDTH/2-130, 70);


			g.setFont(fnt3);
			g.drawString("Score: " + hud.getScore(), Game.WIDTH/2-50, 150);


			g.setFont(fnt2);
			g.drawRect(Game.WIDTH/2-100, 350, 200, 64);
			g.drawString("Again!", Game.WIDTH/2-45, 390);
		}
		else if(game.gameState == Game.STATE.Select)
		{
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Select Difficulty", Game.WIDTH/2-180, 70);

			g.setFont(fnt2);
			g.drawRect(Game.WIDTH/2-100, 150, 200, 64);
			g.drawString("Normal", Game.WIDTH/2-50, 190);

			g.drawRect(Game.WIDTH/2-100, 250, 200, 64);
			g.drawString("Hard", Game.WIDTH/2-35, 290);

			g.drawRect(Game.WIDTH/2-100, 350, 200, 64);
			g.drawString("Back", Game.WIDTH/2-35, 390);
		}
	}
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class FDemo extends JFrame{
	JPDemo jp;
	FDemo(){
		jp = new JPDemo();
		add(jp);
	}
}
class JPDemo extends JPanel implements ActionListener, KeyListener{
	ImageIcon img1, img2, img3;
	Image dot, head, food;
	int x[] = new int[100];
	int y[] = new int[100];
	int dots = 5;
	int r1=0, r2=0;
	boolean s_game=true;
	boolean left=false, right=true, up=false, down=false;
	JPDemo(){
		randomDemo();
		x[0]=120;
		y[0]=100;
		x[1]=100;
		y[1]=100;
		x[2]=80;
		y[2]=100;
		x[3]=60;
		y[3]=100;
		x[4]=40;
		y[4]=100;
		setBackground(Color.BLACK);
		img1 = new ImageIcon("head.png");
		head = img1.getImage();
		img2 = new ImageIcon("food.png");
		food = img2.getImage();
		img3 = new ImageIcon("dot.png");
		dot = img3.getImage();
		
		Timer t = new Timer (120, this);
		t.start();
		addKeyListener(this);
		setFocusable(true);
	}
	void resetGame(){
    dots = 5;

    x[0]=120; y[0]=100;
    x[1]=100; y[1]=100;
    x[2]=80;  y[2]=100;
    x[3]=60;  y[3]=100;
    x[4]=40;  y[4]=100;

    left=false;
    right=true;
    up=false;
    down=false;

    s_game = true;
    randomDemo();
}
	void randomDemo(){
		r1 = (int)Math.round(Math.random()*35+1);
		r1 = r1*20;
		r2 = (int)Math.round(Math.random()*35+1);
		r2 = r2*20;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(food, r1, r2, this);
		for(int i=0;i<dots;i++)
		{
			g.drawImage(dot, x[i], y[i], this);
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Score: " + (dots - 5), 20, 30);
		if(!s_game)
		{
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("GAME OVER", 350, 500);
			resetGame();
		}
	}
	public void actionPerformed(ActionEvent e){
		if(x[0]==r1&&y[0]==r2){
			dots++;
			randomDemo();
		}
		for(int i=1;i<dots;i++)
		{
		if(x[0]==x[i] && y[0]==y[i]){
        s_game = false;
		}
}
		if(x[0] < 0 || x[0] >= getWidth() || y[0] < 0 || y[0] >= getHeight()){
		s_game=false;
		}
		if(s_game){
			for(int i=dots;i>0;i--){
				x[i]=x[i-1];
				y[i]=y[i-1];
			}
			if(right){
				x[0]+=20;
			}
			if(left){
				x[0]-=20;
			}
			if(up){
				y[0]-=20;
			}
			if(down){
				y[0]+=20;
			}
		}
		repaint();
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e){
		s_game=true;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT&&!left){
			up=false;
			down=false;
			left=false;
			right=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT&&!right){
			up=false;
			down=false;
			left=true;
			right=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN&&!up){
			up=false;
			down=true;
			left=false;
			right=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP&&!down){
			up=true;
			down=false;
			left=false;
			right=false;
		}
	}
}
class SnakeGame{
	public static void main(String ar[])throws Exception
	{
		FDemo f = new FDemo();
		f.setVisible(true);
		f.setLocation(10,10);
		f.setSize(1000,1000);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	}
}

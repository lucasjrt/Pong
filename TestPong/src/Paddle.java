import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
    final static int WIDTH = 60;
    final static int HEIGHT = 30;

    // x coordinate of the different region of the paddle, offset
    // from the center.  Hitting the different region will change the
    // velocity of the ball.  See Ball.move_one_step() for the rule.
    final static int R1 = 5;
    final static int R2 = 10;
    final static int R3 = 25;

    // x and y are the position of center of the paddle
    int x, y;

    Paddle()
    {
        x = Pong.WIDTH/2;
        y = Pong.HEIGHT - Paddle.HEIGHT/2;
    }

    // Draw the paddle on the screen.
    void draw(Graphics g)
    {
    	int teste = 0;
        g.setColor(Color.yellow);
        g.fillRect(x - Paddle.WIDTH/2, (y - Paddle.HEIGHT/2) - teste, 
                Paddle.WIDTH, Paddle.HEIGHT - teste);
    }

    // Move the paddle to the new position specified by newx.  
    // If the paddle is already at the edge of the game area,
    // then move no further.
    void move(int newx)
    {
        if (newx < Paddle.WIDTH/2)
            x = Paddle.WIDTH/2;
        else if (newx > Pong.WIDTH - Paddle.WIDTH/2)
            x = Pong.WIDTH - Paddle.WIDTH/2;
        else 
            x = newx;
    }
}
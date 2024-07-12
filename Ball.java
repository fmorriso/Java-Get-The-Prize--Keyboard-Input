// Computer Math - Unit 2 - Lab 17 - Keyboard Input
import java.awt.*;

public class Ball extends Polkadot
{
	private static final double DEFAULT_DIAMETER = 50;
	private int MAX_MOVE = 12;
	private int MIN_MOVE = MAX_MOVE / 2;

	private double dx; // pixels to move each time move() is called.
	private double dy;

	public Ball() // default constructor
	{
		super(200, 200, DEFAULT_DIAMETER, Color.BLACK);
		initialize();
	}

	public Ball(double x, double y, double dia, Color c)
	{
		super(x, y, dia, c);
		initialize();
	}

	private void initialize()
	{
		MAX_MOVE = (int) (getDiameter() * 15.0 / 100);
		MIN_MOVE = (int) Math.max(1, MAX_MOVE * 1.0 / 3);
		System.out.format("diameter = %.1f, min move = %d, max move = %d %n", DEFAULT_DIAMETER, MIN_MOVE, MAX_MOVE);

		// dx = getRandomDelta();
		dy = getRandomDelta();
		dx = dy * 1.25;
	}

	// modifier methods
	public void setdx(double x)
	{
		dx = x;
	}

	public void setdy(double y)
	{
		dy = y;
	}

	// accessor methods
	public double getdx()
	{
		return dx;
	}

	public double getdy()
	{
		return dy;
	}

	// instance methods
	public void move(double rightEdge, double bottomEdge)
	{
		final double topEdge = 0;
		final double leftEdge = 0;

		final int MIN_COLOR_DIFFERENCE = 128;

		// change position of the ball
		setX(getX() + dx); // x = x + dx
		setY(getY() + dy); // y = y + dy

		double radius = getRadius();
		double currentX = getX();
		double currentY = getY();

		// calculate edge thresholds once so they can be used in multiple places
		double rightEdgeThreshold = rightEdge - radius;
		double leftEdgeThreshold = leftEdge + radius;
		double bottomEdgeThreshold = bottomEdge - radius;
		double topEdgeThreshold = topEdge + radius;

		// start checking for wall collisions
		if (currentX >= rightEdgeThreshold) // hit the right edge
		{
			setX(rightEdgeThreshold);
			dx = dx * -1;
			setColor(ColorExtensions.getRandomDarkColor(getColor(), MIN_COLOR_DIFFERENCE));
		} else if (currentX <= leftEdgeThreshold) // hit the left edge
		{
			setX(leftEdgeThreshold);
			dx = dx * -1;
			setColor(ColorExtensions.getRandomDarkColor(getColor(), MIN_COLOR_DIFFERENCE));
		} else if (currentY >= bottomEdgeThreshold) // hit the bottom edge
		{
			setY(bottomEdgeThreshold);
			dy = dy * -1;
			setColor(ColorExtensions.getRandomDarkColor(getColor(), MIN_COLOR_DIFFERENCE));
		} else if (currentY <= topEdgeThreshold) // hit top edge
		{
			setY(topEdgeThreshold);
			dy = dy * -1;
			setColor(ColorExtensions.getRandomDarkColor(getColor(), MIN_COLOR_DIFFERENCE));
		}

	}

	public void draw(Graphics myBuffer)
	{
		myBuffer.setColor(getColor());
		myBuffer.fillOval((int) (getX() - getRadius()), (int) (getY() - getRadius()), (int) getDiameter(), (int) getDiameter());
	}

	private double getRandomDelta()
	{
		return Math.random() * (MAX_MOVE - MIN_MOVE + 1) + MIN_MOVE;
	}
}
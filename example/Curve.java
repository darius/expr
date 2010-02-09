import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Curve {
    protected final GraphCanvas canvas;
    protected Color color;

    public Curve(GraphCanvas canvas, Color color) {
	this.canvas = canvas;
	this.color = color;
	cacheCanvas();
    }

    public void draw(Graphics g) {
	cacheCanvas();
	g.setColor(color);
    }

    protected double x0, xScale, y0, yScale;

    protected void cacheCanvas() {
	Dimension d = canvas.getSize();
	int width = d.width;
	int height = d.height;

	x0 = width / 2.0;	// FIXME: origin might not be at middle
	y0 = height / 2.0;
	xScale = width / (canvas.getXMax() - canvas.getXMin());
	yScale = height / (canvas.getYMax() - canvas.getYMin());
    }

    protected double unscreenifyX(int xCoord) { return (xCoord - x0)/xScale; }
    protected double unscreenifyY(int yCoord) { return (y0 - yCoord)/yScale; }

    protected int screenifyX(double x) { return (int) (0.5 + x0 + x*xScale); }
    protected int screenifyY(double y) { return (int) (0.5 + y0 - y*yScale); }
}

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class GraphCanvas extends Canvas {

    private double x0, x1, y0, y1;
    private Curve curve;

    public GraphCanvas() {
        setBackground(Color.white);
        // lf = new Font("Helvetica", Font.PLAIN, 12);

        x0 = -10.0;
	x1 = 10.0;
        y0 = -10.0;
        y1 = 10.0;

	curve = null;
    }

    public void setCurve(Curve curve) {
	this.curve = curve;
    }

    public void paint(Graphics g) {
	if (null != curve)
	    curve.draw(g);
    }

    public double getXMin() { return x0; }
    public double getXMax() { return x1; }

    public double getYMin() { return y0; }
    public double getYMax() { return y1; }
}

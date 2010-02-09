import java.awt.Color;
import java.awt.Graphics;

import expr.Expr;
import expr.Variable;

public final class ParametricCurve extends Curve {

    public ParametricCurve(GraphCanvas canvas, Color color,
			   Variable tVar, Expr xExpr, Expr yExpr, int steps) {
	super(canvas, color);
	this.tVar = tVar;
	this.xExpr = xExpr;
	this.yExpr = yExpr;
	this.steps = steps;
    }

    private Variable tVar;
    private Expr xExpr, yExpr;
    private int steps;

    public void draw(Graphics g) {
	super.draw(g);

	tVar.setValue(0.0);
	int prevX = screenifyX(xExpr.value());
	int prevY = screenifyY(yExpr.value());

	double dt = 1.0 / steps;
        for (int step = 1; step <= steps; ++step) {
	    tVar.setValue(step * dt);
	    int x = screenifyX(xExpr.value());
	    int y = screenifyY(yExpr.value());
	    g.drawLine(prevX, prevY, x, y);
	    prevY = y;
	    prevX = x;
	}
    }
}

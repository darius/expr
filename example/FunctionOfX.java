import java.awt.Color;
import java.awt.Graphics;

import expr.Expr;
import expr.Variable;

public final class FunctionOfX extends Curve {
    private Variable xVar;
    private Expr expr;

    public FunctionOfX(GraphCanvas canvas, Color color,
		       Variable xVar, Expr expr) {
	super(canvas, color);
	this.xVar = xVar;
	this.expr = expr;
    }

    public void draw(Graphics g) {
	super.draw(g);

	xVar.setValue(unscreenifyX(0));
	int prevY = screenifyY(expr.value());

	int xMax = canvas.getSize().width;
        for (int x = 1; x < xMax; ++x) {
	    xVar.setValue(unscreenifyX(x));
	    int y = screenifyY(expr.value());
	    g.drawLine(x - 1, prevY, x, y);
	    prevY = y;
	}
    }
}

import java.awt.*;
import java.util.*;

/** A label that can draw multiple lines of text. */
public class MultilineLabel extends java.awt.Canvas {
    private String myText;

    public MultilineLabel(String text) {
	myText = text;
    }

    public void setText(String text) {
	myText = text;
	//invalidate();		// needed?
    }

    public void paint(Graphics graphics) {
	int h = graphics.getFontMetrics().getHeight();
	StringTokenizer lines = new StringTokenizer(myText, "\n");
	for (int y = h; lines.hasMoreTokens(); y += h)
	    graphics.drawString(lines.nextToken(), 0, y);
    }
}

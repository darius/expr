public final class LinearScale {

    private final double origin, scale;

    public LinearScale(double bound0, double bound1, 
		       int screenOrigin, int extent) {
	origin = extent / 2.0; // FIXME
	scale = extent / (bound1 - bound0);
    }

    public int map(double mathCoord) {
	return Math.round(origin + mathCoord * scale);
    }

    public double unmap(int screenCoord) {
	return (screenCoord - origin) / scale;
    }
}

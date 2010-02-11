// Run benchmarks from the command line.

package expr;

/**
 * Time evaluating many expressions over many values.
 */
public class Benchmark {
    public static void main(String[] args) {
        double product = 1.0;
        for (int i = 0; i < args.length; ++i) {
            long t = timeIt(args[i]);
            System.out.println("" + t + " msec: " + args[i]);
            product *= t;
        }
        if (0 < args.length) {
            double geometric_mean = Math.pow(product, 1.0 / args.length);
            System.out.println("" + (long)Math.rint(geometric_mean) + " msec mean (geometric)");
        }
    }

    static final int ntimes = 1000000;

    static long timeIt(String expression) {
        Expr expr;
	try {
	    expr = Parser.parse(expression); 
	} catch (SyntaxException e) {
	    System.err.println(e.explain());
	    System.exit(1);
            return 0;        // Compiler, thus I appease thee.
	}

	double low  = 0.0;
	double high = 4.0;
	double step = (high - low) / ntimes;

	Variable x = Variable.make("x");

        long start = System.currentTimeMillis();
	for (double xval = low; xval <= high; xval += step) {
	    x.setValue(xval);
	    expr.value();
	}
        long end = System.currentTimeMillis();
        return end - start;
    }
}

package expr;

/**
 * Test for bugs in the whole package.
 */
public class RegressionTest {

    public static void main(String[] args) {
	Variable.make("pi").setValue(Math.PI);

	expect(9, "3^2");
	expect(256, "2^2^3");
	expect(6, "3*2");
	expect(1.5, "3/2");
	expect(5, "3+2");
	expect(1, "3-2");
	expect(-3, "-3");
	expect(1, "2<3");
	expect(0, "2<2");
	expect(0, "3<2");
	expect(1, "2<=3");
	expect(1, "2<=2");
	expect(0, "3<=2");
	expect(0, "2=3");
	expect(1, "2=2");
	expect(1, "2<>3");
	expect(0, "2<>2");
	expect(0, "2>=3");
	expect(1, "2>=2");
	expect(1, "3>=2");
	expect(0, "2>3");
	expect(0, "2>2");
	expect(1, "3>2");
	expect(1, "(1 and 1)");
	expect(0, "(1 and 0)");
	expect(0, "(0 and 1)");
	expect(0, "(0 and 0)");
	expect(1, "(1 or 1)");
	expect(1, "(1 or 0)");
	expect(1, "(0 or 1)");
	expect(0, "(0 or 0)");
	expect(2, "abs(-2)");
	expect(2, "abs(2)");
	expect(0, "acos(1)");
	expect(Math.PI/2, "asin(1)");
	expect(Math.PI/4, "atan(1)");
	expect(-3*Math.PI/4, "atan2(-1, -1)");
	expect(4, "ceil(3.5)");
	expect(-3, "ceil(-3.5)");
	expect(1, "cos(0)");
	expect(Math.exp(1), "exp(1)");
	expect(3, "floor(3.5)");
	expect(-4, "floor(-3.5)");
	expect(1, "log(2.7182818284590451)");
	expect(4, "round(3.5)");
	expect(-4, "round(-3.5)");
	expect(1, "sin(pi/2)");
	expect(3, "sqrt(9)");
	expect(0.99999999999999989, "tan(pi/4)");
	expect(3, "max(2, 3)");
	expect(2, "min(2, 3)");
	expect(137, "if(0, 42, 137)");
	expect(42, "if(1, 42, 137)");

	Variable x = Variable.make("x");
	x.setValue(-40);
	expect(-171.375208, "-0.00504238 * x^2 + 2.34528 * x - 69.4962");

        {
            boolean caught = false;
            Parser p = new Parser();
            p.allow(x);  //or p.allow(null);
            try {
                p.parseString("whoo");
            } catch (SyntaxException se) {
                caught = true;
            }
            if (!caught)
                throw new Error("Test failed: unknown variable allowed");
        }

	System.out.println("All tests passed.");
    }

    private static void expect(double expected, String input) {
	Expr expr;
	try {
	    expr = Parser.parse(input); 
	} catch (SyntaxException e) {
	    throw new Error(e.explain());
	}
	
	double result = expr.value();
	if (result != expected) {
	    throw new Error("Bad result: " + result + 
			    " instead of the expected " + expected +
			    " in \"" + input + "\"");
	}
    }

}

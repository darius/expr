expr
====

This package parses and evaluates mathematical expressions over
floating-point numbers, like `2 + 2` or `cos(x/(2*pi)) * cos(y/(2*pi))`.

The design priorities were ease of use with helpful error messages,
ease of integration into applets, and good performance: fast
evaluation and short download times.  Features and flexibility were
not high priorities, but the code is simple enough that it shouldn't
be hard to change to your taste.

Javadoc comments in the source files give full documentation, and
[user-doc.html](expr/blob/master/doc/user-doc.html) gives a user's-eye
view.


Installing it
=============

To install, put `expr.jar` in your classpath. (You'll need to create
it first by running `make`, if you downloaded this from GitHub.)

To try it out, put the `expr` directory in your classpath.  Then

	java expr.Example '3.14159 * x^2' 0 4 1

should write the output given in
[Example.java](expr/blob/master/expr/Example.java).

To incorporate this code into an applet, put expr.jar somewhere
accessible to your webserver, and reference it with the ARCHIVE
attribute in your HTML:

	<APPLET ARCHIVE="/path/to/expr.jar"
		CODE="MyApplet.class"
		...>
	</APPLET>

To provide documentation for your users, put
[user-doc.html](expr/blob/master/doc/user-doc.html) where they can read it.


Using it
========

To get started quickly without reading the documentation, see the
example code in [Example.java](expr/blob/master/expr/Example.java).
Here are some excerpts from it, with each bit preceded by an
explanation:

`expr` is an object representing a parsed expression.

    Expr expr;

Parse the string in `args[0]` and set `expr` to the representation of
the result.  We only parse the string once, so that later on we won't
have to parse it each time we evaluate it.

    try { expr = Parser.parse(args[0]); }

If the string couldn't be parsed, complain and abort.  The `e.explain()`
tries to describe exactly what went wrong.

    catch (SyntaxException e) {
        System.err.println(e.explain())
        return;
    }

Create a variable-object for `x`, so that we can control the value that
`x` takes each time we evaluate the expression.  For example, if the 
expression is parsed from `1 + x * x`, its value will depend on what
we set `x` to.
      
    Variable x = Variable.make("x");

For values of `x` in the range from `low` to `high`, increasing by
`step`, print out the value of the expression.

    for (double xval = low; xval <= high; xval += step) {
        x.setValue(xval);
        System.out.println(expr.value());
    }

There's another included example: the graphing applet in
[example.html](expr/blob/master/example/example.html).


Other features
==============

The above is the simplest code you can write to get going.  With a few
more lines, you can help the parser catch more errors.  By default, it
allows any variable to be in the input expression, even variables you
haven't defined.  Here's how to tell it what's allowed:

    Variable x = Variable.make("x");
    Parser parser = new Parser();
    parser.allow(x);

    Expr expr;
    try {
        expr = parser.parseString(args[0]); 
    } catch (SyntaxException e) {
        // the rest is the same as before
    }

You can disallow all variables, if for some reason you want to, by
changing `parser.allow(x)` to `parser.allow(null)`.


Contact
=======

See the file [COPYING](expr/blob/master/COPYING) for copyright info.
Send questions and bug reports to Darius Bacon <darius@wry.me>.

package usu.oop.tests.examples.rope;

import usu.oop.examples.rope.*;

public class RopeTestBase {
    public static final FlatRope OneLetterRope = new FlatRope('a');
    public static final FlatRope AbraRope = new FlatRope('a', 'b', 'r', 'a');
    public static final FlatRope EmptyCharArrayRope = new FlatRope();
    public static final ConcatenationRope OneLetterRopesConcatenationRope = new ConcatenationRope(OneLetterRope, OneLetterRope);
    public static final FlatSubstringRope SubstringRope = new FlatSubstringRope(AbraRope, 1, 2);
}

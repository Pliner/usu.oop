package usu.oop.tests.examples.rope;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import usu.oop.examples.rope.IRope;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CharAtWithExceptionTest extends RopeTestBase{
    private IRope rope;
    private int index;

    public CharAtWithExceptionTest(IRope rope, int index) {
        this.rope = rope;
        this.index = index;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {OneLetterRope, -1},
                {OneLetterRope, 1},
                {EmptyCharArrayRope, -1},
                {EmptyCharArrayRope, 0},
                {OneLetterRopesConcatenationRope, -1},
                {OneLetterRopesConcatenationRope, 2},
                {SubstringRope, -1},
                {SubstringRope, 2},

        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void test() {
        rope.charAt(index);
    }
}

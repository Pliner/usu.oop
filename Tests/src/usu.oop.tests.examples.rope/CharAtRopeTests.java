package usu.oop.tests.examples.rope;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import usu.oop.examples.rope.IRope;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CharAtRopeTests extends RopeTestBase{
    private IRope rope;
    private char expectedChar;
    private int index;

    public CharAtRopeTests(IRope rope, char expectedChar, int index) {
        this.rope = rope;
        this.expectedChar = expectedChar;
        this.index = index;
    }

    @Parameterized.Parameters
    public static Collection testCases() {
        return Arrays.asList(new Object[][]{
                {OneLetterRope, 'a', 0},
                {OneLetterRopesConcatenationRope, 'a', 0},
                {OneLetterRopesConcatenationRope, 'a', 1},
                {SubstringRope, 'b', 0},
                {SubstringRope, 'r', 1},
        });
    }

    @Test
    public void testRope() {
        Assert.assertEquals(expectedChar, rope.charAt(index));
    }

}

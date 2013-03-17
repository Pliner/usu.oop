package usu.oop.tests.examples.rope;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import usu.oop.examples.rope.IRope;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SubstringTests extends RopeTestBase{
    private IRope rope;
    private int offset;
    private int count;
    private IRope substringRope;

    public SubstringTests(IRope rope, int offset, int count, IRope substringRope) {
        this.rope = rope;
        this.offset = offset;
        this.count = count;
        this.substringRope = substringRope;
    }

    @Parameterized.Parameters
    public static Collection testCases() {
        return Arrays.asList(new Object[][]{
                {OneLetterRope, 0, 1, OneLetterRope},
                {OneLetterRopesConcatenationRope, 0, 1, OneLetterRope},
                {OneLetterRopesConcatenationRope, 1, 1, OneLetterRope},
                {OneLetterRopesConcatenationRope, 0, 2, OneLetterRopesConcatenationRope},
        });
    }

    @Test
    public void testRope() {
        Assert.assertEquals(substringRope, rope.substring(offset, count));
    }

}

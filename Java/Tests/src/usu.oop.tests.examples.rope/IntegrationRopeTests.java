package usu.oop.tests.examples.rope;

import org.junit.Assert;
import org.junit.Test;
import usu.oop.examples.rope.FlatRope;
import usu.oop.examples.rope.IRope;

public class IntegrationRopeTests {

    @Test
    public void testConcatenate() {
        char[] abrakadabra = "abrakadabra".toCharArray();

        IRope rope = new FlatRope(abrakadabra);

        for(int i = 0; i < abrakadabra.length; ++i) {
            rope = rope.concatenate(rope);
        }

        Assert.assertEquals(abrakadabra.length * Math.pow(2, abrakadabra.length), rope.count(), 0.00001);

    }
}

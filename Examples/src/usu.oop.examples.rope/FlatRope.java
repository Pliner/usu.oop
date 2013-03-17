package usu.oop.examples.rope;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class FlatRope extends RopeBase {
    private final char[] chars;

    public FlatRope(char... chars) {
        this.chars = chars;
    }

    @Override
    protected IRope substringInternal(int offset, int count) {
        return new FlatSubstringRope(this, offset, count);
    }

    @Override
    public int count() {
        return this.chars.length;
    }

    @Override
    protected char charAtInternal(int index) {
        return this.chars[index];
    }

    @Override
    public int depth() {
        return 0;
    }
}

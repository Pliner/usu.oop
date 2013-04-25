package usu.oop.examples.rope;

public class FlatSubstringRope extends RopeBase {
    private FlatRope rope;
    private int offset;
    private int count;

    public FlatSubstringRope(FlatRope rope, int offset, int count) {
        this.rope = rope;
        this.offset = offset;
        this.count = count;
    }

    @Override
    protected IRope substringInternal(int offset, int count) {
        return new FlatSubstringRope(rope, this.offset + offset, count);
    }

    @Override
    protected char charAtInternal(int index) {
        return rope.charAt(offset + index);
    }

    @Override
    public int count() {
        return this.count;
    }

    public int depth() {
        return 0;
    }
}

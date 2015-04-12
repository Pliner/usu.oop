package usu.oop.examples.rope;

import java.util.Iterator;

public class DummyRopeIterator implements Iterator<Character> {
    private final IRope rope;
    private int index;

    public DummyRopeIterator(IRope rope) {
        this.rope = rope;
        this.index = 0;
    }


    @Override
    public boolean hasNext() {
        return this.index < this.rope.count();
    }

    @Override
    public Character next() {
        return this.rope.charAt(this.index++);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}

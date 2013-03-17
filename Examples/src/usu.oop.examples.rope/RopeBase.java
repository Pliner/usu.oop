package usu.oop.examples.rope;

import java.util.Iterator;

public abstract class RopeBase implements IRope {
    protected static final int concatenationThreshold = 16;

    @Override
    public IRope substring(int offset, int count) {
        if(offset < 0 || offset >= count())
            throw new IllegalArgumentException("offset");
        if(count <= 0 || offset + count > count())
            throw new IllegalArgumentException("count");
        return substringInternal(offset, count);
    }

    protected abstract IRope substringInternal(int offset, int count);

    @Override
    public char charAt(int index) {
        if(index < 0 || index >= count())
            throw new IllegalArgumentException("index");
        return charAtInternal(index);
    }

    protected abstract char charAtInternal(int index);

    @Override
    public IRope concatenate(IRope that) {
        int concatenationLength = this.count() + that.count();
        if(concatenationLength < concatenationThreshold) {
            return toFlatRope(this, that, concatenationLength);
        }
        return new ConcatenationRope(this, that).keepBalance();
    }

    protected static IRope toFlatRope(IRope left, IRope right, int concatenationLength) {
        char[] concatenation = new char[concatenationLength];
        int concatenationIndex = 0;
        for(int i = 0; i < left.count(); ++i)
            concatenation[concatenationIndex++] = left.charAt(i);
        for(int i = 0; i < right.count(); ++i)
            concatenation[concatenationIndex++] = right.charAt(i);
        return new FlatRope(concatenation);
    }

    @Override
    public Iterator<Character> iterator() {
        return new DummyRopeIterator(this);
    }

    @Override
    public boolean equals(Object object) {
        if(object == null)
            return false;
        if(object instanceof RopeBase) {
            RopeBase rope = (RopeBase) object;
            if(rope.count() != this.count())
                return false;
            for(int i = 0; i < this.count(); ++i)
                if(this.charAtInternal(i) != rope.charAtInternal(i))
                    return false;
            return true;
        }
        return false;
    }
}

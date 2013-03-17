package usu.oop.examples.rope;

import java.util.*;

public class ConcatenationRope extends RopeBase {
    private final IRope left;
    private final IRope right;
    private final int count;
    private final int depth;
    private static final int MAX_ROPE_DEPTH = 98;

    public ConcatenationRope(IRope left, IRope right) {
        this.left = left;
        this.right = right;
        this.count = left.count() + right.count();
        this.depth = 1 + Math.max(this.left.depth(), this.right.depth());
    }

    @Override
    protected IRope substringInternal(int offset, int count) {
        int lastIndex = offset + count - 1;
        if(lastIndex < this.left.count())
            return this.left.substring(offset, count);
        if(offset >= this.left.count())
            return this.right.substring(this.left.count() - offset, count);
        int countFromLeft = this.left.count() - offset;
        int countFromRight = count - countFromLeft;
        return this.left.substring(offset, countFromLeft).concatenate(right.substring(0, countFromRight));
    }

    @Override
    public int count() {
        return this.count;
    }

    @Override
    protected char charAtInternal(int index) {
        if(index < this.left.count())
            return this.left.charAt(index);
        return this.right.charAt(index - this.left.count());
    }

    @Override
    public int depth() {
        return this.depth;
    }

    @Override
    public String toString() {
        return this.left.toString().concat(this.right.toString());
    }

    @Override
    public IRope concatenate(IRope that) {
        int concatenationSize = this.right.count() + that.count();
        if(concatenationSize < this.concatenationThreshold)
            return new ConcatenationRope(this.left, toFlatRope(this.right, that, concatenationSize)).keepBalance();
        return super.concatenate(that);
    }

    public IRope keepBalance() {
        if (isBalanced())
            return this;
        Queue<IRope> ropesQueue = new ArrayDeque<IRope>();
        ropesQueue.add(this);
        List<IRope> leafNodes = new ArrayList<IRope>();
        while (!ropesQueue.isEmpty()) {
            IRope rope = ropesQueue.remove();
            if(rope instanceof ConcatenationRope) {
                ConcatenationRope concatenateRope = (ConcatenationRope) rope;
                ropesQueue.add(concatenateRope.left);
                ropesQueue.add(concatenateRope.right);
            } else {
                leafNodes.add(rope);
            }
        }
        return merge(leafNodes, 0, leafNodes.size());
    }

    private boolean isBalanced() {
        return depth < MAX_ROPE_DEPTH;
    }

    private IRope merge(List<IRope> leafNodes, int begin, int end) {
        int difference = end - begin;
        if(difference == 1)
            return leafNodes.get(begin);
        if(difference == 2)
            return new ConcatenationRope(leafNodes.get(begin), leafNodes.get(begin + 1));
        int middle = begin + (difference / 2);
        return new ConcatenationRope(merge(leafNodes, begin, middle), merge(leafNodes, middle, end));
    }
}

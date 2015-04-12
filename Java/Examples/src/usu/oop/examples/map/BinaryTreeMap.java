package usu.oop.examples.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class BinaryTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class BinaryTreeEntry implements Entry<K, V> {
        private final K key;
        private V value;
        private BinaryTreeEntry left;
        private BinaryTreeEntry right;

        public BinaryTreeEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }
    }

    private int size;
    public BinaryTreeEntry root;

    private BinaryTreeEntry newNode(K key, V value) {
        ++size;
        return new BinaryTreeEntry(key, value);
    }

    private BinaryTreeEntry getEntry(BinaryTreeEntry entry, K key) {
        if (entry == null)
            return null;
        K entryKey = entry.getKey();
        int compareResult = key.compareTo(entryKey);
        if (compareResult < 0)
            return getEntry(entry.left, key);
        if (compareResult > 0)
            return getEntry(entry.right, key);
        return entry;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object o) {
        K key = (K) o;
        return getEntry(root, key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V get(Object o) {
        K key = (K) o;
        BinaryTreeEntry entry = getEntry(root, key);
        return entry == null ? null : entry.getValue();
    }

    @Override
    public V put(K key, V value) {
        if(root == null) {
            root = newNode(key, value);
            return null;
        }
        return putInternal(root, key, value);
    }

    private V putInternal(BinaryTreeEntry node, K key, V value) {
        int compareResult = key.compareTo(node.getKey());
        if(compareResult == 0)
            return node.setValue(value);
        if (compareResult < 0) {
            if (node.left != null)
                return putInternal(node.left, key, value);
            node.left = newNode(key, value);
        } else {
            if (node.right != null)
                return putInternal(node.right, key, value);
            node.right = newNode(key, value);
        }
        return null;
    }

    @Override
    public V remove(Object o) {
        K key = (K) o;
        BinaryTreeEntry entry = getEntry(root, key);
        if(entry == null)
            return null;
        V oldValue = entry.getValue();
        root = removeInternal(root, key);
        return oldValue;
    }

    private BinaryTreeEntry removeInternal(BinaryTreeEntry node, K key) {
        if(node == null)
            return null;
        int compareResult = key.compareTo(node.getKey());
        if(compareResult == 0) {
            --size;
            if(node.left == null)
                return node.right;
            if(node.right == null)
                return node.left;
            BinaryTreeEntry leftMost = getLeftMost(node.right);
            leftMost.right = deleteLeftMost(node.right);
            leftMost.left = node.left;
            return leftMost;
        }
        else if(compareResult < 0)
            node.left = removeInternal(node.left, key);
        else
            node.right = removeInternal(node.right, key);
        return node;
    }


    private BinaryTreeEntry deleteLeftMost(BinaryTreeEntry node) {
        if(node.left == null)
            return node.right;
        node.left = deleteLeftMost(node.left);
        return node;
    }

    private BinaryTreeEntry getLeftMost(BinaryTreeEntry node) {
        if(node.left == null)
            return node;
        return getLeftMost(node.left);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        toStringInternal(buffer, root, 0);
        return buffer.toString();
    }

    private void toStringInternal(StringBuilder buffer, BinaryTreeEntry node, int level) {
        if (node == null)
            return;
        toStringInternal(buffer, node.left, level + 1);
        for (int i = 0; i < level; ++i)
            buffer.append(" ");
        buffer.append(node.key + " " + node.value).append("\r\n");
        toStringInternal(buffer, node.right, level + 1);
    }
}

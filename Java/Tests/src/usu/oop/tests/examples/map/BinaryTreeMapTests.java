package usu.oop.tests.examples.map;

import org.junit.Assert;
import org.junit.Test;
import usu.oop.examples.map.BinaryTreeMap;

public class BinaryTreeMapTests {

    private <K extends Comparable<K>> void assertAdd(BinaryTreeMap<K, K> map, K keyAndValue) {
        Assert.assertFalse(map.containsKey(keyAndValue));
        Assert.assertNull(map.put(keyAndValue, keyAndValue));
        Assert.assertTrue(map.containsKey(keyAndValue));
        Assert.assertEquals(keyAndValue, map.get(keyAndValue));
    }


    private <K extends Comparable<K>> void assertRemove(BinaryTreeMap<K, K> map, K keyAndValue) {
        Assert.assertTrue(map.containsKey(keyAndValue));
        Assert.assertEquals(keyAndValue, map.get(keyAndValue));
        Assert.assertEquals(keyAndValue, map.remove(keyAndValue));
        Assert.assertFalse(map.containsKey(keyAndValue));
    }


    @Test
    public void godlikeTest() {
        int [] perfectTreeSequence = new int [] { 0, -2, 2, -3, -1, 1, 3};
        BinaryTreeMap<Integer, Integer> binaryTreeMap = new BinaryTreeMap<Integer, Integer>();
        Assert.assertEquals(0, binaryTreeMap.size());
        for (int keyAndValue : perfectTreeSequence)
            assertAdd(binaryTreeMap, keyAndValue);
        Assert.assertEquals(perfectTreeSequence.length, binaryTreeMap.size());
        for (int keyAndValue : perfectTreeSequence)
            assertRemove(binaryTreeMap, keyAndValue);
        Assert.assertEquals(0, binaryTreeMap.size());
    }
}

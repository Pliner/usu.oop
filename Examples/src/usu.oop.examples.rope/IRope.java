package usu.oop.examples.rope;

public interface IRope extends Iterable<Character>{
    IRope substring(int offset, int count);
    int count();
    char charAt(int index);
    IRope concatenate(IRope rope);
    int depth();
}

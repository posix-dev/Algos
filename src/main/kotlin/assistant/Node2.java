package assistant;

public class Node2<V> {
    public V value;
    public Node2<V> next;
    public Node2<V> prev;

    public Node2(V value, Node2<V> next, Node2<V> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}

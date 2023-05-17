package DataStructure;

public class CNode<T> {
    private T data;
    private int next;

    public CNode(T data, int index) {
        this.data = data;
        this.next = index;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return
                "{data=" + data +
                ", next=" + next +
                '}';
    }
}

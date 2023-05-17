package DataStructure;

public class CursorArray<T> {
    private final CNode<T>[] cursorArray;

    // creates a node array (empty array of pointers), cursor array (free list)
    public CursorArray(int size) {
        cursorArray = new CNode[size];
        initialize();
    }

    public static boolean isBalancedDelimiter(String input) {
        Character[] chr = new Character[input.length()];
        for (int i = 0; i < chr.length; i++) {
            chr[i] = input.charAt(i);
        }
        if (chr.length < 1)
            return false;
        CursorArray<Character> delemiters = new CursorArray<>(512);
        int d = delemiters.createList();
        for (int i = 0; i < chr.length; i++) {
            switch (chr[i]) {
                case '(':
                    delemiters.push('(', d);
                    break;
                case ')':
                    if ((delemiters.isEmpty(d)) || delemiters.pop(d) != '(')
                        return false;

                    break;
            }
        }
        if (delemiters.isEmpty(d))
            return true;
        return false;
    }

    // sets the array items to null
    // initialization can be void.
    public void initialize() {
        for (int i = 0; i < cursorArray.length - 1; i++) {
            cursorArray[i] = new CNode<>(null, i + 1);
        }
        cursorArray[cursorArray.length - 1] = new CNode<>(null, 0);
    }

    // reserves a place in the freelist for an item and returns the index of the node
    private int malloc() {
        int p = cursorArray[0].getNext();
        cursorArray[0].setNext(cursorArray[p].getNext()); // note: it doesn't change the next of the reserved node.
        return p;
    }

    // returns the reserved node to the cursor array
    private void free(int p) {
        cursorArray[p] = new CNode(null, cursorArray[0].getNext());
        cursorArray[0].setNext(p);
    }

    // checks if a list that was created is null or not
    public boolean isNull(int l) {
        return cursorArray[l] == null;
    }

    // checks if the list that was created is empty or not
    public boolean isEmpty(int l) {
        return cursorArray[l].getNext() == 0;
    }

    // checks if the item is the last item in the cursor array
    public boolean isLast(int p) {
        return cursorArray[p].getNext() == 0;
    }

    // creates a list
    public int createList() {
        int l = malloc();
        if (l == 0)
            return -1;
        else
            cursorArray[l] = new CNode("-", 0); // dummy head
        return l;
    }

    public T peek(int l) {
        if (!isNull(l) && !isEmpty(l))
            return cursorArray[cursorArray[l].getNext()].getData();
        return null;
    }

    public T pop(int l) {
        if (!isNull(l) && !isEmpty(l)) {
            T tempData = peek(l);
            int delete = cursorArray[l].getNext();
            cursorArray[l].setNext(cursorArray[delete].getNext());
            free(delete);
            return tempData;
        }
        return null;
    }

    public int push(T data, int l) {
        int p = malloc();
        if (p != 0) {
            cursorArray[p] = new CNode(data, cursorArray[l].getNext());
            cursorArray[l].setNext(p);
        }
        return -1;
    }

    public void traverseList(int l) {
        System.out.print((l > 0) ? "list_" + l + " --> " : "freelist");
        while (!isNull(l) && !isEmpty(l)) {
            l = cursorArray[l].getNext();
            System.out.print(cursorArray[l] + "-->");
        }
        System.out.println("null");
    }

    public int find(T data, int l) {
        while (!isNull(l) && !isEmpty(l)) {
            if (data == cursorArray[l].getData())
                return l;
            l = cursorArray[l].getNext();
        }
        return -1;
    }

    public int findPrevious(T data, int l) {
        while (!isNull(l) && !isEmpty(l)) {
            l = cursorArray[l].getNext();
            if (data == cursorArray[cursorArray[l].getNext()].getData())
                return l;
        }
        return -1;
    }

    public CNode delete(T data, int l) {
        int p = findPrevious(data, l);
        if (p != -1) {
            int d = cursorArray[p].getNext();
            cursorArray[p].setNext(cursorArray[d].getNext());
            CNode temp = cursorArray[d];
            cursorArray[p].setNext(temp.getNext());
            free(d);
            return temp;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("i\tData\tNext\n");
        for (int i = 0; i < cursorArray.length; i++) {
            sb.append(i).append("\t").append(cursorArray[i]).append("\n");
        }
        return sb.toString();
    }

}
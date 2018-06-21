package nested;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccr at 2018/3/28.
 */
public class LinkedListTest {
    static final List<Long> list;
    static {
        list = new ArrayList<>();
    }

    public static void main(String[] args) {

    }
}

class Node<E> {
    E item;
    Node<E> prev;
    Node<E> next;
}

class LinkedList<E>{
    Node<E> first;
    Node<E> last;

    int size = 0;

    public E removeLast(){
        if(size == 0) {
            //throw empty exception
        }
        E result = this.last.item;
        size --;
        if(size == 0) {
            this.last = null;
        } else {
            this.last = this.last.prev;
        }
        return result;
    }

    public void add(E node){

    }

}
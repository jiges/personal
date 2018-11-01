package leetcode.problem155;

/**
 * https://leetcode-cn.com/problems/min-stack/
 */

class MinStack {

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {

    }

    public void pop() {

    }

    public int top() {

    }

    public int getMin() {

    }
}

public class MainClass {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();   //--> 返回 -3.
        minStack.pop();
        minStack.top();      //--> 返回 0.
        minStack.getMin();   //--> 返回 -2.
    }
}

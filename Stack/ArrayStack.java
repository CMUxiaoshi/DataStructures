public class ArrayStack<AnyType> implements StackInterface<AnyType> {
    private static final int DefaultCapacity = 10;
    private int top;
    private Object[] elements;

    public ArrayStack(int initialCapacity) {
        if (initialCapacity <= 0) {
            elements = new Object[DefaultCapacity];
        } else {
            elements = new Object[initialCapacity];
        }
        top = -1;
    }

    public ArrayStack() {
        this(DefaultCapacity);
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void push(AnyType item) {
        if (top == elements.length - 1) {
            throw new StackException("Stack is full");
        }
        top++;
        elements[top] = item;
    }

    @Override
    public AnyType pop() {
        if (isEmpty()) {
            throw new StackException("Empty");
        }
        @SuppressWarnings("unchecked")
        AnyType result = (AnyType) elements[top];
        elements[top] = null;
        top--;
        return result;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public AnyType peek() {
        if (isEmpty()) {
            throw new StackException("Empty");
        }
        return (AnyType) elements[top];
    }
}
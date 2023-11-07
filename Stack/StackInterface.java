public interface StackInterface<AnyType> {
    void push(AnyType item);
    AnyType pop();
    AnyType peek();
    boolean isEmpty();
}

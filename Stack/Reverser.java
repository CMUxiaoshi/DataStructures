public class Reverser {
    private String input;

    public Reverser(String str) {
        input = str;
    }

    public String doReverse() {
        ArrayStack<Character> theStack = new ArrayStack<Character> (input.length());
        for (int i = 0; i < input.length(); i++) {
            theStack.push(input.charAt(i));
        }
        String output = "";
        while (!theStack.isEmpty()) {
            output = output + theStack.pop();
        }
        return output;
    }
}

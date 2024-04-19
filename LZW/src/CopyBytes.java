// copy a binary or text file
import java.io.*;
public class CopyBytes {
    public static void main(String[] args) throws IOException {
        DataInputStream in =
                new DataInputStream(
                        new BufferedInputStream(
                                new FileInputStream("01_Overview.mp4")));

        char byteIn;
        try {
            while(true) {
                byteIn = (char) in.readByte();
                System.out.println(byteIn);
            }
        }
        catch(EOFException e) {
            in.close();

        }
    }
}

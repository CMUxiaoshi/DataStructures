import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Driver class for LZW Compression.
 * This LZW Compression can be directly use on both ASCII files and binary files.
 * For words.html, the size decreases from 2437kb to 1046kb.
 * For CrimeLatLonXY1990.csv, the size decreases from 2548kb to 1254kb.
 * For 01_Overview.mpt, however, the size increases from 24423 kb to 32978 kb.
 * @author Xiao Shi
 * @AndrewID xiaoshi
 * */
public class LZWCompression {
    public static void main(String[] args) throws IOException {
        if (args.length < 4 || !(args[0].equals("-c") || args[0].equals("-d")) || !args[1].equals("-v")) {
            return;
        }

        String inputFileName = args[2];
        String outputFileName = args[3];

        if (args[0].equals("-c")) {
            Encoder c = new Encoder(inputFileName, outputFileName);
            c.compress();
            System.out.println("bytes read = "+ c.getInCount() + ", bytes written = " + c.getOutCount());
        } else if (args[0].equals("-d")) {
            Decoder d = new Decoder(inputFileName, outputFileName);
            d.depress();
            System.out.println("bytes read = "+ d.getInCount() + ", bytes written = " + d.getOutCount());
        }
    }
}



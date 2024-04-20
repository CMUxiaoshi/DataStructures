import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Driver class for LZW Compression
 * @author Xiao Shi
 * @AndrewID xiaoshi
 * */
public class LZWCompression {
    public static void main(String[] args) throws IOException {
        String[] inputFile = {"files/shortwords.txt", "files/words.html",
                "files/CrimeLatLonXY.csv", "files/01_Overview.mp4"};
        String[] compressedFile = {"files/shortwords-compressed.txt", "files/words-compressed.html",
                "files/CrimeLatLonXY-compressed.csv", "files/01_Overview-compressed.mp4"};
        String[] decompressedFile = {"files/shortwordss-decompressed.txt", "files/wordss-decompressed.html",
                "files/CrimeLatLonXY-decompressed.csv", "files/01_Overviews-decompressed.mp4"};
        for (int i = 0; i < inputFile.length; i++) {
            Encoder ec = new Encoder(inputFile[i], compressedFile[i]);
            ec.compress();
            Decoder dc = new Decoder(compressedFile[i], decompressedFile[i]);
            dc.depress();
        }
    }
}

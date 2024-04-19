/**
 * LZW compression class. Input a mp4/txt file, return a 12-bit based LZW compression file.
 * @author Xiao Shi
 * @AndrewID xiaoshi
 * */
import java.io.*;

public class LZW {
    @SuppressWarnings("unchecked")
    private HashSet encoder;
    private HashSet decoder;
    private static final int binarySize = 256;

    private TmpArray tmpArray;

    public LZW() {
        encoder = new HashSet();
        decoder = new HashSet();
        tmpArray = new TmpArray();
    }


    private void read(String inputFile, String outputFile) throws IOException{
        DataInputStream in =
                new DataInputStream(
                        new BufferedInputStream(
                                new FileInputStream(inputFile)));
        DataOutputStream out =
                new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(outputFile)));
        dictInit();
        byte byteIn;
        String s = "";
        int index = binarySize;
        try {
            while(true) {
                byteIn = in.readByte();
                char c = (char)byteIn;
                c = (char)(c & 0xFF); // Mask the first 8 bits of data
                int t = c;
                String result = s + (char)t;
                if (encoder.contain(result)) {
                    s = result;
                } else {
//                    // output(s, out);
//                    encoder.insert(result, index);
//                    index = index + 1;
//                    s = "" + (char)t;
//                    if (encoder.getCurSize() > 4096) {
//                        dictInit();
                    }
//                }
                //out.writeByte(byteIn);
            }
        }
        catch(EOFException e) {
            in.close();
            out.close();
            // lastOutput(s,out);
        }
    }



    private void output(String s, DataOutputStream out) throws IOException {

        int result = (Integer) encoder.valueOf(s);
        tmpArray.insert(result);
        if (tmpArray.isFull()) {
            int byte1 = ((tmpArray.getElement(0) & 0xFFF) >> 4) & 0xFF;
            int byte2 = (((tmpArray.getElement(0) & 0xFFF) << 4) | ((tmpArray.getElement(1) & 0xFFF) >> 8)) & 0xFF;
            int byte3 = (tmpArray.getElement(1) & 0xFFF) & 0xFF;

            // write into output file
            out.writeByte(byte1);
            out.writeByte(byte2);
            out.writeByte(byte3);

            tmpArray = new TmpArray();
        }
    }

    private void lastOutput(String s, DataOutputStream out) throws IOException {
        if (tmpArray.getSize() == 1) {
            output(s, out);
        }
        int result = (Integer) encoder.valueOf(s);
        // padding
        byte highByte = (byte) (result >> 8);
        byte lowByte = (byte) (result);

        out.writeByte(highByte);
        out.writeByte(lowByte);
    }

    /**
     * Initialize encoder and decoder.
     * */
    private void dictInit() {
        for (int i = 0; i < binarySize; i++) {
            String tmp = "" + (char) i;
            encoder.insert(tmp, i);
        }
    }

    public static void main(String[] args) throws IOException {
        LZW nmsl = new LZW();
        // nmsl.encoder.print();
        nmsl.read("01_Overview.mp4", "test.txt");

    }
}

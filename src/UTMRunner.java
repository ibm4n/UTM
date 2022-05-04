import java.util.ArrayList;
import java.util.Scanner;

public class UTMRunner {


    public static void main(String[] args) {

        UTM utm = new UTM();
        utm.generateTM("0100010010001001101010001000100110010001000000000000000100010011001010001000100110001000100001000100110001010001010011000010001000000000000000010001011000010100000100010011000001000100000010001001100000101000001010011000000100010000000101011000000101000000101001100000001000100000000100010110000000101000000010101100000000100010000000000101011000000001010000000001010110000000001000100001010011000000000101000000000101011000000000010001000000000001000101100000000001010000000000101011000000000001000100000000000001000100110000000000010100000000000010101100000000000010001010001001100000000000010100000000000010101100000000000001000100000000000001000100110000000000000101000000000000001000100110000000000000010100000000000000100010011000000000000000101000000000000000100010011000000000000000010001000000000000000001000101100000000000000001010000000000000000100010110000000000000000010100000000000000000100010111000_00", false);
        try {
            utm.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}

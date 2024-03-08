import java.util.Scanner;

public class CRC_CCITT {

    // CRC-CCITT parameters
    private static final int POLYNOMIAL = 0x1021; // Polynomial for CRC-CCITT (X^16 + X^12 + X^5 + 1)
    private static final int CRC_INITIAL = 0xFFFF; // Initial CRC value
    private static final int CRC_FINAL = 0x0000; // Final CRC value

    // Method to calculate CRC-CCITT
    public static int calculateCRC(byte[] data) {
        int crc = CRC_INITIAL;

        for (byte b : data) {
            crc ^= (b << 8); // XOR the next byte with the upper byte of CRC
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x8000) != 0) {
                    crc = (crc << 1) ^ POLYNOMIAL; // If the MSB is set, perform a left shift and XOR with polynomial
                } else {
                    crc <<= 1; // Otherwise, just perform a left shift
                }
            }
        }

        return crc & 0xFFFF; // Return the CRC value
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter data to generate CRC: ");
        String dataString = scanner.nextLine();
        byte[] data = dataString.getBytes();

        int crc = calculateCRC(data);
        System.out.println("Generated CRC: " + Integer.toHexString(crc));

        // Error detection
        if (crc == CRC_FINAL) {
            System.out.println("No errors detected.");
        } else {
            System.out.println("Errors detected.");
        }

        scanner.close();
    }
}

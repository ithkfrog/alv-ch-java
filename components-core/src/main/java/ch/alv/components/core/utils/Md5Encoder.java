package ch.alv.components.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encodes a String with the MD5 algorithm
 *
 * @since 1.0.0
 */
public final class Md5Encoder {

    /*
     * Don't create an instance of this class.
     */
    private Md5Encoder() {
    }

    /**
     * Convert a String into its md5 representation
     *
     * @param source the String to convert
     * @return the String as Md5 hash
     */
    public static String convertToMd5(String source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }

        try {
            return convertToHexString(convertToMD5Bytes(source));
        } catch (Exception e) {
            throw new IllegalStateException("MD5 convertion failure", e);
        }
    }

    /**
     * Do the encoding with {@link java.security.MessageDigest}
     *
     * @param source the String to convert
     * @return md5 representation of the source
     * @throws java.security.NoSuchAlgorithmException
     *
     * @throws java.io.UnsupportedEncodingException
     *
     */
    private static byte[] convertToMD5Bytes(String source)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest algorithm = MessageDigest.getInstance("MD5");
        byte[] defaultBytes = source.getBytes("UTF-8");
        algorithm.update(defaultBytes);
        return algorithm.digest();
    }

    /**
     * Convert the byte array to the final String
     *
     * @param bytes the bytes to convert
     * @return the resulting HEX-String
     */
    private static String convertToHexString(byte[] bytes) {
        // TODO take solution from jobroom...
        if (bytes == null)
            throw new IllegalArgumentException();

        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            int i = (b & 0xff); // copy the byte bit pattern into int value
            hex.append(String.format("%02x", i));
        }
        return hex.toString();
    }


}

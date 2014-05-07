package ch.alv.components.core.utils;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;

/**
 * To enforce the usage of the spring StringUtils those are wrapped in this class. We name it explicitly StringHelper to
 * create a distinction to other StringUtils classes.
 *
 * @since 1.0.0
 */
public class StringHelper extends StringUtils {

    private static final String ENCODING_UTF8 = "UTF-8";
    private static final String ALGORITHM_MD5 = "MD5";

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    /**
     * Convert a String into its md5 representation
     *
     * @param source the String to convert
     * @return the String as Md5 hash
     */
    public static String encodeToMd5(String source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        return convertToHexString(convertToHashedBytes(source, ENCODING_UTF8, ALGORITHM_MD5));
    }

    /**
     * Convert a String into its md5 representation
     *
     * @param source the String to convert
     * @return the String as Md5 hash
     */
    public static String encodeToMd5(String source, String charEncoding) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        return convertToHexString(convertToHashedBytes(source, charEncoding, ALGORITHM_MD5));
    }

    /**
     * Convert a String into its md5 representation
     *
     * @param source the String to convert
     * @return the String as Md5 hash
     */
    public static String encodeToSecurityHash(String source, String algorithm) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        return convertToHexString(convertToHashedBytes(source, ENCODING_UTF8, algorithm));
    }

    /**
     * Convert a String into its md5 representation
     *
     * @param source the String to convert
     * @return the String as Md5 hash
     */
    public static String encodeToSecurityHash(String source, String encoding, String algorithm) {
        if (source == null) {
            throw new IllegalArgumentException("Source to encode must not be null");
        }
        return convertToHexString(convertToHashedBytes(source, encoding, algorithm));
    }

    /**
     * Do the encoding with {@link java.security.MessageDigest}
     *
     * @param source the String to convert
     * @return md5 representation of the source
     */
    private static byte[] convertToHashedBytes(String source, String encoding, String algorithm) {
        try {
            String defaultedEncoding = ENCODING_UTF8;
            if (StringHelper.isNotEmpty(encoding)) {
                defaultedEncoding = encoding;
            }
            byte[] defaultBytes = source.getBytes(defaultedEncoding);

            String defaultedAlgorithm = ALGORITHM_MD5;
            if (isNotEmpty(algorithm)) {
                defaultedAlgorithm = algorithm;
            }
            MessageDigest digest = MessageDigest.getInstance(defaultedAlgorithm);
            digest.update(defaultBytes);
            return digest.digest();
        } catch (Exception e) {
            throw new StringHelperException("Could not convert String '" + source + "' to a '" + encoding + "' encoded '" + algorithm + "'-hash.", e);
        }

    }

    /**
     * Convert the byte array to the final String
     *
     * @param bytes the bytes to convert
     * @return the resulting HEX-String
     */
    private static String convertToHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            int i = (b & 0xff); // copy the byte bit pattern into int value
            hex.append(String.format("%02x", i));
        }
        return hex.toString();
    }

    public static class StringHelperException extends RuntimeException {

        private static final long serialVersionUID = -7953924514343130249L;

        public StringHelperException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}

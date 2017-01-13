package ua.nure.poliakov.utils.encodind;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    static public String encodePassword(String password) {

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert messageDigest != null;
        messageDigest.update(password.getBytes());
        byte[] bytes = messageDigest.digest();
        StringBuilder st = new StringBuilder();
        for (byte b : bytes) {
            String result = Integer.toHexString(b & 0xff);
            if (result.length() == 1) {
                st.append('0');
            }
            st.append(result).append("");
        }
        return st.toString();
    }
}
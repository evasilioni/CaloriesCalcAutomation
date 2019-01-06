package utilities;

import java.util.Random;

public class RandomEmailGenerator {
    public static String getEmail() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder emailsb = new StringBuilder();
        Random rnd = new Random();
        while (emailsb.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            emailsb.append(chars.toLowerCase().charAt(index));
        }
        emailsb.append("@example.com");
        return emailsb.toString();
    }
}

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static final int NUMBER_OF_TEXTS = 10_000;
    public static final String LETTERS = "abc";
    public static final int LENGTH_OF_TEXT = 100_000;

    public static final BlockingQueue<String> texts1 = new ArrayBlockingQueue<>(100);
    public static final BlockingQueue<String> texts2 = new ArrayBlockingQueue<>(100);
    public static final BlockingQueue<String> texts3 = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) {

        for (int i = 0; i < NUMBER_OF_TEXTS; i++) {
            new Thread(() -> {
                try {
                    texts1.put(generateText(LETTERS, LENGTH_OF_TEXT));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    texts2.put(generateText(LETTERS, LENGTH_OF_TEXT));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    texts3.put(generateText(LETTERS, LENGTH_OF_TEXT));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        new Thread(() -> {
            int max = 0;
            for (String text : texts1) {
                int aFreq = (int) text.chars().filter(ch -> ch == 'a').count();
                if (max == 0 || max < aFreq) {
                    max = aFreq;
                }
            }
            System.out.println(max);
        }).start();

        new Thread(() -> {
            int max = 0;
            for (String text : texts1) {
                int bFreq = (int) text.chars().filter(ch -> ch == 'b').count();
                if (max == 0 || max < bFreq) {
                    max = bFreq;
                }
            }
            System.out.println(max);
        }).start();

        new Thread(() -> {
            int max = 0;
            for (String text : texts1) {
                int cFreq = (int) text.chars().filter(ch -> ch == 'c').count();
                if (max == 0 || max < cFreq) {
                    max = cFreq;
                }
            }
            System.out.println(max);
        }).start();
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}


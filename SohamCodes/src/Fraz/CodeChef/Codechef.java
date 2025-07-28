package Fraz.CodeChef;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Codechef {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int testCases = Integer.parseInt(reader.readLine());

        while (testCases-- > 0) {
            int strLength = Integer.parseInt(reader.readLine());
            String binaryStr = reader.readLine();
            int minLength = computeMinLength(binaryStr, strLength);
            result.append(minLength).append('\n');
        }
        System.out.print(result);

    }
    private static int computeMinLength(String binaryStr, int n) {
        int cnt = 0;
        for (int i = 1; i < n - 1; i++) {
            if (binaryStr.charAt(i - 1) != binaryStr.charAt(i) &&
                    binaryStr.charAt(i) != binaryStr.charAt(i + 1)) {
                cnt++;
                i += 1;
            }
        }
        return n - cnt;
    }
}

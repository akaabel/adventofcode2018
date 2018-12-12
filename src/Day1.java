import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {

    public class Resultat {
        private int frequency = 0;
        private boolean found = false;

        public Resultat(int frequency, boolean found) {
            this.frequency = frequency;
            this.found = found;
        }
    }

    public Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();

    public static void main(String[] args) throws IOException {
        System.out.println("Hello");
        int[] arrayOfNumbers = getArrayOfNumbers();
        Day1 day1 = new Day1();
        day1.solveIt(arrayOfNumbers);
    }

    private void solveIt(int[] arrayOfNumbers) {
        Resultat resultat = findFrequency(0, arrayOfNumbers);
        while (!resultat.found) {
            resultat = findFrequency(resultat.frequency, arrayOfNumbers);
            System.out.println("Looping...");
        }
        System.out.println("Solution: " + resultat.frequency);
    }

    public Resultat findFrequency(int sum, int[] arrayOfNumbers) {
        int currFreq = sum;
        for (int i = 0; i < arrayOfNumbers.length; i++) {
            int numOfCurrent = lookupFrequency(currFreq);
            if (numOfCurrent == 1) {
                return new Resultat(currFreq, true);
            }
            freqMap.put(currFreq, numOfCurrent + 1);
            int numFromArray = arrayOfNumbers[i];
            currFreq = currFreq + numFromArray;
        }
        return new Resultat(currFreq, false);
    }

    private int lookupFrequency(int frequency) {
        Integer val = freqMap.get(frequency);
        if (val == null) {
            return 0;
        } else {
            return val;
        }
    }

    public static int[] getArrayOfNumbers() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("inputday1.txt"));
        return strings.stream().mapToInt(num -> Integer.parseInt(num)).toArray();
    }


}

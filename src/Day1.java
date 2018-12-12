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

    public Map freqMap = new HashMap();

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
            currFreq = currFreq + arrayOfNumbers[i];
            int numOfCurrent = lookupFrequency(currFreq);
            //System.out.println("currFreq: " + currFreq + ". numOfCurrent: " + numOfCurrent);
            if (numOfCurrent == 2) {
                //System.out.println("Found solution: " + currFreq);
                return new Resultat(currFreq, true);
            } else {
                //System.out.println("Putting in map: " + currFreq + ". Count: " + (numOfCurrent + 1));
                freqMap.put(currFreq, numOfCurrent + 1);
            }
        }
        return new Resultat(currFreq, false);
    }

    private int lookupFrequency(int frequency) {
        Object obj = freqMap.get(frequency);
        if (obj == null) {
            return 0;
        } else return (int)obj;
    }

    public static int[] getArrayOfNumbers() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("inputday1.txt"));
        return strings.stream().mapToInt(num -> Integer.parseInt(num)).toArray();
    }


}

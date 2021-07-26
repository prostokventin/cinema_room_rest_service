import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        ArrayList<Integer> numbers = new ArrayList<>();

        Scanner s = new Scanner(System.in);

        for (String num : s.nextLine().split(" ")) {
            numbers.add(Integer.parseInt(num));
        }

        int number = s.nextInt();

        Collections.sort(numbers);

        ArrayList<Integer> distances = new ArrayList<>();

        for (Integer num : numbers) {
            distances.add(Math.abs(num - number));
        }

        for (int i = 0; i < distances.size(); i++) {
            if (Collections.min(distances) == distances.get(i)) {
                System.out.print(numbers.get(i) + " ");
            }
        }

    }
}
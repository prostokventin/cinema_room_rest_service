import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner s = new Scanner(System.in);

        List<String> result = new ArrayList<>();

        for (String str : s.nextLine().split(" ")) {
            result.add(str);
        }

        System.out.println(result);
    }
}
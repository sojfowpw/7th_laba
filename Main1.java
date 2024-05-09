import java.util.Scanner;
import java.util.ArrayList;

public class Main1 {
    static boolean createNumber(int S, ArrayList<Integer> numbers, int index, int sum) {
        if (index == numbers.size()) { // если исчерпаны все числа, проверяем равна ли сумма S
            return sum == S;
        }
        int number = numbers.get(index); // текущее число
        // пытаемся добавить к текущей сумме число или умножить на текущее число сумму
        if (createNumber(S, numbers, index + 1, sum + number)) { // сумма
            return true;
        }
        if (sum != 0 && createNumber(S, numbers, index + 1, sum * number)) { // произведение
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число S: ");
        int S = in.nextInt(); // S - создаваемое число
        System.out.print("Введите число N: ");
        int N = in.nextInt(); // N - количество вводимых чисел
        ArrayList<Integer> numbers = new ArrayList<>(); // вектор для вводимых чисел
        for (int i = 0; i < N; i++) {
            int number = in.nextInt();
            numbers.add(number);
        }
        if (createNumber(S, numbers, 0, 0)) { // проверка, можно ли создать заданное число S
            System.out.print("Можно");
        }
        else {
            System.out.print("Нельзя");
        }
    }
}

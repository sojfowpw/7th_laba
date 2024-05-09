import java.util.Scanner;
import java.util.ArrayList;

public class Main2 {
    static ArrayList<Integer> Eratosthenes(int size) { // генерация простых чисел методом Эратосфена
        ArrayList<Integer> prime_dig = new ArrayList<>(); // массив для простых чисел
        prime_dig.add(2);
        for (int i = 3; i <= size; i++) {
            boolean is_prime = true;
            for (int j = 0; j < prime_dig.size(); j++) {
                if (i % prime_dig.get(j) == 0) { // если число не делится на все предыдущие числа в векторе простых чисел, то оно простое
                    is_prime = false;
                    break;
                }
            }
            if (is_prime) { // если условие верно, то число простое => добавляем в массив
                prime_dig.add(i);
            }
        }
        return prime_dig;
    }

    static int count(ArrayList<Integer> factors, int n) { // функция для подсчёта хороших делителей
        int k = 0; // счётчик хороших делителей
        ArrayList<Integer> delit = new ArrayList<>(); // вектор делителей числа
        for (int i = 1; i <= (int) Math.sqrt(n); i++) { // ищем делители числа
            if (n % i == 0) {
                delit.add(i);
                if (i != n / i) {
                    delit.add(n / i);
                }
            }
        }
        for (int i = 0; i < delit.size(); i++) {
            int count = 0; // счётчик делимости
            for (int j = 0; j < factors.size(); j++) {
                if (delit.get(i) % factors.get(j) == 0) {
                    count++;
                }
            }
            if (count == factors.size()) { // если делитель делится на все простые множители, то он хороший
                k++;
            }
        }
        return k;
    }

    static ArrayList<Integer> primeFactorization(int n) { // делим число на простые множители
        ArrayList<Integer> factors = new ArrayList<>(); // вектор для простых множителей
        while (n % 2 == 0) { // проверяем делимость на 2, пока это возможно
            factors.add(2);
            n /= 2;
        }
        for (int i = 3; i * i <= n; i += 2) { // теперь n нечетное, поэтому мы можем пропустить один элемент
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 2) { // если n > 2, то оно должно быть простым и больше 2, добавляем его в качестве последнего множителя
            factors.add(n);
        }
        return factors;
    }

    static int combination(int primeFactors, ArrayList<Integer> prime_digits, ArrayList<Integer> prime_combination, int result, int index) { // перебор комбинаций
        if (index == primeFactors * prime_digits.size()) { // если перебрали все возможные комбинации, возвращаем текущий результат
            return result;
        }
        prime_combination.set(index % primeFactors, prime_digits.get(index / primeFactors)); // выбираем простой множитель из вектора
        int mult = 1;
        for (int i : prime_combination) { // ищем произведение комбинации
            mult *= i;
        }
        ArrayList<Integer> factors = primeFactorization(mult); // простые множители числа
        int Count = count(factors, mult); // считаем количество хороших делителей
        return Math.max(result, combination(primeFactors, prime_digits, prime_combination, Math.max(result, Count), index + 1)); // рекурсия
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество простых множителей: ");
        int primeFactors = in.nextInt();
        ArrayList<Integer> prime_digits = Eratosthenes(50); // заполняем вектор простыми числами до 50
        ArrayList<Integer> prime_combination = new ArrayList<>();
        for (int i = 0; i < primeFactors; i++) {
            prime_combination.add(2);
        }
        System.out.print("Максимальное количество хороших делителей: " + combination(primeFactors, prime_digits, prime_combination, 0, 0));
    }
}

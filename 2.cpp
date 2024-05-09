#include <vector>
#include <iostream>

using namespace std;

vector<int> Eratosthenes(int size) { // генерация простых чисел методом Эратосфена
    vector<int> prime_dig = { 2 }; // вектор для простых чисел
    for (int i = 3; i <= size; i++) {
        bool is_prime = true;
        for (int j = 0; j < prime_dig.size(); j++) {
            if (i % prime_dig[j] == 0) { // если число не делится на все предыдущие числа в векторе простых чисел, то оно простое
                is_prime = false;
                break;
            }
        }
        if (is_prime) { // если условие верно, то число простое => добавляем в вектор
            prime_dig.push_back(i);
        }
    }
    return prime_dig;
}

int count(const vector<int>& factors, int n) { // функция для подсчёта хороших делителей
    int k = 0; // счётчик хороших делителей
    vector<int> delit; // вектор делителей числа
    for (int i = 1; i <= sqrt(n); i++) { // ищем делители числа
        if (n % i == 0) {
            delit.push_back(i);
            if (i != n / i) {
                delit.push_back(n / i);
            }
        }
    }
    for (int i = 0; i < delit.size(); i++) {
        int count = 0; // счётчик делимости
        for (int j = 0; j < factors.size(); j++) {
            if (delit[i] % factors[j] == 0) {
                count++;
            }
        }
        if (count == factors.size()) { // если делитель делится на все простые множители, то он хороший
            k++;
        }
    }
    return k;
}

vector<int> primeFactorization(int n) { // делим число на простые множители
    vector<int> factors; // вектор для простых множителей
    while (n % 2 == 0) { // проверяем делимость на 2, пока это возможно
        factors.push_back(2);
        n /= 2;
    }
    for (int i = 3; i * i <= n; i += 2) { // теперь n нечетное, поэтому мы можем пропустить один элемент
        while (n % i == 0) {
            factors.push_back(i);
            n /= i;
        }
    }
    if (n > 2) { // если n > 2, то оно должно быть простым и больше 2, добавляем его в качестве последнего множителя
        factors.push_back(n);
    }
    return factors;
}

int combination(int primeFactors, const vector<int>& prime_digits, vector<int>& prime_combination, int result, int index) { // перебор комбинаций
    if (index == primeFactors * prime_digits.size()) { // если перебрали все возможные комбинации, возвращаем текущий результат
        return result;
    }
    prime_combination[index % primeFactors] = prime_digits[index / primeFactors]; // выбираем простой множитель из вектора 
    int mult = 1;
    for (int i : prime_combination) { // ищем произведение комбинации
        mult *= i;
    }
    vector<int> factors = primeFactorization(mult); // простые множители числа
    int Count = count(factors, mult); // считаем количество хороших делителей
    return max(result, combination(primeFactors, prime_digits, prime_combination, max(result, Count), index + 1)); // рекурсия
}

int main() {
    setlocale(LC_ALL, "Russian");
    cout << "Введите количество простых множителей: ";
    int primeFactors;
    cin >> primeFactors;
    vector<int> prime_digits = Eratosthenes(50); // заполняем вектор простыми числами до 50
    vector<int> prime_combination(primeFactors, 2);
    cout << "Максимальное количество хороших делителей: " << combination(primeFactors, prime_digits, prime_combination, 0, 0);
}

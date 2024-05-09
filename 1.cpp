#include <iostream>
#include <vector>

using namespace std;

bool createNumber(int S, const vector<int>& numbers, int index, int sum) {
	if (index == numbers.size()) { // если исчерпаны все числа, проверяем равна ли сумма S
		return sum == S;
	}
	int number = numbers[index]; // текущее число
	// пытаемся добавить к текущей сумме число или умножить на текущее число сумму
	if (createNumber(S, numbers, index + 1, sum + number)) { // сумма
		return true;
	}
	if (sum != 0 && createNumber(S, numbers, index + 1, sum * number)) { // произведение + проверка на то, равна ли сумма 0
		return true;
	}
	return false;
}

int main() {
	setlocale(LC_ALL, "Russian");
	cout << "Введите число S: ";
	int S, N, number; 
	cin >> S; // S - создаваемое число
	cout << "Введите число N: ";
	cin >> N; // N - количество вводимых чисел
	vector<int> numbers; // вектор для вводимых чисел
	cout << "Введите двузначные числа: ";
	for (int i = 0; i < N; i++) {
		cin >> number;
		numbers.push_back(number);
	}
	if (createNumber(S, numbers, 0, 0)) { // проверка, можно ли создать заданное число S
		cout << "Можно";
	}
	else {
		cout << "Нельзя";
	}
	return 0;
}

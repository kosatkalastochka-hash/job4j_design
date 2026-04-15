package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    public static void main(String[] args) {

        System.out.println("Игра FizzBuzz.");

        try (var input = new Scanner(System.in)) {
            int currentNumber = 1;
            while (currentNumber < 100) {
                var computerAnswer = getFizzBuzzValue(currentNumber++);
                System.out.println(computerAnswer);

                var userAnswer = input.nextLine();
                var correctAnswer = getFizzBuzzValue(currentNumber++);
                if (!userAnswer.equals(correctAnswer)) {
                    System.out.printf("Ошибка.Ожидалось %s,  полученно %s. Начинай снова.%n", correctAnswer, userAnswer);
                    currentNumber = 1;
                }
            }
        }
    }

    private static String getFizzBuzzValue(int number) {
        if (number % 15 == 0) {
            return "FizzBuzz";
        }
        if (number % 5 == 0) {
            return "Buzz";
        }
        if (number % 3 == 0) {
            return "Fizz";
        }
        return String.valueOf(number);
    }
}

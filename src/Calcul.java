import java.util.Scanner;

public class Calcul {
    private static final String[] ROMAN_SYMBOLS = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
    private static final int[] ROMAN_VALUES = {1, 4, 5, 9, 10, 40, 50, 90, 100};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();

        String[] parts = input.split(" ");
        if (parts.length != 3) {
            System.out.println("Ошибка: неверный формат выражения.");
            return;
        }

        boolean isNum1Roman = isRoman(parts[0]);
        boolean isNum2Roman = isRoman(parts[2]);

        if (isNum1Roman && !isNum2Roman || !isNum1Roman && isNum2Roman) {
            System.out.println("Ошибка: операции между различными системами счисления недопустимы.");
            return;
        }

        int num1 = parseNumber(parts[0]);
        char operator = parts[1].charAt(0);
        int num2 = parseNumber(parts[2]);

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            System.out.println("Ошибка: числа должны быть в диапазоне от 1 до 10.");
            return;
        }

        int result;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Ошибка: деление на ноль.");
                    return;
                }
                break;
            default:
                System.out.println("Ошибка: неверная операция.");
                return;
        }

        if (isNum1Roman || isNum2Roman) {
            System.out.println("Результат: " + toRoman(result));
        } else {
            System.out.println("Результат: " + result);
        }
    }

    private static int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return romanToDecimal(input);
        }
    }

    private static int romanToDecimal(String roman) {
        int result = 0;
        int i = ROMAN_SYMBOLS.length - 1;

        while (i >= 0) {
            if (roman.startsWith(ROMAN_SYMBOLS[i])) {
                result += ROMAN_VALUES[i];
                roman = roman.substring(ROMAN_SYMBOLS[i].length());
            } else {
                i--;
            }
        }

        return result;
    }

    private static boolean isRoman(String input) {
        return input.matches("^[IVXLCDM]+$");
    }

    private static String toRoman(int num) {
        StringBuilder roman = new StringBuilder();
        int i = ROMAN_SYMBOLS.length - 1;

        while (num > 0) {
            if (num >= ROMAN_VALUES[i]) {
                roman.append(ROMAN_SYMBOLS[i]);
                num -= ROMAN_VALUES[i];
            } else {
                i--;
            }
        }

        return roman.toString();
    }
}




package by.bsuir.lab;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static int[][] createMagicSquare(int order) {
        int[][] matrix = new int[order][order];
        int i = order / 2;
        int j = order - 1;
        boolean isDone;
        for (int num = 1; num <= order * order; ) {
            isDone = false;
            if (i == -1 && j == order) {
                j = order - 2;
                i = 0;
            } else {
                if (j == order)
                    j = 0;
                if (i < 0)
                    i = order - 1;
            }
            if (matrix[i][j] != 0) {
                j -= 2;
                i++;
                isDone = true;
            } else
                matrix[i][j] = num++;
            if (!isDone) {
                j++;
                i--;
            }
        }
        return matrix;
    }

    public static void showMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.printf("%3d ", anInt);
            }
            System.out.println();
        }

    }


    public static String findFilePath() {
        String filePath;
        FileReader reader = null;
        Scanner input = new Scanner(System.in);
        boolean isIncorrect;
        do {
            System.out.print("Введите путь к файлу: ");
            filePath = input.nextLine();
            isIncorrect = false;
            try {
                reader = new FileReader(filePath);
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден.");
                isIncorrect = true;
            }
        } while (isIncorrect);
        try {
            reader.close();
            input.close();
        } catch (IOException e) {
            System.err.println("I/O error.");
        }
        return filePath;
    }

    public static void saveMatrix(int[][] matrix){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(findFilePath()));
            for (int[] ints : matrix) {
                for (int anInt : ints){
                    writer.write(anInt + " ");
                }
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Ошибка сохранения.");
        }
        System.out.println("Успешно сохранено.");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int order = 0;
        boolean isCorrect;
        do {
            System.out.println("Введите порядок матрицы:");
            isCorrect = true;
            try {
                order = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e){
                isCorrect = false;
            }
            if ((isCorrect) && (order % 2 == 0) || ((order < 3) || (order > 7))) {
                isCorrect = false;
            }
        } while (!isCorrect);
        int[][] matrix = createMagicSquare(order);
        showMatrix(matrix);
        String temp;
        do{
            System.out.println("Save result? Y/N");
            isCorrect = true;
            temp = input.nextLine();
            if ((!temp.toUpperCase(Locale.ROOT).equals("Y")) && (!temp.toUpperCase(Locale.ROOT).equals("N"))){
                isCorrect = false;
            }
        } while (!isCorrect);
        if (temp.toUpperCase(Locale.ROOT).equals("Y")){
            saveMatrix(matrix);
        }
        input.close();
    }
}

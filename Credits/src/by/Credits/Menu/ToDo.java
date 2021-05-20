package by.Credits.Menu;

import by.Credits.Banks.Bank;
import by.Credits.Banks.Credit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class ToDo {

    private static short Choice;            // выбор действия
    private static  ArrayList<Bank> Banks;  // копия банков со всеми кредитами
    private static ArrayList<Bank> MyTotalCredits;    // наши выбранные кредиты в банках

    public static void Start(Bank...banks){
        Banks = new ArrayList<Bank>(Arrays.stream(banks).toList());
        //Banks.forEach(i-> System.out.println(i));
        do{
            Menu();
            Choice = InputNumber();
            switch (Choice){
                case 1:{
                    TakeCredit();
                    break;
                }
            }
        }
        while (Choice!=0);              // так как 0 - Выход
    }

    private static void Menu(){

        System.out.println("Выберите действие:\n" +
                "1 - Взять кредит\n" +
                "2 - Найти кредит\n" +
                "3 - Совершить платеж\n" +
                "4 - Закрыть кредит\n" +
                "0 - Выход\n");
    }

    private static short InputNumber(){
        Scanner sc = new Scanner(System.in); // создаём объект класса Scanner
        while (!sc.hasNextInt()) {   // возвращает истинну если с потока ввода можно считать целое число
            System.out.println("Вы ввели не целое число, попробуйте ещё раз!");
            sc.next();  // считывает одно «слово». Слова разделяются пробелами или enter
        }
        return sc.nextShort();         // считывает целое число с потока ввода и сохраняем в переменную
    }
    private static short InputNumber(int last){  // перегрузка метода. Ввод до числа включительно
        short Total;
        do {
            System.out.println("Введите число до "+last+" включительно");
            Total = InputNumber();
        }while (Total>last || Total<0);
        return Total;
    }

    private static void TakeCredit(){
        int Number;       // для хранения выбранного номера кредита
        Banks.forEach(i-> System.out.println(i));
        System.out.println("\nВыберите номер кредита (значение Number):");
        Number = InputNumber(Credit.CountCredit);   // даеём ввести число сколько максимум есть кредитов во всех банках
        //System.out.println( "good");
        Banks.forEach(i->i.GetCreditByNumber(Number));

    }
}


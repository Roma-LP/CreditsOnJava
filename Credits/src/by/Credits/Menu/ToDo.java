package by.Credits.Menu;

import by.Credits.Banks.Bank;
import by.Credits.Banks.Credit;
import by.Credits.Banks.TypesCredit;
import by.Credits.Main;
import org.w3c.dom.ls.LSOutput;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class ToDo {

    private static short Choice;                // выбор действия
    private static ArrayList<Bank> Banks;       // копия банков со всеми кредитами
    private static ArrayList<Bank> MyTotalCredits = new ArrayList<Bank>();      // наши выбранные кредиты в банках, сразу её создаем

    public static void Start(Bank...banks){
        Banks = new ArrayList<Bank>(Arrays.stream(banks).toList());
        //Banks.forEach(i-> System.out.println(i));
        do{
            Menu();
            Choice = InputNumber(5);
            switch (Choice){
                case 1:{
                    TakeCredit();
                    break;
                }
                case 2:{
                    FindCredit();
                    break;
                }
                case 3:{
                    MakePayment(TypePayment.OnePayment);
                    break;
                }
                case 4:{
                    MakePayment(TypePayment.FullPricePayment);
                    break;
                }
                case 5:{
                    PrintMyTotalCredits();
                    break;
                }
            }
        }
        while (Choice!=0);              // так как 0 - Выход
    }

    private static void Menu(){

        System.out.println("\nВыберите действие:\n" +
                "1 - Взять кредит\n" +
                "2 - Найти кредит\n" +
                "3 - Совершить платеж\n" +
                "4 - Досрочное погашение кредита\n" +
                "5 - Мой список кредитов\n" +
                "0 - Выход\n");
    }

    private static void FindMenu(){

        System.out.println("\nВыберите действие:\n" +
                "1 - Показать кредиты НИЖЕ определенной цены в месяц\n" +
                "2 - Показать кредиты ВЫШЕ определенной цены в месяц\n" +
                "3 - Показать кредиты какого-нибудь банка\n" +
                "4 - Показать кредиты по машине\n" +
                "5 - Показать кредиты по квартире\n" +
                "6 - Показать кредиты по образованию\n" +
                "0 - Выход");
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
            Total = InputNumber();
            if(Total>last || Total<0) {
                System.out.println("Введите число до " + last + " включительно");
            }
        }while (Total>last || Total<0);
        return Total;
    }

    private static void TakeCredit(){
        int Number;       // для хранения выбранного номера кредита
        Banks.forEach(i-> System.out.println(i));
        System.out.println("\nВыберите номер кредита (значение Number):");
        Number = InputNumber(Credit.getCountCredit());   // даеём ввести число сколько максимум есть кредитов во всех банках
        System.out.println( "good");
        //System.out.println(GetCreditByNumber(Number));
        //System.out.println(GetNameBankbyCreditNumber(Number));
        AddBankWithCreditNumber(Number,GetNameBankbyCreditNumber(Number)); // добавляем еаш выбранный кредит в наш список
    }

    // делает посик кредита по всем банкам, пока не найдёт нужный кредит
    private static Credit GetCreditByNumber(int Number){
        Credit crd=null;
        for (int i = 0; i < Banks.size(); i++) {
            if(Banks.get(i).FindCreditByNumber(Number)==null){  // ищем нужный кредит по номеру
                continue;
            }
            else crd = Banks.get(i).FindCreditByNumber(Number);
        }
        return crd;
    }

    // делает посик имя банка по номеру кредита, пока не найдёт это имя банка
    private static String GetNameBankbyCreditNumber(int Number){
        String name=null;
        for (int i = 0; i < Banks.size(); i++) {
            if(Banks.get(i).FindCreditByNumber(Number)==null){
                continue;
            }
            else name = Banks.get(i).getName();

        }
        return name;
    }

    private static void AddBankWithCreditNumber(int number,String name){
        if(MyTotalCredits.size()==0){
           MyTotalCredits.add(new Bank(name,GetCreditByNumber(number)));
        }
        else {
            for (int i = 0; i < MyTotalCredits.size(); i++) {  // если уже есть такой банк, то добовляем в его список новый кредит
                if(MyTotalCredits.get(i).getName().equals(name)){  // сравниваем на имена банков
                    System.out.println("est takoi");
                    MyTotalCredits.get(i).AddCredits(GetCreditByNumber(number));
                    return;  // добавляем к нашему банку и выходим принудительно из метода
                }
            }
            System.out.println("no");
            MyTotalCredits.add(new Bank(name,GetCreditByNumber(number)));  // создаём новый банк и его кредит
        }
    }

    private static void PrintMyTotalCredits(){
        if(MyTotalCredits==null || MyTotalCredits.size()==0){
            System.out.println("У вас ещё нет кредитов");
        }
        else{
            MyTotalCredits.forEach(i-> System.out.println(i));
        }
    }

    private static void MakePayment(TypePayment TP){
        if (MyTotalCredits.size()==0){
            System.out.println("У вас ещё нет кредитов");
            return;
        }
        PrintMyTotalCredits();
        System.out.println("\nВыберите номер кредита (значение Number) где хотите совершить платеж:");
        short Number=InputNumber();
        outterLoop: while (true) {  // используем метку для выхода из бесконечного цикла
            for (int i = 0; i < MyTotalCredits.size(); i++) {
                if (MyTotalCredits.get(i).FindCreditByNumber(Number) == null) {
                    System.out.println("bad");
                    continue;
                }
                else{
                    System.out.println("good");
                    switch (TP){
                        case OnePayment:{
                            MyTotalCredits.get(i).FindCreditByNumber(Number).OnePayment();
                            break ;
                        }
                        case FullPricePayment:{
                            MyTotalCredits.get(i).FindCreditByNumber(Number).FullPricePayment();
                            break ;
                        }
                    }
                    break outterLoop;  // если есть такой кредит, то совершаем платёж и выходим из цикла по метке
                }
            }
            System.out.println("Нет такого кредита, попробуйте ещё раз!");
            Number=InputNumber();
        }
        CheckTimeStatus();
        CheckEmptyBanks();
    }

    // метод для проверки времени кредита, если он уже выплачен (Time равен 0)
    private static void CheckTimeStatus(){
        for (int i = 0; i < MyTotalCredits.size(); i++) {  // первый цикл идём по банкам
            for (int j = 0; j < MyTotalCredits.get(i).GetCountCredits(); j++) {  // второй цикл идём по кредитам
                if (MyTotalCredits.get(i).getCredits().get(j).getTime()==0) {  // ищем выплаченные кредиты по Time==0
                    MyTotalCredits.get(i).getCredits().remove(j);   // и удаляем из нашего списка
                    System.out.println("Поздравляем! Вы выплатили кредит! :)");
                }
            }
        }
    }

    // метод для поиска и удаления пустых банков, без кредитов
    private static void CheckEmptyBanks(){
        for (int i = 0; i < MyTotalCredits.size(); i++) {  //  идём по банкам
                if (MyTotalCredits.get(i).getCredits().isEmpty()) {  // ищем банки с пустыми кредитами
                    MyTotalCredits.remove(i);   // и удаляем из нашего списка
                    System.out.println("Поздравляем! Вы расплатились с банком! :)");
                }

        }
    }

    private static void FindCredit(){
        boolean flagtocontinue=false;  // флаг для проверки результата поиска
        FindMenu();
        short vibor = InputNumber(6);
        switch (vibor){

            case 1:{
                System.out.print("Введите цену:  ");
                short price = InputNumber();
                for (int i = 0; i < Banks.size(); i++) {
                    for (int j = 0; j < Banks.get(i).getCredits().size(); j++) {
                        if(Banks.get(i).getCredits().get(j).getPricePerMonth()<price){  // выводим все кредиты НИЖЕ нужной цены
                            System.out.print(Banks.get(i).getCredits().get(j));
                            flagtocontinue = true;
                        }
                    }
                }
                if(!flagtocontinue) {
                    System.out.println("Таких нет"); return; }  // если поиск ничего не дал, то выходим
                int Number;       // для хранения выбранного номера кредита
                System.out.println("\nВыберите номер кредита (значение Number):");
                Number = InputNumber(Credit.getCountCredit());   // даеём ввести число сколько максимум есть кредитов во всех банках
                AddBankWithCreditNumber(Number,GetNameBankbyCreditNumber(Number)); // добавляем еаш выбранный кредит в наш список
                break;
            }

            case 2:{
                System.out.print("Введите цену:  ");
                short price = InputNumber();
                for (int i = 0; i < Banks.size(); i++) {
                    for (int j = 0; j < Banks.get(i).getCredits().size(); j++) {
                        if(Banks.get(i).getCredits().get(j).getPricePerMonth()>price){  // // выводим все кредиты ВЫШЕ нужной цены
                            System.out.print(Banks.get(i).getCredits().get(j));
                            flagtocontinue = true;
                        }
                    }
                }
                if(!flagtocontinue) {
                    System.out.println("Таких нет"); return; }  // если поиск ничего не дал, то выходим
                int Number;       // для хранения выбранного номера кредита
                System.out.println("\nВыберите номер кредита (значение Number):");
                Number = InputNumber(Credit.getCountCredit());   // даеём ввести число сколько максимум есть кредитов во всех банках
                AddBankWithCreditNumber(Number,GetNameBankbyCreditNumber(Number)); // добавляем еаш выбранный кредит в наш список
                break;
            }

            case 3:{
                for (int i = 0; i < Banks.size(); i++) {
                    System.out.println(i +" - "+Banks.get(i).getName());
                }
                System.out.print("Введите номер банка:  ");
                short bank = InputNumber(Banks.size()-1);
                for (int i=0;i<Banks.get(bank).getCredits().size();i++){
                    System.out.print(Banks.get(bank).getCredits().get(i));
                }
                int Number;       // для хранения выбранного номера кредита
                System.out.println("\nВыберите номер кредита (значение Number):");
                Number = InputNumber(Credit.getCountCredit());   // даеём ввести число сколько максимум есть кредитов во всех банках
                AddBankWithCreditNumber(Number,GetNameBankbyCreditNumber(Number)); // добавляем еаш выбранный кредит в наш список
                break;

            }

            case 4:{
                for (int i = 0; i < Banks.size(); i++) {
                    for (int j = 0; j < Banks.get(i).getCredits().size(); j++) {
                        if(Banks.get(i).getCredits().get(j).getTypeCredet()== TypesCredit.CAR){  // // выводим все кредиты ВЫШЕ нужной цены
                            System.out.print(Banks.get(i).getCredits().get(j));
                            flagtocontinue = true;
                        }
                    }
                }
                if(!flagtocontinue) {
                    System.out.println("Таких нет"); return; }  // если поиск ничего не дал, то выходим
                int Number;       // для хранения выбранного номера кредита
                System.out.println("\nВыберите номер кредита (значение Number):");
                Number = InputNumber(Credit.getCountCredit());   // даеём ввести число сколько максимум есть кредитов во всех банках
                AddBankWithCreditNumber(Number,GetNameBankbyCreditNumber(Number)); // добавляем еаш выбранный кредит в наш список
                break;
            }

            case 5:{
                for (int i = 0; i < Banks.size(); i++) {
                    for (int j = 0; j < Banks.get(i).getCredits().size(); j++) {
                        if(Banks.get(i).getCredits().get(j).getTypeCredet()== TypesCredit.HOUSE){  // // выводим все кредиты ВЫШЕ нужной цены
                            System.out.print(Banks.get(i).getCredits().get(j));
                            flagtocontinue = true;
                        }
                    }
                }
                if(!flagtocontinue) {
                    System.out.println("Таких нет"); return; }  // если поиск ничего не дал, то выходим
                int Number;       // для хранения выбранного номера кредита
                System.out.println("\nВыберите номер кредита (значение Number):");
                Number = InputNumber(Credit.getCountCredit());   // даеём ввести число сколько максимум есть кредитов во всех банках
                AddBankWithCreditNumber(Number,GetNameBankbyCreditNumber(Number)); // добавляем еаш выбранный кредит в наш список
                break;
            }

            case 6:{
                for (int i = 0; i < Banks.size(); i++) {
                    for (int j = 0; j < Banks.get(i).getCredits().size(); j++) {
                        if(Banks.get(i).getCredits().get(j).getTypeCredet()== TypesCredit.EDUCATION){  // // выводим все кредиты ВЫШЕ нужной цены
                            System.out.print(Banks.get(i).getCredits().get(j));
                            flagtocontinue = true;
                        }
                    }
                }
                if(!flagtocontinue) {
                    System.out.println("Таких нет"); return; }  // если поиск ничего не дал, то выходим
                int Number;       // для хранения выбранного номера кредита
                System.out.println("\nВыберите номер кредита (значение Number):");
                Number = InputNumber(Credit.getCountCredit());   // даеём ввести число сколько максимум есть кредитов во всех банках
                AddBankWithCreditNumber(Number,GetNameBankbyCreditNumber(Number)); // добавляем еаш выбранный кредит в наш список
                break;
            }

            case 0:{
                System.out.println("Выход");
                return;
            }
        }
    }
}


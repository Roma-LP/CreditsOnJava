package by.Credits.Banks;

import java.util.ArrayList;
import java.util.Arrays;

public class Bank {

    private String Name;             // имя банка
    ArrayList<Credit> Credits;   // кредиты которые банк предлагает
    public Bank(String name, Credit...credits) {
        Name = name;
        this.Credits = new ArrayList<Credit>(Arrays.stream(credits).toList());
    }

    // метод для добавления кредит(ов)
    public void AddCredits(Credit...credits){
        for (int i = 0; i < credits.length; i++) {
            this.Credits.add(credits[i]);
        }
    }

    // метод для вывода данных
    public  void PrintData(){
        System.out.println(toString());
    }

    public int GetCountCredits(){
        return Credits.size();
    }

    public void GetCreditByNumber(int number){
        for (int i = 0; i < Credits.size(); i++) {
            if(Credits.get(i).getNumber()==number){
                System.out.println(Credits.get(i));
            }
        }
    }

    @Override
    public String toString() {
        return "\nBank{" +
                "Name='" + Name + '\'' +
                ",\tCredits=" + Credits +
                '}';
    }
}

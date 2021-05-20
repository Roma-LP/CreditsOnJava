package by.Credits;

import by.Credits.Banks.Bank;
import by.Credits.Banks.Credit;
import by.Credits.Banks.TypesCredit;
import by.Credits.Menu.ToDo;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        Credit pr1 = new Credit(TypesCredit.HOUSE,3,500);
        Credit pr2 = new Credit(TypesCredit.CAR,4,7860);
        Credit pr3 = new Credit(TypesCredit.OTHER,18,193);
        Credit pr4 = new Credit(TypesCredit.EDUCATION,10,193);
        Credit pr5 = new Credit(TypesCredit.MOBILEPHONE,15,56);
        Credit pr6 = new Credit(TypesCredit.HOUSE,22,157);
        Credit pr7 = new Credit(TypesCredit.CAR,17,317);
        //PrintData(pr1,pr2,pr3,pr4,pr5);
        //System.out.println();

        Bank BPS = new Bank("BPS",pr1,pr2,pr7);
        Bank USA = new Bank("USA",pr3,pr4);
        Bank BLR = new Bank("BLR",pr5,pr6);
       // BPS.PrintData();
        USA.AddCredits(new Credit(TypesCredit.MOBILEPHONE,1,1),
                       new Credit(TypesCredit.CAR,13,493) );
        //System.out.println();
        //BPS.PrintData();



        ToDo.Start(BPS,USA,BLR);


    }
    // статический обобщенный (Generics) метод с переменным числом аргументов для вывода (использую для проверки)
    public static <T> void PrintData(T...data){
        for(int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }


}

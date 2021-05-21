package by.Credits.Banks;

public class Credit {


    private static short CountCredit=0; // номер кредита абсолютный
    private TypesCredit TypeCredet;     // тип кредита (на что)


    private int Time;                   // на какое время
    private int PricePerMonth;          // платеж в месяц
    private short Number;               // номер конкретного кредита
    private int FullPrice;              // полная стоимость кредита

    public Credit(TypesCredit typecredet, int Time, int PricePerMonth) {
        this.TypeCredet = typecredet;
        this.Time = Time;
        this.PricePerMonth = PricePerMonth;
        this.Number = CountCredit++;
        FullPrice = PricePerMonth*Time;
    }

    public short getNumber() {
        return Number;
    }

    public int getTime() {
        return Time;
    }

    public static short getCountCredit() {
        return CountCredit;
    }

    // метод для совершения одного платежа
    public void OnePayment(){
        Time--;
    }

    public int getPricePerMonth() {
        return PricePerMonth;
    }

    public TypesCredit getTypeCredet() {
        return TypeCredet;
    }

    // метод для погашения кредита
    public void FullPricePayment(){
        Time=0;
    }

    @Override // переопределяем базовый метод "toString()" для красивого вывода
    public String toString() {
        return "\n\tCredit{" +
                "TypeCredet=" + TypeCredet +
                ", Time=" + Time +
                ", PricePerMonth=" + PricePerMonth +
                ", Number=" + Number +
                ", FullPrice=" + FullPrice +
                '}';
    }
}

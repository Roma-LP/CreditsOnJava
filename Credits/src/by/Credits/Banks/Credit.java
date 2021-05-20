package by.Credits.Banks;

public class Credit {

    public static short CountCredit=0; // номер кредита абсолютный
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

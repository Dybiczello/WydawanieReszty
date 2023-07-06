import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        //Scanner do przyjmowania wartości reszty
        Scanner resztaPodana = new Scanner(System.in);

        //Stworzenie kasy z odpowiednią liczbą drobnych
        Map<BigDecimal, Integer> kasaDrobnych = new LinkedHashMap<>();
        kasaDrobnych.put(new BigDecimal("5.0"),1);
        kasaDrobnych.put(new BigDecimal("2.0"),3);
        kasaDrobnych.put(new BigDecimal("1.0"),5);
        kasaDrobnych.put(new BigDecimal("0.5"),10);
        kasaDrobnych.put(new BigDecimal("0.2"),20);
        kasaDrobnych.put(new BigDecimal("0.1"),200);
        kasaDrobnych.put(new BigDecimal("0.05"),100);
        kasaDrobnych.put(new BigDecimal("0.02"),100);
        kasaDrobnych.put(new BigDecimal("0.01"),10000);

        while (true){
            System.out.println("Podaj resztę: ");
            String resztaString = resztaPodana.nextLine();

            BigDecimal reszta = new BigDecimal(resztaString);

            Map<BigDecimal, Integer> wydanaReszta = new LinkedHashMap<>();
            BigDecimal zero = new BigDecimal("0.0");

            A: for(Map.Entry<BigDecimal, Integer> drobne : kasaDrobnych.entrySet()){
                BigDecimal wartosc = drobne.getKey();
                int ilosc = drobne.getValue();
                B: while(ilosc != 0) {
                    if (reszta.compareTo(wartosc) < 0) {break B;}
                    else{
                        reszta = reszta.subtract(wartosc);
                        ilosc -= 1;
                        kasaDrobnych.put(wartosc,ilosc);
                        if(wydanaReszta.containsKey(wartosc)){
                            int iloscReszty = wydanaReszta.get(wartosc);
                            wydanaReszta.put(wartosc, iloscReszty+1);
                        }
                        else{
                            wydanaReszta.put(wartosc,1);
                        }
                        if(reszta.compareTo(zero) == 0){
                            break A;
                        }
                    }
                }
            }
            for(Map.Entry<BigDecimal, Integer> doWydania : wydanaReszta.entrySet()){
                System.out.println("Wydaj " + doWydania.getValue() + " monet " + doWydania.getKey() + " zł \n");
            }
        }
    }
}
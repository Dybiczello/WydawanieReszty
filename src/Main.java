import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        //Scanner do przyjmowania wartości reszty
        Scanner resztaPodana = new Scanner(System.in);

        //Stworzenie kasy z odpowiednią liczbą drobnych
        Map<Double, Integer> kasaDrobnych = new LinkedHashMap<>();
        kasaDrobnych.put(5.0,1);
        kasaDrobnych.put(2.0,3);
        kasaDrobnych.put(1.0,5);
        kasaDrobnych.put(0.5,10);
        kasaDrobnych.put(0.2,20);
        kasaDrobnych.put(0.1,200);
        kasaDrobnych.put(0.05,100);
        kasaDrobnych.put(0.02,100);
        kasaDrobnych.put(0.01,10000);

        while (true){
            System.out.println("Podaj resztę: ");
            double reszta = resztaPodana.nextDouble();

            Map<Double, Integer> wydanaReszta = new LinkedHashMap<>();

            A: for(Map.Entry<Double, Integer> drobne : kasaDrobnych.entrySet()){
                double wartosc = drobne.getKey();
                int ilosc = drobne.getValue();
                B: while(ilosc != 0) {
                    if (reszta < wartosc) {break B;}
                    else{
                        reszta = reszta - wartosc;
                        ilosc -= 1;
                        kasaDrobnych.put(wartosc,ilosc);
                        if(wydanaReszta.containsKey(wartosc)){
                            int iloscReszty = wydanaReszta.get(wartosc);
                            wydanaReszta.put(wartosc, iloscReszty+1);
                        }
                        else{
                            wydanaReszta.put(wartosc,1);
                        }
                        if(reszta == 0){
                            break A;
                        }
                    }
                }
            }
            for(Map.Entry<Double, Integer> doWydania : wydanaReszta.entrySet()){
                System.out.println("Wydaj " + doWydania.getValue() + " monet " + doWydania.getKey() + " zł \n");
            }
            break;
        }
    }
}
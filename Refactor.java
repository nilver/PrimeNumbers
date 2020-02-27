import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@FunctionalInterface
interface IStrategy {

    public boolean anyMathProperty(Integer number);
}

public class Refactor {

    public static void main(String[] args) {

        IStrategy strategyisPrime = (number -> {
            if(number <= 2)
                return number == 2;
            else
                return  (number % 2) != 0
                        &&
                        IntStream.rangeClosed(3, (int) Math.sqrt(number))
                                .filter(n -> n % 2 != 0)
                                .noneMatch(n -> (number % n == 0));

        });

        IStrategy strategyIsEven = (number ->{
            return (number%2) ==0;
        });

        Scanner scan= new Scanner(System.in);
        System. out.println("Introduzca un número : ");
        int max = scan.nextInt();
        scan.close();

        List<Integer> listPrimes = IntStream.range(1, Integer.MAX_VALUE)
                .filter(n ->  strategyisPrime.anyMathProperty(n))
                .limit(max)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> listEvens = IntStream.range(1, Integer.MAX_VALUE)
                .filter(n ->  strategyIsEven.anyMathProperty(n))
                .limit(max)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("Prime numbers ");
        new Printer().print(listPrimes, 200);
        System.out.println("Even number");
        new Printer().print(listEvens, 200);
    }
}

class Printer{

    public void print(List<Integer> list,int numberBypage){
        int total = list.size();
        int end = 0;
        int paginator = 1;

        if(numberBypage>total){
            numberBypage= total;
        }
        for (int start= 0; start< total; start+=numberBypage){
            end = end +numberBypage;
            List<Integer> subList= list.subList(start, end );
            printPage(subList, paginator);
            paginator ++;

        }
    }
    private void printPage(List<Integer> list,int page){
        int index=0;
        int countColumns=0;
        int countRows=0;

        final int MAX_COLUM=4;
        int totalRows = list.size()/MAX_COLUM;
        System.out.println("Numbers Page=="+ page);
        for (int i=0; i< list.size();i++){
            System.out.print(list.get(index)+" ");
            index= index + totalRows;
            countColumns ++;
            if(countColumns==MAX_COLUM){
                countRows++;
                index = countRows;
                System.out.println(" ");
                countColumns=0;
            }

        }
        System.out.println();
    }

}

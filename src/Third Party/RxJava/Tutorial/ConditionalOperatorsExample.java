
// https://www.tutorialspoint.com/rxjava/rxjava_conditional_operators.htm

import io.reactivex.Observable;

public class ConditionalOperatorsExample {
    public static void main(String[] args) {    
        final StringBuilder result = new StringBuilder();
        Observable.empty()
        .defaultIfEmpty("No Data")
        .subscribe(s -> result.append(s));
        System.out.println(result);
        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        final StringBuilder result1 = new StringBuilder();
        Observable.fromArray(letters)
        .firstElement()
        .defaultIfEmpty("No data")   
        .subscribe(s -> result1.append(s));
        System.out.println(result1);
     }
}
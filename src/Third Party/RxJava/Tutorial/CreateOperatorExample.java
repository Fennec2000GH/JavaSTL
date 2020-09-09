
// https://www.tutorialspoint.com/rxjava/rxjava_creating_operators.htm

import io.reactivex.Observable;

public class CreateOperatorExample {
    public static void main(String[] args) {
        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        final StringBuilder result = new StringBuilder();
        Observable<String> observable = Observable.fromArray(letters);
        observable.map(String::toUpperCase).subscribe(letter -> result.append(letter));
        System.out.println(result);
    }    
}

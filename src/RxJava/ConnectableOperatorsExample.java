
// https://www.tutorialspoint.com/rxjava/rxjava_connectable_operators.htm

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class ConnectableOperatorsExample {
   public static void main(String[] args) {
      String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
      final StringBuilder result = new StringBuilder();
      ConnectableObservable<String> connectable = Observable.fromArray(letters).publish();      
      connectable.subscribe(letter -> result.append(letter));
      System.out.println(result.length());
      connectable.connect();
      System.out.println(result.length());
      System.out.println(result);
   }
}

// https://www.tutorialspoint.com/rxjava/rxjava_replaysubject.htm

import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectExample {
    public static void main(String[] args) {
        final StringBuilder result1 = new StringBuilder();
        final StringBuilder result2 = new StringBuilder();         

        ReplaySubject<String> subject = ReplaySubject.create(); 
        subject.subscribe(value -> result1.append(value) ); 
        subject.onNext("a"); 
        subject.onNext("b"); 
        subject.onNext("c"); 
        subject.subscribe(value -> result2.append(value)); 
        subject.onNext("d"); 
        subject.onComplete();

        System.out.println(result1);
        System.out.println(result2);
    }
}
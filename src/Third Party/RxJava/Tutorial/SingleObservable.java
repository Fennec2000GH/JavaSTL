
// https://www.tutorialspoint.com/rxjava/rxjava_single_observable.htm

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SingleObservable {
    public static void main(String[] args) throws InterruptedException {

        Single<String> testSingle = Single.just("Hello World!");
        Disposable disposable = testSingle
        .delay(2, TimeUnit.SECONDS, Schedulers.io())
        .subscribeWith(new DisposableSingleObserver<String>(){
            
            @Override
            public void onError(Throwable e) { e.printStackTrace(); }

            @Override
            public void onSuccess(String value) { System.out.println(value); }

        });

        Thread.sleep(3000);
        disposable.dispose();
   }
}

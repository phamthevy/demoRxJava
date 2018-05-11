package com.example.vypt.demorxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class intervalActivity extends AppCompatActivity {
    private final CompositeDisposable disposables = new CompositeDisposable();
    Button start;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);
        this.setTitle("INTERVAL");
        start = (Button) findViewById(R.id.start);
        tv = (TextView) findViewById(R.id.tv);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // clearing it : do not emit after destroy
    }

    public void start() {
        disposables.add(getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));
    }

    private Observable<? extends Long> getObservable() {
        return Observable.interval(0, 2, TimeUnit.SECONDS);
    }

    private DisposableObserver<Long> getObserver() {
        return new DisposableObserver<Long>() {

            @Override
            public void onNext(Long value) {
                tv.append(" onNext : value : " + value);
                tv.append("\n");
                Log.d("interval", " onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                tv.append(" onError : " + e.getMessage());
                tv.append("\n");
                Log.d("interval", " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                tv.append(" onComplete");
                tv.append("\n");
                Log.d("interval", " onComplete");
            }
        };
    }
}

package com.example.vypt.demorxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class timerActivity extends AppCompatActivity {
    
    Button start;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        this.setTitle("TIMER");
        start = (Button) findViewById(R.id.start);
        tv = (TextView) findViewById(R.id.tv);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }
    
    public void start(){
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<? extends Long> getObservable() {
        return Observable.timer(2, TimeUnit.SECONDS);
    }
    
    private Observer<Long> getObserver() {
        return new Observer<Long>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("timer", " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Long value) {
                tv.append(" onNext : value : " + value);
                tv.append("\n");
                Log.d("timer", " onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                tv.append(" onError : " + e.getMessage());
                tv.append("\n");
                Log.d("timer", " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                tv.append(" onComplete");
                tv.append("\n");
                Log.d("timer", " onComplete");
            }
        };
    }
}

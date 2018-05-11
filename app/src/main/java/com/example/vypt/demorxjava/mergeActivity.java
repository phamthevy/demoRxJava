package com.example.vypt.demorxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class mergeActivity extends AppCompatActivity {
    Button start;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);
        this.setTitle("MERGE");
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
        final String[] aStrings = {"one", "two", "three", "four"};
        final String[] bStrings = {"1", "2", "3"};

        final io.reactivex.Observable<String> aObservable = io.reactivex.Observable.fromArray(aStrings);
        final io.reactivex.Observable<String> bObservable = io.reactivex.Observable.fromArray(bStrings);

        io.reactivex.Observable.merge(aObservable, bObservable)
                .subscribe(getObserver());
    }
    
    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("merge", " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                tv.append(" onNext : value : " + value);
                tv.append("\n");
                Log.d("merge", " onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                tv.append(" onError : " + e.getMessage());
                tv.append("\n");
                Log.d("merge", " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                tv.append(" onComplete");
                tv.append("\n");
                Log.d("merge", " onComplete");
            }
        };
    }
}

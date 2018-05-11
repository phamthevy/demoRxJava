package com.example.vypt.demorxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class distinceActivity extends AppCompatActivity {

    Button start;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distince);
        this.setTitle("DISTINCE");
        start = (Button) findViewById(R.id.start);
        tv = (TextView) findViewById(R.id.tv);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    public void start() {

        getObservable()
                .distinct()
                .subscribe(getObserver());
    }

    private Observable<Integer> getObservable() {
        return Observable.just(1, 2, 1, 1, 2, 3, 4, 6, 4);
    }


    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("distince", " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                tv.append(" onNext : value : " + value);
                tv.append("\n");
                Log.d("distince", " onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("distince", " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("distince", " onComplete");
            }
        };
    }
}

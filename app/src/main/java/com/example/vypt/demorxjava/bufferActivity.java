package com.example.vypt.demorxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

public class bufferActivity extends AppCompatActivity {
    Button start;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer);
        this.setTitle("BUFFER");
        start = (Button) findViewById(R.id.start);
        tv = (TextView) findViewById(R.id.tv);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    private void start() {

        Observable<List<String>> buffered = getObservable().buffer(4, 2);

        // 4 means,  it takes max of four from its start index and create list
        // 2 means, it jumps one step every time
        // so the it gives the following list
        // 1 - one, two, three, four
        // 2 - three four, five, six
        // 3 - five, six

        buffered.subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("one", "two", "three", "four", "five", "six");
    }

    private Observer<List<String>> getObserver() {
        return new Observer<List<String>>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("buffer", " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(List<String> stringList) {
                tv.append(" onNext size : " + stringList.size());
                tv.append("\n");
                Log.d("buffer", " onNext : size :" + stringList.size());
                for (String value : stringList) {
                    tv.append(" value : " + value);
                    tv.append("\n");
                    Log.d("buffer", " : value :" + value);
                }

            }

            @Override
            public void onError(Throwable e) {
                tv.append(" onError : " + e.getMessage());
                tv.append("\n");
                Log.d("buffer", " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                tv.append(" onComplete");
                tv.append("\n");
                Log.d("buffer", " onComplete");
            }
        };
    }
    
}

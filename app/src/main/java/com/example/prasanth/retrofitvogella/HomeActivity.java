package com.example.prasanth.retrofitvogella;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.prasanth.retrofitvogella.model.Question;
import com.example.prasanth.retrofitvogella.model.StackOverflowQuestions;
import com.example.prasanth.retrofitvogella.rest.StackOverflowAPI;

import java.util.List;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {
    Call<StackOverflowQuestions> normalCall;
    Observable<StackOverflowQuestions> rxCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rxCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<StackOverflowQuestions>() {
                    @Override
                    public void call(StackOverflowQuestions stackOverflowQuestions) {
                        List<Question> questions = stackOverflowQuestions.items;
                        for (Question question : questions) {
                            System.out.println("XXXX question[" + question.toString() + "]");
                        }
                    }
                });




////                normalCall.enqueue(new Callback<StackOverflowQuestions>() {
////                    @Override
////                    public void onResponse(Response<StackOverflowQuestions> response, Retrofit retrofit) {
////                                List<Question> questions = response.body().items;
////                                for(Question question:questions){
////                                    System.out.println("XXXX question["+question.toString()+"]");
////                                }
////                            }
////
////                            @Override
////                            public void onFailure(Throwable t) {
////
////                            }
////                        });
//
//
//
//
//                    @Override
//                    public void onFailure(Throwable t) {
//
//                    }
//                });
            }
        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.stackexchange.com").addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        StackOverflowAPI api = retrofit.create(StackOverflowAPI.class);
//        normalCall = api.loadQuestions("android");
        rxCall = api.loadQuestionsUsingRx("android");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

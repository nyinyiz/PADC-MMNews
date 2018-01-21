package com.nyinyi.nw.sfc.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.nyinyi.nw.sfc.R;

import butterknife.ButterKnife;

/**
 * Created by User on 11/14/2017.
 */

public class NewsCommentActivity extends BaseActivity {


    public static Intent newIntent(Context context)
    {
        Intent intent = new Intent(context,NewsCommentActivity.class);
        return intent;
    }
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_catagory);
        ButterKnife.bind(this,this);



    }
}

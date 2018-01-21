package com.nyinyi.nw.sfc.network;

import android.content.Context;

/**
 * Created by User on 12/3/2017.
 */

public interface MMNewsDataAgent {

    void loadMMNews(String accessToken , int pageNo, Context context);

}

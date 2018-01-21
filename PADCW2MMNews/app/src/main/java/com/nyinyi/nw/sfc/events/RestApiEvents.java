package com.nyinyi.nw.sfc.events;

import android.content.ContentValues;
import android.content.Context;

import com.nyinyi.nw.sfc.data.vo.NewsVO;

import java.util.List;

/**
 * Created by User on 12/2/2017.
 */

public class RestApiEvents {

    public static class EmptyResponseEvent
    {

    }

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class NewsDataLoadedEvent{
        private int loadedpageIndex;
        private List<NewsVO> loadNews;

        private Context context;

        public NewsDataLoadedEvent(int loadedpageIndex, List<NewsVO> loadNews,Context context) {
            this.loadedpageIndex = loadedpageIndex;
            this.loadNews = loadNews;
            this.context = context;

        }

        public int getLoadedpageIndex() {
            return loadedpageIndex;
        }

        public List<NewsVO> getLoadNews() {
            return loadNews;
        }

        public Context getContext() {
            return context;
        }
    }


}

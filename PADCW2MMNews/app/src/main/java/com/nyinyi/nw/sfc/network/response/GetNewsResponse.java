package com.nyinyi.nw.sfc.network.response;

import com.google.gson.annotations.SerializedName;
import com.nyinyi.nw.sfc.data.vo.NewsVO;
import com.nyinyi.nw.sfc.network.SFCResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/3/2017.
 */

public class GetNewsResponse extends SFCResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String messaeg;
    @SerializedName("apiVersion")
    private String apiVersion;
    @SerializedName("page")
    private int pageNo;
    @SerializedName("mmNews")
    private List<NewsVO> newsList;

    public int getCode() {
        return code;
    }

    public String getMessaeg() {
        return messaeg;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public int getPageNo() {
        return pageNo;
    }

    public List<NewsVO> getNewsList() {
        if (newsList == null)
        {
            newsList = new ArrayList<>();
        }
        return newsList;
    }
}

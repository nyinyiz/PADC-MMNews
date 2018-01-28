package com.padcmyanmar.sfcnn.delegates;

import com.padcmyanmar.sfcnn.data.vo.NewsVO;

/**
 * Created by aung on 11/11/17.
 */

public interface NewsItemDelegate {
    void onTapComment();
    void onTapSendTo();
    void onTapFavorite();
    void onTapStatistics();
    void onTapNews(NewsVO news);
}

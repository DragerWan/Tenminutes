package com.czy.tenminutes.Listener;


import com.czy.tenminutes.Bean.ArticleLatest;

/**
 * Created by ZY on 2016/7/26.
 * 加载最新文章事件监听
 */
public interface OnLoadLatestArticleListener {

    void onSuccess(ArticleLatest articleLatest);

    void onFailure();
}

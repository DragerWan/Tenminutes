package com.czy.tenminutes.Listener;


import com.czy.tenminutes.Bean.ArticleBefore;

/**
 * Created by ZY on 2016/7/28.
 * 加载过去文章事件监听
 */
public interface OnLoadBeforeArticleListener {

    void onSuccess(ArticleBefore articleBefore);

    void onFailure();
}

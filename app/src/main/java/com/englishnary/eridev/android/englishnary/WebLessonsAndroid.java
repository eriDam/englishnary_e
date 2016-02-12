package com.englishnary.eridev.android.englishnary;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebLessonsAndroid extends ActionBarActivity {
    //Declaro los controles
    WebView wview_lesson_android;
    final String urlAndroid = "https://developer.android.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_lessons_android);
        //Obtengo los controles
        wview_lesson_android = (WebView) findViewById(R.id.wview_lesson_android);
        WebSettings settings = wview_lesson_android.getSettings();
        settings.setJavaScriptEnabled(true);
        wview_lesson_android.setWebViewClient(new MyWebViewClientAndro());
        wview_lesson_android.loadUrl(urlAndroid);
    }

    //Creo clase privada MyWebClient para manejar la vista de web
    private class MyWebViewClientAndro extends WebViewClient
    {
        //ctrol+o sobreescribir metod, cogemos este para sobreescribir solo.
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }//FIN CLASS MyWebViewClient



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_web_lessons_android, menu);
//        return true;
//    }

    }

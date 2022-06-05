package com.aguilacontrol.gpstracking;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URISyntaxException;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        WebView myWebView = (WebView) findViewById(R.id.uiWebView);
        WebSettings webSettings = myWebView.getSettings();
        //myWebView.setWebViewClient(new WebViewClient());

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if( URLUtil.isNetworkUrl(url) ) {
                    return false;
                }

                if(url != null && url.startsWith("whatsapp://")){

                    try {
                        view.getContext().startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return true;

                }

                if(url != null && url.startsWith("waze://")){

                    try {
                        // Launch Waze to look for Hawaii:
                        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                        startActivity( intent );

                        //startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url)));
                    } catch ( ActivityNotFoundException ex ){
                        // If Waze is not installed, open it in Google Play:
                        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.waze" ) );
                        startActivity(intent);
                    }

                    return true;

                }

                if (url.startsWith("tel:")) {
                    try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent);
                    view.reload();
                    return true;
                    } catch ( ActivityNotFoundException ex ){
                        ex.printStackTrace();
                    }
                }

                if (url.startsWith("sms:")) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
                        startActivity(intent);
                        view.reload();
                        return true;
                    } catch ( ActivityNotFoundException ex ){
                        ex.printStackTrace();
                    }
                }


                /*
                if (appInstalledOrNot(url)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity( intent );
                } else {
                    // do something if app is not installed
                }
                */
                return true;
            }

        });


        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");

        //myWebView.loadUrl("http://190.117.148.164:90/_Proyectos/apps/mobile/index.php");
        //myWebView.loadUrl("http://178.62.226.83/android/mobile/index.php"); Servitax
        //myWebView.loadUrl("http://159.203.165.175/apps/android/mobile/index.php"); //TrackPy
        //myWebView.loadUrl("http://206.189.222.46/apps/android/mobile/index.php"); //Controlsat
        //myWebView.loadUrl("http://207.154.195.114/apps/android/betel/mobile/index.php"); //Betel
        //myWebView.loadUrl("http://45.55.119.54/android/aguilacontrol/mobile/index.php"); //AguilaControl
        //myWebView.loadUrl("http://178.62.19.78/android/celcomapp/207.154.202.114/mobile/"); //Celcom
        //myWebView.loadUrl("http://104.227.139.157/android/appalessamgps/mobile/index.php"); // AlessamGPS
        //myWebView.loadUrl("http://45.55.119.54/android/appmartinez/mobile/index.php"); //Martinez
        //myWebView.loadUrl("http://69.4.81.122/android/talentus/mobile/index.php"); //Talentus
        //myWebView.loadUrl("http://142.93.185.141/android/142.93.185.141/mobile/index.php"); // Richard Medina 1
        myWebView.loadUrl("http://localiza.gpsskynet.com/mobile2/mobile/");

    }


    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }


}


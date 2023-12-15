package com.example.onlinepdfbookreaderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class PdfViewerActivity extends AppCompatActivity {

    WebView webView;

    public static  String pdf_link = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        webView = findViewById(R.id.webViewId);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        Toast.makeText(PdfViewerActivity.this,"Wait a few second! page is loading...",Toast.LENGTH_SHORT).show();
        webView.loadUrl(pdf_link);


    }
}
package com.troyward.browserandroid;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText addressBar;
    Button back;
    Button forward;
    Button go;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addressBar = (EditText) findViewById(R.id.addressBar);
        back = (Button) findViewById(R.id.back);
        forward = (Button) findViewById(R.id.forward);
        go = (Button) findViewById(R.id.go);
        web = (WebView) findViewById(R.id.web);

        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        go.setOnClickListener(this);

        WebViewClient client = new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                addressBar.setText(url);
            }
        };
        web.setWebViewClient(client);
    }

    @Override
    public void onClick(View v) {
        if (v == back) {
            web.goBack();
        }
        else if (v == forward) {
            web.goForward();
        }
        else if (v == go) {
            String url = addressBar.getText().toString();
            if(!url.startsWith("http")) {
                url = "http://" + url;
            }
            web.loadUrl(url);
        }
    }
}

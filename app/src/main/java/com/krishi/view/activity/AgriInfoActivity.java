package com.krishi.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.krishi.R;
import com.krishi.databinding.ActivityAgriInfoBinding;
import com.krishi.viewmodel.AgriInfoViewModel;

public class AgriInfoActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    @Override
    protected void initialize() {
        ActivityAgriInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_agri_info);
        AgriInfoViewModel agriInfoViewModel = new ViewModelProvider(this).get(AgriInfoViewModel.class);
        agriInfoViewModel.setupModel(this);
        binding.setViewModel(agriInfoViewModel);
        binding.setLifecycleOwner(this);

        webView = findViewById(R.id.web_view);
        webView.loadUrl("http://agritech.tnau.ac.in/");
        webView.setWebViewClient(new AgriWebViewClient());
    }

    @Override
    protected void updateUI(String type, String data) {

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    private class AgriWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("http://agritech.tnau.ac.in/")) {
                view.loadUrl(url);
                return true;
            }
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            showDialogPopup("Loading...");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dismissDialogPopup();
        }
    }
}

package com.x1ongzhu.pdfviewer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;


public class PDFActivity extends Activity implements Callback.CommonCallback<File>, Callback.ProgressCallback<File>, Callback.Cancelable {

    private final static int MAX_DOWNLOAD_THREAD = 2;

    private final Executor executor = new PriorityExecutor(MAX_DOWNLOAD_THREAD, true);

    private PDFView     pdfView;
    private ProgressBar progressBar;
    private TextView    tvPage;
    private Timer       timeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResources().getIdentifier("activity_pdf", "layout", getPackageName()));
        pdfView = findViewById(getResources().getIdentifier("pdfView", "id", getPackageName()));
        progressBar = findViewById(getResources().getIdentifier("progressBar", "id", getPackageName()));
        tvPage = findViewById(getResources().getIdentifier("tvPage", "id", getPackageName()));
        JSONObject params;
        String url = null;
        try {
            params = new JSONObject(getIntent().getStringExtra("params"));
            url = params.getString("url");
        } catch (Exception e) {
            finish();
        }
        timeout = new Timer();

        RequestParams requestParams = new RequestParams(url);
        requestParams.setAutoResume(true);
        requestParams.setAutoRename(true);
        requestParams.setExecutor(executor);
        requestParams.setCancelFast(true);
        x.http().get(requestParams, this);
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void onWaiting() {

    }

    @Override
    public void onStarted() {
        progressBar.setProgress(0);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {
        progressBar.setProgress((int) (current * 100 / total));
    }

    @Override
    public void onSuccess(File result) {
        progressBar.setVisibility(View.GONE);
        pdfView.fromFile(result)
                .spacing(10)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        timeout.cancel();
                        tvPage.setText((page + 1) + "/" + pageCount);
                        tvPage.setVisibility(View.VISIBLE);
                        timeout = new Timer();
                        timeout.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvPage.setVisibility(View.GONE);
                                    }
                                });
                            }
                        }, 2000);
                    }
                })
                .load();
        setTitle(result.getName());
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCancelled(CancelledException cex) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFinished() {
        progressBar.setVisibility(View.GONE);
    }
}

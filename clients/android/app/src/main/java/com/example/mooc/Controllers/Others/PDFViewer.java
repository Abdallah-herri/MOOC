package com.example.mooc.Controllers.Others;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mooc.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class PDFViewer extends AppCompatActivity {
    byte[] test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            test = extras.getByteArray("course");
        }

        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromBytes(test)
                .enableSwipe(true) // allows to block changing pages using swipe
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(10)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
    }

}

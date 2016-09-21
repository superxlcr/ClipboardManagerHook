package com.github.superxlcr.clipboardhook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // hook clipBoardManager
        final Button hookBtn1 = (Button)findViewById(R.id.hook_btn_1);
        hookBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ClipboardManagerHookHelper.hook1();
                    toastShow("Hook 1 success!");
                    hookBtn1.setEnabled(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    toastShow("Hook 1 fail!");
                }
            }
        });
        final Button hookBtn2 = (Button)findViewById(R.id.hook_btn_2);
        hookBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ClipboardManagerHookHelper.hook2();
                    toastShow("Hook 2 success!");
                    hookBtn2.setEnabled(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    toastShow("Hook 2 fail!");
                }
            }
        });
    }

    private void toastShow(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}

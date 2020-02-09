package com.bird_brown.drawapp01;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DrawViewのオブジェクトを取得
        drawView = (DrawView) findViewById(R.id.drawView1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);    //メニュー項目を設定
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();  //メニュー項目のIDを取得

        switch (itemId) {
            case R.id.item1:
                drawView.backCanvas();
                break;
            case R.id.item2:
                drawView.clearCanvas();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

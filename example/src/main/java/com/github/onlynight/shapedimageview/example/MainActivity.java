package com.github.onlynight.shapedimageview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.onlynight.chatimageview.ChatImageView;
import com.github.onlynight.shapedimageview.ShapedImageView;

public class MainActivity extends AppCompatActivity {

    private ChatImageView chatImageView;
    private ShapedImageView shapedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chatImageView = (ChatImageView) findViewById(R.id.chatImageView);
        shapedImageView = (ShapedImageView) findViewById(R.id.shapedImageView);
        chatImageView.setSharpCornerStart(Utils.dpToPx(100));
        chatImageView.setCornerRadius(Utils.dpToPx(20));
        chatImageView.setSharpCornerDirection(ChatImageView.SHARP_CORNER_DIRECTION_RIGHT);
//        chatImageView.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

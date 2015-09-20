package com.library.lili.albumfuntek;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.library.lili.ablumli.album.AlbumFuntek;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ExampleActivity extends Activity {

    @InjectView(R.id.gvDisplayPhoto)
    GridView gvDisplayPhoto;
    @InjectView(R.id.comfirm)
    TextView comfirm;


    private GridView gridView;
    private AlbumFuntek album;
    private List<String> List2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        ButterKnife.inject(this);
        //宣告要放置的位置
        gridView = (GridView) findViewById(R.id.gvDisplayPhoto);
        //宣告要使用的方法
        album = new AlbumFuntek(this, gridView);

//        onlyShow();
        setHaveSelect();
//        getSelectImagePath();
    }

    @OnClick(R.id.comfirm)
    public void onClickComfirm() {
        getSelectImagePath();
    }


    private void onlyShow() {
        album.startAlbum();
    }

    private void setHaveSelect() {
        album.setNumOfColum(5);//設定欄位，不設定預設為三
        album.setGridPadding(9);//設定item之間的邊界，不設定預設為三
        album.startAlbum();
    }

    private void getSelectImagePath() {
        List2 = album.getSelectImagePath();
        String temp = "";
        for (String tmp : List2) {
//            Log.e("select path", tmp);
            temp = temp + tmp;
        }

        Toast.makeText(ExampleActivity.this, temp, Toast.LENGTH_LONG).show();

        clearAllSelect();
    }

    private void clearAllSelect() {
        album.clearAllSelect();
    }


}
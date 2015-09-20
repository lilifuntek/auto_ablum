package com.library.lili.ablumli.album;

import android.app.Activity;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lili on 2015/9/2.
 */
public class AlbumFuntek {
    private Activity mActivity;
    private GridProperty gridProperty;
    private AlbumCursorAdapter adapter;

//    private List<Builder> builderList;


    public AlbumFuntek(Activity mActivity, GridView gridView) {
        this.mActivity = mActivity;
        gridProperty = new GridProperty(mActivity, gridView);
    }


    public void startAlbum() {
        gridProperty.setmGridViewProperty();
         adapter = new AlbumCursorAdapter(mActivity, gridProperty.getColumnWidth());
        gridProperty.getmGridView().setAdapter(adapter);
    }

    public void setGridPadding(int gridPadding) {
        gridProperty.setGridPadding(gridPadding);
    }

    public void setNumOfColum(int numOfColum) {
        gridProperty.setNumOfColum(numOfColum);

    }

    //    public void setColumnWidth(int columnWidth) {
//        gridProperty.setColumnWidth(columnWidth);
//    }
    private void setOneSelect() {

    }

    public List<String> getSelectImagePath() {
        List<String> List2 = new ArrayList<>();
        for (int i = 0; i < adapter.getCount()-1; i++) {
            if (adapter.getIsSelected().get(i)) {
                List2.add(adapter.getBuilderList().get(i).getAlbumImage());
            }
        }
        return List2;
    }

    public void clearAllSelect() {
        for (int i = 0; i < adapter.getCount()-1; i++) {
            if (adapter.getIsSelected().get(i)) {
                adapter.getIsSelected().put(i, false);
            }
        }
        adapter.notifyDataSetChanged();
    }

}

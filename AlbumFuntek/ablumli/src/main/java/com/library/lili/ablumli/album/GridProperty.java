package com.library.lili.ablumli.album;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.GridView;

/**
 * Created by lili on 2015/9/2.
 */
public class GridProperty {
    private Context mContext;
    private GridView mGridView;

    private int gridPadding = 3; // in dp
    private int numOfColum = 3;
    private int columnWidth = 0;


    public void setGridPadding(int gridPadding) {
        this.gridPadding = gridPadding;
    }

    public void setNumOfColum(int numOfColum) {
        this.numOfColum = numOfColum;
    }

    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;

    }


    public int getColumnWidth() {
        return columnWidth;
    }

    public GridView getmGridView() {
        return mGridView;
    }

    public GridProperty(Context mContext, GridView mGridView) {
        this.mContext = mContext;
        this.mGridView = mGridView;
    }

    public void setmGridViewProperty() {
        Resources r = mContext.getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                gridPadding, r.getDisplayMetrics());
        if (columnWidth == 0) {
            columnWidth = (int) ((getScreenWidth() - ((numOfColum + 1) * padding)) / numOfColum);
        } else {
            columnWidth = (int) ((columnWidth - ((numOfColum + 1) * padding)) / numOfColum);
        }
        mGridView.setNumColumns(numOfColum);
        mGridView.setColumnWidth(columnWidth);
        mGridView.setStretchMode(GridView.NO_STRETCH);
        mGridView.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        mGridView.setHorizontalSpacing((int) padding);
        mGridView.setVerticalSpacing((int) padding);
    }

    private int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        return columnWidth;
    }
}

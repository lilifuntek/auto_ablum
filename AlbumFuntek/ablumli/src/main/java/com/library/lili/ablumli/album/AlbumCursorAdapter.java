package com.library.lili.ablumli.album;

import android.app.Activity;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.library.lili.ablumli.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AlbumCursorAdapter extends BaseAdapter {

    private Activity _activity;
    private int imageWidth;
    //    private Cursor imagecursor;
    private static HashMap<Integer, Boolean> isSelected;
    private LayoutInflater inflater = null;

    private List<Builder> builderList;


    public AlbumCursorAdapter(Activity activity, int imageWidth) {
        this._activity = activity;
        this.imageWidth = imageWidth;

        inflater = LayoutInflater.from(activity);
        builderList = new ArrayList<>();
        allImagesByCursor();
        isSelected = new HashMap<Integer, Boolean>();
        initDate();
    }

    public Cursor allImagesByCursor() {

        final String[] columns = {
                MediaStore.Images.Media.DATA,
        };

        //final String orderBy = MediaStore.Images.Media._ID;
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN + " DESC";
        Cursor imagecursor = _activity.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                null,
                null,
                orderBy);
        for (imagecursor.moveToFirst(); !imagecursor.isAfterLast(); imagecursor.moveToNext()) {
            int pathColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
            String s = imagecursor.getString(pathColumnIndex);
            builderList.add(new Builder(s));

        }
        return imagecursor;
    }

    private void initDate() {
        for (int i = 0; i < builderList.size(); i++) {
            getIsSelected().put(i, false);// ==isSelected(key,value)
        }

    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }


    @Override
    public int getCount() {
        return this.builderList.size();
    }

    @Override
    public String getItem(int position) {

        return this.builderList.get(position).getAlbumImage();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_image_grid_albums, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.image.getLayoutParams().height = imageWidth;
        holder.image.getLayoutParams().width = imageWidth;


        File imgFile = new File(builderList.get(position).getAlbumImage());
        if (imgFile.exists()) {
            Glide.with(_activity)
                    .load(builderList.get(position).getAlbumImage())
                    .centerCrop()
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.theme_cell_bg_icon_no_text)
                    .error(R.drawable.theme_cell_bg_icon_no_text)
                    .into(holder.image);
        }
        holder.image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.checkBox.setChecked(!holder.checkBox.isChecked());

                if (getIsSelected().get(position)) {
                    getIsSelected().put(position, false);

                } else {
                    getIsSelected().put(position, true);
                }
            }
        });
        holder.checkBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                if (holder.checkBox.isChecked()) {
                    getIsSelected().put(position, true);
                } else {
                    getIsSelected().put(position, false);
                }
            }
        });
        if (getIsSelected().get(position)) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }

        return convertView;
    }



    public List<Builder> getBuilderList() {
        return builderList;
    }

    public static class ViewHolder {
        ImageView image;
        CheckBox checkBox;

    }
}

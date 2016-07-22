package samcorp.newsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pauljoiner on 7/19/16.
 */
public class CategoryListAdapter extends BaseAdapter {

    List<NewsCategory> mCategoryList;
    private LayoutInflater layoutInflater;
   ArrayList<String> followedCategories;
    ArrayHelper arrayHelper;
    public String key;
    Context context;
    public CategoryListAdapter(Context context, List<NewsCategory> mCategoryList){
        arrayHelper = new ArrayHelper(context);
        this.followedCategories = arrayHelper.getArray("followed");
        Log.d("FOLLOWED_ARRAY", followedCategories.toString());
        this.mCategoryList = mCategoryList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mCategoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return mCategoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.drawer_list_item, null);
        }
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
        Log.d("CHECK_FOLLWD", followedCategories.toString());
        if(followedCategories.contains(mCategoryList.get(i).getName())){
            checkBox.setChecked(true);
        }else{checkBox.setChecked(false);}
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    followedCategories.add(mCategoryList.get(i).getName());
                    Log.d("FOLLOW", followedCategories.get(followedCategories.indexOf(mCategoryList.get(i).getName())));
                }else {
                    followedCategories.remove(mCategoryList.get(i).getName());
                }
                arrayHelper.saveArray("followed", followedCategories);

            }
        });

        TextView nameView = (TextView) convertView.findViewById(R.id.drawer_list_text);
        ImageView categoryImage = (ImageView) convertView.findViewById(R.id.drawer_list_image);
        nameView.setText(mCategoryList.get(i).getName());
        categoryImage.setImageResource(mCategoryList.get(i).getImageId());
        return convertView;
    }

}

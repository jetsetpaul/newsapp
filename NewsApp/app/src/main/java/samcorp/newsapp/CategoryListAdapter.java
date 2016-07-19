package samcorp.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pauljoiner on 7/19/16.
 */
public class CategoryListAdapter extends BaseAdapter {
    List<NewsCategory> mCategoryList;
    private LayoutInflater layoutInflater;

    public CategoryListAdapter(Context context, List<NewsCategory> mCategoryList){
        this.mCategoryList = mCategoryList;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.drawer_list_item, null);
        }
        TextView nameView = (TextView) convertView.findViewById(R.id.drawer_list_text);
        ImageView categoryImage = (ImageView) convertView.findViewById(R.id.drawer_list_image);
        nameView.setText(mCategoryList.get(i).getName());
        categoryImage.setImageResource(mCategoryList.get(i).getImageId());
        return convertView;
    }
}

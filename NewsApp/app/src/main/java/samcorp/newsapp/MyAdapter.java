package samcorp.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by samwyz on 7/18/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<Story> mStoryList;
    Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Story> storyList, Context context) {

        this.mStoryList = storyList;
        this.mContext = context;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mHeadline.setText(mStoryList.get(position).getTitle());
        holder.mBlurb.setText(mStoryList.get(position).getBlurb());
        Picasso.with(mContext).load(mStoryList.get(position).getImage()).into(holder.mImage);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mStoryList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mHeadline;
        public TextView mBlurb;
        public ImageView mImage;

        public ViewHolder(View itemView) {

            super(itemView);
            mHeadline = (TextView) itemView.findViewById(R.id.headline_text);
            mBlurb = (TextView) itemView.findViewById(R.id.blurb_text);
            mImage = (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Bundle bundle = new Bundle();
            Story story = mStoryList.get(position);
            Intent intent = new Intent(mContext, DetailActivity.class);
            bundle.putSerializable("story", story);
            intent.putExtras(bundle);
            mContext.startActivity(intent);

        }
    }
}

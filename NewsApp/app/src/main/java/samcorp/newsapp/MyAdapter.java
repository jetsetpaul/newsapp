package samcorp.newsapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by samwyz on 7/18/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<News> newsList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<News> newsList) {

        this.newsList = newsList;

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
        holder.mHeadline.setText(newsList.get(position).getmHeadline());
        holder.mCategory.setText(newsList.get(position).getmCategory());
        holder.mLink.setText(newsList.get(position).getmWebLink());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return newsList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mHeadline;
        public TextView mCategory;
        public TextView mLink;

        public ViewHolder(View itemView) {

            super(itemView);
            mHeadline = (TextView) itemView.findViewById(R.id.headline_text);
            mCategory = (TextView) itemView.findViewById(R.id.category_text);
            mLink = (TextView) itemView.findViewById(R.id.link_text);

        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            News news = newsList.get(position);

        }
    }
}

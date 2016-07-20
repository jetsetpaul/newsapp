package samcorp.newsapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by samwyz on 7/19/16.
 */
public class MyCursorAdapter extends RecyclerView.Adapter<MyCursorAdapter.ViewHolder> implements
        MyContentObserver.changeListener{

    Context context;
    Cursor cursor;


    public MyCursorAdapter(Context context, Cursor cursor){

        this.context = context;
        this.cursor = cursor;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        cursor.moveToPosition(position);
        holder.mHeadline.setText(cursor.getString(cursor.getColumnIndex(NewsDBHelper.COLUMN_TITLE)));
        holder.mBlurb.setText(cursor.getString(cursor.getColumnIndex(NewsDBHelper.COLUMN_BLURB)));
        Picasso.with(context)
                .load(cursor.getString(cursor.getColumnIndex(NewsDBHelper.COLUMN_IMAGE)))
                .resize(1000, 650)
                .centerInside()
                .into(holder.mImage);

    }

    @Override
    public int getItemCount() {

        return cursor.getCount();
    }

    @Override
    public void change(Cursor cursor) {

        this.cursor = context.getContentResolver().query(NewsContentProvider.CONTENT_URI,
                null, null, null, null);
        notifyDataSetChanged();


    }

    public class ViewHolder extends RecyclerView.ViewHolder{



        public TextView mHeadline;
        public TextView mBlurb;
        public ImageView mImage;

        public ViewHolder(View itemView) {

            super(itemView);
            mHeadline = (TextView) itemView.findViewById(R.id.headline_text);
            mBlurb = (TextView) itemView.findViewById(R.id.blurb_text);
            mImage = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}
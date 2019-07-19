package com.example.paymat;

import androidx.annotation.NonNull;
import android.content.Context;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.operation.ListenComplete;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> implements Filterable {
    private Context mContext;
    private List<Upload> mUploads;
    private List<Upload> uploadfull;
    private onItemClickListener mlistener;;


    public ImageAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
        uploadfull=new ArrayList<>(mUploads);
    }
    @Nullable
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        android.view.View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.textviewroute.setText(uploadCurrent.getroute());
        holder.textViewgroup.setText(uploadCurrent.getgroup());
        Picasso.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.imageview);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewName,textviewroute,textViewgroup;
        public ImageView imageview;

        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            textviewroute=itemView.findViewById(R.id.text_view_route);
            textViewgroup=itemView.findViewById(R.id.text_view_group);
            imageview = itemView.findViewById(R.id.image_view_upload);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mlistener!=null){
                int position=getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION){
                    mlistener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("select action");
            MenuItem doWhatever= contextMenu.add(contextMenu.NONE,1,1,"pay");
            MenuItem delete=contextMenu.add(contextMenu.NONE,2,2,"");


            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (mlistener!=null){
                int position=getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION){
                    switch (menuItem.getItemId()){
                        case 1:
                            mlistener.onwhateverClick(position);
                            return true;case 2:
                            mlistener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }
    public interface onItemClickListener{
        void onItemClick(int position);
        void onwhateverClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(onItemClickListener listener){
        mlistener=listener;

    }

    @Override
    public Filter getFilter() {
        return uploadfilter;
    }
    private Filter uploadfilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Upload> filteredList=new ArrayList<>();
            if (charSequence==null || charSequence.length()==0){
                filteredList.addAll(uploadfull);
            }
            else {
                String Filterpattern=charSequence.toString().toLowerCase().trim();

                for (Upload item:uploadfull){
                    if (item.getName().toLowerCase().contains(Filterpattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mUploads.clear();
            mUploads.addAll((List) filterResults.values);
            notifyDataSetChanged();

        }
    };
}
package com.tklabs.instahealthassit;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter <ImageAdapter.ImageViewHolder> {



    private Uri mImageUri;

    private Context mContext;
    private List<Upload> mUploads;

    ImageAdapter(Context context, List<Upload> upload){
        mContext = context;
        mUploads = upload;


    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);

        return new ImageViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {


        Upload uploadCurrent = mUploads.get(position);


        holder.textViewName.setText(uploadCurrent.getName());


        Picasso.get()
                .load(uploadCurrent.getImageUrl())
                .fit()
                .placeholder(R.drawable.upload_defalt)
              //  .centerCrop()
                .into(holder.imageView);
        


    }


    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName;

        ImageView imageView;


        ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);
        }


    }





}

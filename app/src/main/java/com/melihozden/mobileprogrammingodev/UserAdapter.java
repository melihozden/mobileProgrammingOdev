package com.melihozden.mobileprogrammingodev;

import android.content.Context ;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> mUserList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    UserAdapter(Context context, ArrayList<User> users) {
        this.mInflater = LayoutInflater.from(context);
        this.mUserList = users;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.kullanicilar, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User selectedUser = mUserList.get(position);
        holder.setData(selectedUser,position);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mUserList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView,myTextViewPassword;
        ImageView myImageView ;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.userName);
            myTextViewPassword = itemView.findViewById(R.id.userName2);
            myImageView = itemView.findViewById(R.id.userImage);
            itemView.setOnClickListener(this);
        }


        // Bir item'a tıklanıldığı anda yapılacak eylemler
       @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }


        public void setData(User selectedUser, int position){
            this.myTextView.setText(selectedUser.getUserName());
            this.myTextViewPassword.setText(selectedUser.getUserPassword());
            this.myImageView.setImageResource(selectedUser.getImageID());
        }
    }
    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

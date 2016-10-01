package com.group19.seng301_w2016.yourlifecounter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Jennykuma on 2016-03-05.
 */
public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cv_profile;
        public TextView nameTextView;
        public TextView dateTextView;
        public TextView infoTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            cv_profile = (CardView) itemView.findViewById(R.id.cardvprofile);
            nameTextView = (TextView) itemView.findViewById(R.id.username);
            dateTextView = (TextView) itemView.findViewById(R.id.date);
            infoTextView = (TextView) itemView.findViewById(R.id.info);
        }
    }

        private List<DiaryEntries> mEntries;

        public DiaryAdapter(List<DiaryEntries> entries){
            mEntries = entries;
        }

        @Override
        public DiaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            Context context = parent.getContext();
            LayoutInflater inflator = LayoutInflater.from(context);

            // Inflate the custom layout
            View profileView = inflator.inflate(R.layout.diaryformat, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(profileView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(DiaryAdapter.ViewHolder viewHolder, int position){
            DiaryEntries diaryEntries = mEntries.get(position);

            TextView userTextView = viewHolder.nameTextView;
            userTextView.setText(diaryEntries.getUserName());

            TextView dateTextView = viewHolder.dateTextView;
            dateTextView.setText(diaryEntries.getDate());

            TextView infoTextView = viewHolder.infoTextView;
            infoTextView.setText(diaryEntries.getDiaryInfo());
        }

        @Override
        public int getItemCount(){
            return mEntries.size();
        }
}

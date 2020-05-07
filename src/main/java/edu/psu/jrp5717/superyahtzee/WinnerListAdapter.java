package edu.psu.jrp5717.superyahtzee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class WinnerListAdapter {}//extends RecyclerView.Adapter<WinnerListAdapter.WinnerViewHolder> {
    /*class WinnerViewHolder extends RecyclerView.ViewHolder {
        private final TextView winnerItemView;

        private WinnerViewHolder(View itemView) {
            super(itemView);
            winnerItemView = itemView.findViewById(R.id.recyclerview_item);
        }
    }

    private final LayoutInflater mInflater;
    private List<Winner> mWinners; // Cached copy of winners

    WinnerListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public WinnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.activity_first, parent, false);
        return new WinnerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WinnerViewHolder holder, int position) {
        if (mWinners != null) {
            Winner current = mWinners.get(position);
            holder.winnerItemView.setText(current.getWinner());
        } else {
            // Covers the case of data not being ready yet.
            holder.winnerItemView.setText("No Winner");
        }
    }

    void setWinners(List<Winner> winners){
        mWinners = winners;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWinners has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWinners != null)
            return mWinners.size();
        else return 0;
    }
}*/
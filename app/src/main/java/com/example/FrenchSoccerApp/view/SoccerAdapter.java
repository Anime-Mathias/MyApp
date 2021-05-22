package com.example.FrenchSoccerApp.view;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.FrenchSoccerApp.R;
import com.example.FrenchSoccerApp.model.TeamDetailsModel;
import com.squareup.picasso.Picasso;

public class SoccerAdapter extends RecyclerView.Adapter<SoccerAdapter.ViewHolder> {

    private List<TeamDetailsModel> values;
    private final View.OnClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textHeader;
        public TextView textFooter;
        public ImageView icon;
        public View layout;
        public ViewHolder(View v) {
                super(v);
                layout = v;
                textHeader = (TextView) v.findViewById(R.id.firstLine);
                textFooter = (TextView) v.findViewById(R.id.secondLine);
                icon = (ImageView) v.findViewById(R.id.icon);
            }
    }

    public void add(int position, TeamDetailsModel item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public SoccerAdapter(List<TeamDetailsModel> myDataset, View.OnClickListener listener) {
        values = myDataset;
        this.listener = listener;
    }

    @Override
    public SoccerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        v.setOnClickListener(this.listener);
        ViewHolder aze = new ViewHolder(v);
        return aze;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TeamDetailsModel currentMatch = values.get(position);
        final String name = currentMatch.getStrTeam();
        final String compet = currentMatch.getStrLeague();
        holder.textHeader.setText(name);
        holder.textFooter.setText(compet);

        Picasso
                .get()
                .load(currentMatch.getStrTeamBadge())
                .placeholder(R.drawable.default_icon)
                .resize(0,180)
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
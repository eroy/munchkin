package sergey.zhuravel.munchkin.ui.fight;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sergey.zhuravel.munchkin.R;
import sergey.zhuravel.munchkin.model.PlayerFight;

public class FightAdapter extends RecyclerView.Adapter<FightAdapter.ViewHolder> {

    private int rowIndex = 0;
    private List<PlayerFight> mListPlayer;
    private FightContract.Presenter mPresenter;
    private Context mContext;

    public FightAdapter(FightContract.Presenter mPresenter, Context context) {
        this.mPresenter = mPresenter;
        this.mContext = context;
        mListPlayer = new ArrayList<>();
    }

    public void addPlayers(List<PlayerFight> playerList) {
        mListPlayer.clear();
        mListPlayer.addAll(playerList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_munchkin_fight, parent, false);
        return new FightAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        PlayerFight player = mListPlayer.get(position);
        holder.nameMunchkin.setText(player.getName());
        holder.level.setText(String.valueOf(player.getLevel()));
        holder.allCount.setText(String.valueOf(player.getSummary()));

        holder.rlName.setOnClickListener(v -> {
            rowIndex = position;

            notifyDataSetChanged();

        });

        if (rowIndex == position) {
            mPresenter.setTextSubTitle(player);
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
        }

    }

    public List<PlayerFight> getListPlayer() {
        return mListPlayer;
    }

    public PlayerFight getCurrentPlayerFight() {
        return mListPlayer.get(rowIndex);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    @Override
    public int getItemCount() {
        return mListPlayer.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameMunchkin;
        TextView level;
        TextView allCount;
        RelativeLayout rlName;

        ViewHolder(View view) {
            super(view);

            nameMunchkin = (TextView) view.findViewById(R.id.tvName);
            level = (TextView) view.findViewById(R.id.tvLevel);
            allCount = (TextView) view.findViewById(R.id.tvAll);
            rlName = (RelativeLayout) view.findViewById(R.id.rlName);

            view.setClickable(true);


        }
    }
}

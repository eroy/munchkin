package sergey.zhuravel.munchkin.ui.start;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sergey.zhuravel.munchkin.R;
import sergey.zhuravel.munchkin.model.Player;


public class StartAdapter extends RecyclerView.Adapter<StartAdapter.ViewHolder> {

    private StartContract.Presenter mPresenter;
    private List<Player> mListPlayer;

    public StartAdapter(StartContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
        mListPlayer = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        mListPlayer.add(player);
        notifyDataSetChanged();
    }

    public void addPlayers(List<Player> playerList) {
        mListPlayer.clear();
        mListPlayer.addAll(playerList);
        notifyDataSetChanged();
    }

    public void deletePlayer(Player player) {
        mListPlayer.remove(player);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_munchkin_start, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Player player = mListPlayer.get(position);

        holder.nameMunchkin.setText(player.getName());
        holder.rlName.setOnClickListener(v -> mPresenter.editMunchkin(player));

    }

    @Override
    public int getItemCount() {
        return mListPlayer.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameMunchkin;
        LinearLayout rlName;

        ViewHolder(View view) {
            super(view);

            nameMunchkin = (TextView) view.findViewById(R.id.tvName);
            rlName = (LinearLayout) view.findViewById(R.id.rlName);

        }
    }
}

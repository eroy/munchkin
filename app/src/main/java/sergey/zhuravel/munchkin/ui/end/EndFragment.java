package sergey.zhuravel.munchkin.ui.end;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import im.dacer.androidcharts.LineView;
import rx.Observable;
import sergey.zhuravel.munchkin.R;
import sergey.zhuravel.munchkin.constant.Constant;
import sergey.zhuravel.munchkin.model.Game;
import sergey.zhuravel.munchkin.model.PlayerFight;
import sergey.zhuravel.munchkin.ui.base.BaseFragment;


public class EndFragment extends BaseFragment {


    private Game mGame;
    private GraphView mGraphLevel;
    private List<PlayerFight> mPlayerFightList;
    private LineView mLvLevel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_end, container, false);

        mGraphLevel = (GraphView) view.findViewById(R.id.graphLevel);
        mLvLevel = (LineView) view.findViewById(R.id.graph_gas);


        mGraphLevel.getViewport().setMaxX(20);
        mGraphLevel.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.RED);
        mGraphLevel.getSecondScale().setMaxY(20);

        mPlayerFightList = new ArrayList<>();

        mGame = getArguments().getParcelable(Constant.LIST_PLAYERS);
        mPlayerFightList.addAll(mGame.getPlayerFightList());


        Observable
                .from(mPlayerFightList)
                .filter(playerFight -> playerFight.getName().equals("serj"))
                .subscribe(playerFight -> {

                    String listlevel = String.valueOf(playerFight.getListLevel());
                    String listStrength = String.valueOf(playerFight.getListStrength());
                    String listSummary = String.valueOf(playerFight.getListSummary());

                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(generateDataPoint(playerFight.getListLevel()));
                    mGraphLevel.addSeries(series);

                    generateDat(playerFight.getListLevel());
                    Log.e("WIN", playerFight.getName() + ": level " + listlevel + ", strength " + listStrength
                            + ", summary " + listSummary);

                });

        Observable
                .from(mPlayerFightList)
                .filter(playerFight -> playerFight.getName().equals("vera"))
                .subscribe(playerFight -> {

                    String listlevel = String.valueOf(playerFight.getListLevel());
                    String listStrength = String.valueOf(playerFight.getListStrength());
                    String listSummary = String.valueOf(playerFight.getListSummary());

                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(generateDataPoint(playerFight.getListLevel()));
                    series.setColor(Color.RED);
                    mGraphLevel.getSecondScale().addSeries(series);


                    Log.e("WIN", playerFight.getName() + ": level " + listlevel + ", strength " + listStrength
                            + ", summary " + listSummary);

                });


        return view;
    }

    private DataPoint[] generateData() {
        Random mRand = new Random();

        int count = 30;
        DataPoint[] values = new DataPoint[count];
        for (int i = 0; i < count; i++) {
            double x = i;
            double f = mRand.nextDouble() * 0.15 + 0.3;
            double y = Math.sin(i * f + 2) + mRand.nextDouble() * 0.3;
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    private DataPoint[] generateDataPoint(List<Integer> list) {
        DataPoint[] dataPoints = new DataPoint[list.size()];
        for (int i = 0; i < list.size(); i++) {
            DataPoint dp = new DataPoint(i, list.get(i));
            dataPoints[i] = dp;
        }

        return dataPoints;

    }

    private void generateDat(List<Integer> list) {
        ArrayList<String> valueList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> priceListGraph = new ArrayList<>();
        ArrayList<Integer> priceList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            valueList.add(String.valueOf(i));
        }
        for (Integer a: list) {
            priceList.add(a);
        }
        priceListGraph.add(priceList);

        mLvLevel.setBottomTextList(valueList);
        mLvLevel.setDataList(priceListGraph);
        mLvLevel.setShowPopup(LineView.SHOW_POPUPS_All);

    }
}

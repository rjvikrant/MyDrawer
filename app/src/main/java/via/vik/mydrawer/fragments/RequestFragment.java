package via.vik.mydrawer.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import via.vik.mydrawer.MainActivity;
import via.vik.mydrawer.R;
import via.vik.mydrawer.adapters.ListViewAdapter;

/**
 * Created by MR__PN on 10-10-2017.
 */

public class RequestFragment extends Fragment {
    private ListView listView;
    private  ArrayAdapter<String> adapter;
    private  ArrayList<String> friendsList;
    private  TextView totalClassmates;
    private SwipeLayout swipeLayout;
    View rootView;
    String TAG="abc";
    LayoutInflater inflater;
    MainActivity mainActivity;


    public RequestFragment() {
    }

    public RequestFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater=inflater;
         rootView=inflater.inflate(R.layout.fragment_requests, container, false);



        listView = (ListView)rootView.findViewById(R.id.list_item1);

        friendsList = new ArrayList<>();
        getDataFromFile();
        setListViewHeader();

        if (getActivity() != null) {
            // Code goes here.
            setListViewAdapter(getContext());
        }


        return  rootView;

    }

    public  void updateAdapter() {
        adapter.notifyDataSetChanged(); //update adapter
        totalClassmates.setText("(" + friendsList.size() + ")"); //update total friends in list
    }



    private void getDataFromFile() {
        BufferedReader reader = null;
       /* try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("classmates.txt"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String line = reader.readLine();
            while (line != null && !line.equals("")) {
                line = reader.readLine();*/
                friendsList.add("line"); // add line to array list
        friendsList.add("line2");
        friendsList.add("line3");
        friendsList.add("line4"); // add line to array list
        friendsList.add("line5");
        friendsList.add("line6");
        friendsList.add("line7"); // add line to array list
        friendsList.add("line8");
        friendsList.add("line9");
        friendsList.add("line10"); // add line to array list
        friendsList.add("line11");
        friendsList.add("line12");

         /*   }
        } catch (IOException e) {
            //log the exception
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

    private void setListViewHeader() {

        View header = inflater.inflate(R.layout.header_listview, listView, false);
        totalClassmates = (TextView) header.findViewById(R.id.total);
        swipeLayout = (SwipeLayout)header.findViewById(R.id.swipe_layout);
        setSwipeViewFeatures();
        listView.addHeaderView(header);
    }

    private void setSwipeViewFeatures() {
        //set show mode.
        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        //add drag edge.(If the BottomView has 'layout_gravity' attribute, this line is unnecessary)
        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, rootView.findViewById(R.id.bottom_wrapper));

        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                Log.i(TAG, "onClose");
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                Log.i(TAG, "on swiping");
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {
                Log.i(TAG, "on start open");
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                Log.i(TAG, "the BottomView totally show");
            }

            @Override
            public void onStartClose(SwipeLayout layout) {
                Log.i(TAG, "the BottomView totally close");
            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });
    }

    private void setListViewAdapter(Context context) {
        adapter = new ListViewAdapter(context,mainActivity,inflater, R.layout.item_listview, friendsList);
        listView.setAdapter(adapter);

        totalClassmates.setText("(" + friendsList.size() + ")");
    }


}

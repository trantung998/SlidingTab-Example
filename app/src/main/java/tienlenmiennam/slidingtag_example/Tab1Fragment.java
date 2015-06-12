package tienlenmiennam.slidingtag_example;

/**
 * Created by Tran on 6/1/2015.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import facebook.FriendInfo;
import slidingtab.adapter.FacebookFriendAdapter;
import slidingtab.adapter.MyRecycleViewAdapter;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab1Fragment extends Fragment {

    private int previousTotal = 0;
    private static boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    int friendsListOffset = 0 ;
    static FacebookFriendAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tab1, container, false);

        final RecyclerView mRecyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(llm);

        adapter = new FacebookFriendAdapter();
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mRecyclerView.getChildCount();
                totalItemCount = llm.getItemCount();
                firstVisibleItem = llm.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                } else if ((totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleItemCount)) {
                    Log.i("...", "end called");
                    // Do something

                    loading = true;
                }
            }
        });

        return view;
    }

   public static void getFriends(int offset) {
        Tab2Fragment.request();
    }
    public static void clearList(){
        adapter.clear();
    }

    public static void updateFriendList(JSONArray friendsArray){

        for (int i = 0; i < friendsArray.length(); i++) {
            JSONObject obj = friendsArray.optJSONObject(i);
            FriendInfo info = new FriendInfo();

            info.setId(obj.optString("id"));
            info.setName(obj.optString("name"));
            info.setAge(21);

            adapter.update(info);
        }
        adapter.refresh();
        loading = false;
    }
}

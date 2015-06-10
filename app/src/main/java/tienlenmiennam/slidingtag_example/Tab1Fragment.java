package tienlenmiennam.slidingtag_example;

/**
 * Created by Tran on 6/1/2015.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import slidingtab.adapter.MyRecycleViewAdapter;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab1Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tab1, container, false);

        RecyclerView mRecyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(llm);

        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter();
        mRecyclerView.setAdapter(adapter);
        return view;
    }
}

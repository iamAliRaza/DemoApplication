package com.demoapp.View;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.demoapp.MainActivity;
import com.demoapp.Model.Article;
import com.demoapp.R;
import java.util.List;


public class ViewImplementer {

    private MainActivity activity;
    private SwipeRefreshLayout srl;
    private ListView listView;

    public ViewImplementer ( Context context ){

        activity = (MainActivity) context;
        activity.setContentView(R.layout.activity_main);

    }

    public void initViews (){

        listView = activity.findViewById(R.id.articleList);
        srl = activity.findViewById(R.id.str);

        srl.setOnRefreshListener(() -> {
            activity.getArticleRequest().getAllArticles();
            srl.setRefreshing(true);
        });

        listView.setOnItemClickListener(activity);

    }

    public void showList( List<Article> list ){

        listView.setAdapter(new ListAdapter(list));
    }

    public void showProgress(){

        srl.setRefreshing(true);
    }

    public void hideProgress(){

        srl.setRefreshing(false);
    }

    private class ListAdapter extends BaseAdapter {

        List<Article> articleList;

        ListAdapter (List<Article> list){

            articleList = list;
        }

        @Override
        public int getCount() {
            return articleList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;

            if ( convertView == null ){

                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.mainText.setText(articleList.get(position).getTitle());
            viewHolder.bottomText.setText(articleList.get(position).get_abstract());
            viewHolder.date.setText(articleList.get(position).getpDate());

            return convertView;
        }


        private class ViewHolder {

            TextView mainText;
            TextView bottomText;
            TextView date;

            ViewHolder ( View root ){

                mainText = root.findViewById(R.id.mainText);
                bottomText = root.findViewById(R.id.lowText);
                date = root.findViewById(R.id.date);
            }
        }
    }

}

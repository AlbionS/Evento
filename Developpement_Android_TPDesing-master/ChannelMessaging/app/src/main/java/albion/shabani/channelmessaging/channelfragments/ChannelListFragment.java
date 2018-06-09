package albion.shabani.channelmessaging.channelfragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

import albion.shabani.channelmessaging.Container.ChannelContainer;
import albion.shabani.channelmessaging.Acticity.ChannelListActivity;
import albion.shabani.channelmessaging.Adapter.CustomArrayAdapter;
import albion.shabani.channelmessaging.Acticity.FriendListActivity;
import albion.shabani.channelmessaging.Request.HttpPostHandler;
import albion.shabani.channelmessaging.Request.OnDownloadListener;
import albion.shabani.channelmessaging.Request.PostRequest;
import albion.shabani.channelmessaging.R;

import com.google.gson.Gson;
/**
 * Created by shabania on 02/03/2018.
 */
public class ChannelListFragment extends Fragment implements OnDownloadListener, View.OnClickListener {

    private ListView channels;
    public ChannelContainer getChannelList() {
        return channelList;
    }
    private ChannelContainer channelList;
    private Button friends;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_channellistfragment,container);
        channelList = new ChannelContainer();
        channels = (ListView) v.findViewById(R.id.listView);
        friends = (Button) v.findViewById(R.id.buttonFriends);
        return v;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        HttpPostHandler post = new HttpPostHandler();
        post.addOnDownloadListener(this);
        HashMap<String,String> hm = new HashMap<String,String>();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getView().getContext());
        hm.put("accesstoken",prefs.getString("AccessToken",""));

        post.setHttpPostHandler(this.getView().getContext());
        post.execute(new PostRequest("http://www.raphaelbischof.fr/messaging/?function=getchannels",hm));

        channels.setOnItemClickListener((ChannelListActivity)getActivity());
        friends.setOnClickListener(this);
    }


    @Override
    public void onDownloadComplete(String downloadedContent) {
        Gson gson = new Gson();
        channelList = gson.fromJson(downloadedContent, ChannelContainer.class);
        CustomArrayAdapter adapter = new CustomArrayAdapter(this.getView().getContext(),channelList.getChannels());
        channels.setAdapter(adapter);
    }

    @Override
    public void onDownloadError(String error) {
        Toast.makeText(this.getView().getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(this.getView().getContext(),FriendListActivity.class);
        startActivity(myIntent);
       // Toast.makeText(getActivity().getApplicationContext(), "le Tp 3 n'est pas fini lol!!!", Toast.LENGTH_SHORT).show();
    }

}

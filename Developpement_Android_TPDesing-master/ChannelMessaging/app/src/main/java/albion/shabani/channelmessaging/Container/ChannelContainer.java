package albion.shabani.channelmessaging.Container;

import java.util.ArrayList;

import albion.shabani.channelmessaging.Clases.Channel;

/**
 * Created by shabania on 26/02/2018.
 */
public class ChannelContainer {

    public ChannelContainer(){
        channels = new ArrayList<Channel>();
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }

    private ArrayList<Channel> channels;

}

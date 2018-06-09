package albion.shabani.channelmessaging.Clases;

/**
 * Created by shabania on 22/01/2018.
 */

public class Channel {
    private String channelID;
    private String name;
    private String connectedusers;

    public String getConnectedusers() {
        return connectedusers;
    }

    public void setConnectedusers(String connectedusers) {
        this.connectedusers = connectedusers;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

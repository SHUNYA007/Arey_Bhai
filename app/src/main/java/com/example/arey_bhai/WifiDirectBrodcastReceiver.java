package com.example.arey_bhai;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Parcelable;
import android.widget.Toast;

public class WifiDirectBrodcastReceiver extends BroadcastReceiver {
    private WifiP2pManager mManger;
    private WifiP2pManager.Channel mChannel;
    private MainActivity mActivity;

    public WifiDirectBrodcastReceiver(WifiP2pManager mManger,WifiP2pManager.Channel mChannel,MainActivity mActivity){
        this.mManger=mManger;
        this.mChannel=mChannel;
        this.mActivity=mActivity;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action= intent.getAction();

        if(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)){
            int state=intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE,-1);

            if(state==WifiP2pManager.WIFI_P2P_STATE_ENABLED){
                Toast.makeText(context,"WIFI IS ON",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context,"WIFI IS OFF",Toast.LENGTH_SHORT).show();
            }
        }
        else if(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals((action))) {
            if (mManger!=null) mManger.requestPeers(mChannel, mActivity.peerListListener);
        }
        else if(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)){
            if(mManger==null){

                return;
            }
            NetworkInfo networkInfo=intent.getParcelableArrayExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
        }
        else if(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)){
            //
        }
    }
}

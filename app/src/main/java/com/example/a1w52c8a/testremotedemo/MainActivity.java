package com.example.a1w52c8a.testremotedemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;

import com.google.android.tv.support.remote.R;
import com.google.android.tv.support.remote.core.Device;
import com.google.android.tv.support.remote.discovery.DeviceInfo;
import com.google.android.tv.support.remote.widget.DeviceListFragment;
import com.google.android.tv.support.remote.widget.PairingFragment;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements DeviceListFragment.Listener, PairingFragment.Listener,RemoteFrag.RemoteFramentInteractor {

    private static final String TAG = "RemoteDebug";
    private DeviceInfo deviceInfo;
    private DeviceListener deviceListener = new DeviceListener();
    private Device device;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout_main_activity, new DeviceListFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDevicesUpdated() {
        Log.i(TAG," onDevicesUpdated ");
    }

    @Override
    public void onDeviceSelected(DeviceInfo deviceInfo) {
        Log.i(TAG," onDeviceSelected "+deviceInfo);

        device = Device.from(this, deviceInfo, deviceListener, new Handler());
        this.deviceInfo = deviceInfo;
    }

    @Override
    public void onNoDevices() {
        Log.i(TAG," onNoDevices ");
    }

    @Override
    public void onNoConnectivity() {
        Log.i(TAG," onNoConnectivity ");
    }

    @Override
    public void onPairingCompleted(String s) {
        Log.i(TAG," onPairingCompleted ");
        device.setPairingSecret(s);
    }

    @Override
    public void onPairingCancelled() {
        Log.i(TAG," onPairingCancelled ");
    }

    @Override
    public void sendKeyEvent(int keyCode, int keyEvent) {
        device.sendKeyEvent(keyCode, keyEvent);
    }

    @Override
    public void sendAudioCommand() {

    }

    @Override
    public void stopAudioCommand() {

    }


    class DeviceListener extends Device.Listener {


        @Override
        public void onConnecting(Device device) {
            Log.i(TAG, "onConnecting");
        }

        @Override
        public void onConnected(Device device) {
            Log.i(TAG, "onConnected");
            MainActivity.this.device = device;
            openRemoteFragment();
        }

        @Override
        public void onConnectFailed(Device device) {
            Log.i(TAG, "onConnectFailed " + device);
        }

        @Override
        public void onDisconnected(Device device) {
            Log.i(TAG, "onDisconnected");
        }

        @Override
        public void onPairingRequired(Device device) {
            Log.i(TAG, "onPairingRequired");
            openPairingFragment();

        }

        @Override
        public void onShowIme(Device device, EditorInfo editorInfo, boolean b, ExtractedText extractedText) {
            Log.i(TAG, "onShowIme");
        }

        @Override
        public void onHideIme(Device device) {
            Log.i(TAG, "onHideIme");
        }

        @Override
        public void onCompletionInfo(Device device, CompletionInfo[] completionInfos) {
            Log.i(TAG, "onCompletionInfo");
        }

        @Override
        public void onStartVoice(Device device) {
            Log.i(TAG, "onStartVoice");
        }

        @Override
        public void onVoiceSoundLevel(Device device, int i) {
            Log.i(TAG, "onVoiceSoundLevel");
        }

        @Override
        public void onStopVoice(Device device) {
            Log.i(TAG, "onStopVoice");
        }

        @Override
        public void onConfigureSuccess(Device device) {
            Log.i(TAG, "onConfigureSuccess");
        }

        @Override
        public void onConfigureFailure(Device device, int i) {
            Log.i(TAG, "onConfigureFailure");
        }

        @Override
        public void onException(Device device, Exception e) {
            Log.i(TAG, "onException "+e);
        }

        @Override
        public void onDeveloperStatus(Device device, boolean b) {
            Log.i(TAG, "onDeveloperStatus");
        }

        @Override
        public void onBugReportStatus(Device device, int i) {
            Log.i(TAG, "onBugReportStatus");
        }

        @Override
        public void onAsset(Device device, String s, Map<String, String> map, byte[] bytes) {
            Log.i(TAG, "onAsset");
        }
    }


    private void openPairingFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout_main_activity, new PairingFragment())
                .commit();
    }


    private void openRemoteFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout_main_activity, new RemoteFrag())
                .commit();
    }
}

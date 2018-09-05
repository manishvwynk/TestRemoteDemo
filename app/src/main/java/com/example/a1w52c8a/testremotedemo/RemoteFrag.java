package com.example.a1w52c8a.testremotedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.tv.support.remote.R;

public class RemoteFrag extends Fragment implements View.OnClickListener {


    private   RemoteFramentInteractor listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

            listener = (RemoteFramentInteractor)context ;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote, container, false);

//        initView(view)

        view.findViewById(R.id.btn_volume_up).setOnClickListener(this);
        view.findViewById(R.id.btn_volume_down).setOnClickListener(this);
        view.findViewById(R.id.up).setOnClickListener(this);
        view.findViewById(R.id.down).setOnClickListener(this);
        view.findViewById(R.id.leftBtn).setOnClickListener(this);
        view.findViewById(R.id.rightBtn).setOnClickListener(this);




        return view;
    }





    private void initView( View view) {
//        view.leftBtn.setOnClickListener(this);
//        view.rightBtn.setOnClickListener(this);
//        view.up.setOnClickListener(this)
//        view.down.setOnClickListener(this)
//        view.ok.setOnClickListener(this)
//        view.back.setOnClickListener(this)
//        view.home.setOnClickListener(this)
//        view.imgv_mic.setOnClickListener(this)
//        view.btn_volume_up.setOnClickListener(this)
//        view.btn_volume_down.setOnClickListener(this)
//        view.btn_netflix.setOnClickListener(this)
//        view.btn_youtube.setOnClickListener(this)
    }



    private void leftClicked() {

        sendEvent(KeyEvent.KEYCODE_TV_POWER);
    }

    private void rightClicked() {
        sendEvent(KeyEvent.KEYCODE_POWER);
    }

    private void upClicked() {
        sendEvent(KeyEvent.KEYCODE_POWER);
    }

    private void downClicked() {
        sendEvent(KeyEvent.KEYCODE_DPAD_DOWN);
    }


    private void volumeUpClicked() {
        sendEvent(KeyEvent.KEYCODE_TV_POWER);
    }

    private void volumeDownClicked() {
        sendEvent(KeyEvent.KEYCODE_VOLUME_DOWN);
    }




    private void sendEvent( int keyCode) {
        listener.sendKeyEvent(keyCode, KeyEvent.ACTION_DOWN);
        listener.sendKeyEvent(keyCode, KeyEvent.ACTION_UP);
    };

    @Override
    public void onClick(View view) {
      int id=   view.getId();

      switch (id){
          case  R.id.up:
              upClicked();
              break;

          case  R.id.down:

              downClicked();
              break;

          case  R.id.leftBtn:
              leftClicked() ;
              break;

          case  R.id.rightBtn:
              rightClicked();
              break;
          case  R.id.btn_volume_up:
              volumeDownClicked();
              break;

          case  R.id.btn_volume_down:
              volumeDownClicked();
              break;



      }
    }

    interface RemoteFramentInteractor {
        void sendKeyEvent(int keyCode,int keyEvent);

        void sendAudioCommand();

        void stopAudioCommand();
    }
}

package com.example.a1w52c8a.testremotedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.tv.support.remote.R;

public class RemoteFrag extends Fragment implements View.OnClickListener,View.OnKeyListener {


    private   RemoteFramentInteractor listener;
    private boolean isSpeaking;
          private LinearLayout keyboardTextContainer;
          private EditText keyboardText;


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

        view.findViewById(R.id.ok).setOnClickListener(this);


        view.findViewById(R.id.home).setOnClickListener(this);
        view.findViewById(R.id.back).setOnClickListener(this);
        view.findViewById(R.id.back).setOnClickListener(this);
        view.findViewById(R.id.voice).setOnClickListener(this);
        view.findViewById(R.id.play).setOnClickListener(this);
        view.findViewById(R.id.power_off).setOnClickListener(this);// power off
        view.findViewById(R.id.keyboard).setOnClickListener(this); // keyboard

        initView(view);

//





        return view;
    }





    private void initView( View view) {
//

        keyboardTextContainer =   view.findViewById(R.id.keyboard_container);
        keyboardText = view.findViewById(R.id.keyboard_edittext);
        keyboardText.setOnKeyListener(this);

        ((SeekBar)(view.findViewById(R.id.seekBar))).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("mvv","onStopTrackingTouch  "+seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("mvv","onStartTrackingTouch  "+seekBar.getProgress());
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.d("mvv","onProgressChanged  "+progress);

//                t1.setTextSize(progress);
                Toast.makeText(getActivity(), String.valueOf(progress), Toast.LENGTH_LONG).show();

            }
        });
    }



    private void leftClicked() {

        sendEvent(KeyEvent.KEYCODE_DPAD_UP_LEFT);
    }

    private void rightClicked() {
        sendEvent(KeyEvent.KEYCODE_DPAD_UP_RIGHT);
    }

    private void upClicked() {
        sendEvent(KeyEvent.KEYCODE_DPAD_UP);
    }

    private void downClicked() {
        sendEvent(KeyEvent.KEYCODE_DPAD_DOWN);
    }


    private void volumeUpClicked() {
        sendEvent(KeyEvent.FLAG_SOFT_KEYBOARD);
    }


    private void ok() {
        sendEvent(KeyEvent.KEYCODE_DPAD_CENTER);
    }

    private void volumeDownClicked() {
        sendEvent(KeyEvent.KEYCODE_VOLUME_DOWN);
    }


    private void powerOff() {
        sendEvent(KeyEvent.KEYCODE_POWER);
    }


    private void KeyBoardClick() {

        keyboardTextContainer.setVisibility(View.VISIBLE);
        InputMethodManager imm = (InputMethodManager) getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

    }


    private void back() {
        sendEvent(KeyEvent.KEYCODE_BACK);
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
              volumeUpClicked();
              break;

          case  R.id.btn_volume_down:
              volumeDownClicked();

          case  R.id.back:
              back();
              break;
          case  R.id.power_off:
              powerOff();
              break;

          case  R.id.keyboard:
              KeyBoardClick();
              break;

          case  R.id.voice:
              micClick();


          case  R.id.play:
              play();
              break;
          case  R.id.ok:
              ok();
              break;

//          case  R.id.seekBar:
//              micClick();
//              break;



      }
    }


    void micClick(){
        if (isSpeaking) {
            listener.stopAudioCommand();
            isSpeaking = false;
        } else {
            listener.sendAudioCommand();
            isSpeaking = true;
        }
    }


    void play(){
        sendEvent(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
    }


    void keyclick(int code){
        sendEvent(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
    }
    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Log.i("mvv"," keyboard keyEvent ::: "+keyEvent+ " eyEvent.getCharacters() "+keyEvent.getCharacters()+" key code"+keyEvent.getKeyCode());


        keyboardText.setText(keyEvent.getCharacters());

        keyclick(keyEvent.getKeyCode());

        Log.i("mvv"," keyboard keyEvent ::: "+keyEvent+ " eyEvent.getCharacters() "+keyEvent.getCharacters());
        Toast.makeText(getActivity(),keyEvent.getCharacters(), Toast.LENGTH_SHORT).show();

        return false;
    }

    interface RemoteFramentInteractor {
        void sendKeyEvent(int keyCode,int keyEvent);

        void sendAudioCommand();

        void stopAudioCommand();
    }

}

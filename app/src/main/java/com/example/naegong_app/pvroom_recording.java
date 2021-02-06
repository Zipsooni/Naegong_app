// 카메라 전환 해야됨

package com.example.naegong_app;

import android.Manifest;
import android.app.Activity;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class pvroom_recording extends Activity implements SurfaceHolder.Callback {

    private android.hardware.Camera camera;
    private MediaRecorder mediaRecorder;

    private Button btn_record; // 녹화 버튼
    private Button btn_facing; //카메라 전환 버튼
    private int currentCameraId;

    private SurfaceView surfaceView; // 카메라 미리보기 화면
    private SurfaceHolder surfaceHolder;

    private boolean recording = false;
    private String button_s;
    private String filename;



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try{
            if (camera == null){
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            }
        } catch (IOException e){
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // view가 존재하지 않을 때
        if (surfaceHolder.getSurface() == null) {
            return;
        }

        //작업을 위해 잠시 멈춤
        try {
            camera.stopPreview();
        }
        catch (Exception e) {
            // 에러 나도 무시
        }

        Camera.Parameters parameters = camera.getParameters();
        List<String> focusModes = parameters.getSupportedFocusModes();
        if (focusModes.contains(Camera.Parameters.FLASH_MODE_AUTO)){
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }
        camera.setParameters(parameters);

        //view를 재생성한다
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        }
        catch (Exception e) {
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pvroom_studying);


        btn_facing = (Button)findViewById(R.id.btn_facing);
        btn_facing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(pvroom_recording.this, "Clicked", Toast.LENGTH_SHORT).show();
                // 전면 -> 후면 or 후면 -> 전면으로 카메라 상태 전환
                currentCameraId = (currentCameraId==Camera.CameraInfo.CAMERA_FACING_BACK) ?
                        Camera.CameraInfo.CAMERA_FACING_FRONT
                        : Camera.CameraInfo.CAMERA_FACING_BACK;

            }
        });

        // 카메라 미리보기 세팅
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        camera = Camera.open(currentCameraId);
        camera.setDisplayOrientation(90);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(pvroom_recording.this);
        surfaceHolder.setType(surfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        TedPermission.with(this)
                .setPermissionListener(permission)
                .setRationaleMessage("녹화를 위해 권한을 허용해주세요.")
                .setDeniedMessage("권한이 거부되었습니다. 설정 > 권한에서 허용해주세요.")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO).check();

        //시간으로 파일명 생성
        long time = System.currentTimeMillis();  //시간 받기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //포멧 변환  형식 만들기
        Date dd = new Date(time);  //받은 시간을 Date 형식으로 바꾸기
        String strTime = sdf.format(dd); //Data 정보를 포멧 변환하기

        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        filename = sdcard + "/DCIM/Camera/" + strTime + ".mp4";

        btn_record = (Button)findViewById(R.id.btn_record);
        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭 시 record와 stop으로 나타냄
                button_s = btn_record.getText().toString();
                btn_record.setText("Stop");
                if(button_s == "Stop"){
                    btn_record.setText("Record");
                }

                if (recording){
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    camera.lock();
                    recording = false;

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(pvroom_recording.this, "녹화가 시작되었습니다.", Toast.LENGTH_SHORT).show();
                            try{
                                mediaRecorder = new MediaRecorder();
                                camera.unlock();
                                mediaRecorder.setCamera(camera); //카메라 등록
                                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER); //녹화 시 효과음
                                mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA); // 비디오 source를 카메라에 넣어준다
                                mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P)); // 동영상 녹화 화질
                                mediaRecorder.setOrientationHint(90); // 촬영 각도 맞추기
                                mediaRecorder.setOutputFile(filename); // 저장 경로
                                mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface()); // 실제로 보이는 미리보기 화면
                                mediaRecorder.prepare();
                                mediaRecorder.start();
                                recording = true; // 리코딩 진행
                            } catch (Exception e){
                                Toast.makeText(pvroom_recording.this, "녹화가 중지되었습니다.", Toast.LENGTH_SHORT).show();
                                Toast.makeText(pvroom_recording.this, "녹화 영상은 갤러리에 저장됩니다.", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                mediaRecorder.release(); // 촬영 종료

                            }
                        }
                    });
                }
            }
        });



    }


    PermissionListener permission = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(pvroom_recording.this, "권한 허가", Toast.LENGTH_SHORT).show();

//            if(currentCameraId==Camera.CameraInfo.CAMERA_FACING_BACK) {
//                currentCameraId=Camera.CameraInfo.CAMERA_FACING_FRONT; }

//            camera = Camera.open(currentCameraId);
//            camera.setDisplayOrientation(90);
//            surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
//            surfaceHolder = surfaceView.getHolder();
//            surfaceHolder.addCallback(pvroom_recording.this);
//            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(pvroom_recording.this, "권한 거부", Toast.LENGTH_SHORT).show();
        }
    };
}
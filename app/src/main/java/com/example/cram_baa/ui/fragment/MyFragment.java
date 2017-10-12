package com.example.cram_baa.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cram_baa.Entity.Setting;
import com.example.cram_baa.R;
import com.example.cram_baa.ui.activity.PersonalDataActivity;
import com.example.cram_baa.util.DBOperation;
import com.example.cram_baa.view.TakePhotoPopWin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/17 0017.
 * ‘我的’页面
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    public static MyFragment fragment;
    private ImageView iv_head,iv_personal_data;
    private RelativeLayout call;
    public TextView telephone_number,tv_name;
    public final int PIC_FROM_CAMERA = 1;
    public final int PIC_FROM＿LOCALPHOTO = 0;
    public Uri photoUri;
    public Setting setting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragment = this;
        initView();
    }

    public void initView() {
        setting = getSettingData();//初始化数据库
        call = (RelativeLayout) getView().findViewById(R.id.call);
        iv_head = (ImageView) getView().findViewById(R.id.iv_head);
        tv_name= (TextView) getView().findViewById(R.id.tv_name);
        tv_name.setText(setting.getName());
        //获取图片
        getPicture();
        telephone_number = (TextView) getView().findViewById(R.id.telephone_number);
        iv_personal_data = (ImageView) getView().findViewById(R.id.iv_personal_data);
        call.setOnClickListener(this);
        iv_head.setOnClickListener(this);
        iv_personal_data.setOnClickListener(this);
    }

    public void getPicture(){
        File pictureFileDir = new File(Environment.getExternalStorageDirectory(), "/CramBaa");
        if (!pictureFileDir.exists()) {
            pictureFileDir.mkdirs();
        }
        File picFile = new File(pictureFileDir, "head.jpeg");
        if (picFile.exists()) {
            photoUri = Uri.fromFile(picFile);
            Bitmap bitmap = decodeUriAsBitmap(photoUri);
            iv_head.setImageBitmap(createCircleImage(bitmap));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head:
                TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(getActivity(), onClickListener, 0);
                takePhotoPopWin.showAtLocation(getView().findViewById(R.id.main_view), Gravity.BOTTOM, 0, 0);//125
                break;
            case R.id.call:
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telephone_number.getText().toString())));//拨打电话
                break;
            case R.id.iv_personal_data:
                Intent intent = new Intent(getActivity(), PersonalDataActivity.class);
                intent.putExtra("Setting", (Serializable) setting);
                startActivity(intent);
                break;
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
//                case R.id.tv_huowei:
//                    System.out.println("fewafwaefwea--------------------------------------");
//                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PIC_FROM_CAMERA: // 拍照
                try {
                    cropImageUriByTakePhoto();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case PIC_FROM＿LOCALPHOTO:
                try {
                    if (photoUri != null) {
                        Bitmap bitmap = decodeUriAsBitmap(photoUri);
                        iv_head.setImageBitmap(createCircleImage(bitmap));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * 将图片剪裁为圆形
     */
    public static Bitmap createCircleImage(Bitmap source) {
        int length = source.getWidth() < source.getHeight() ? source.getWidth() : source.getHeight();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(length, length, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(length / 2, length / 2, length / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }


    /**
     * 启动裁剪
     */
    private void cropImageUriByTakePhoto() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(photoUri, "image/*");
        setIntentParams(intent);
        startActivityForResult(intent, PIC_FROM＿LOCALPHOTO);
    }

    /**
     * 设置公用参数
     */
    private void setIntentParams(Intent intent) {
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);
        intent.putExtra("noFaceDetection", true); // no face detection
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    private void doHandlerPhoto(int type) {
        try {
            //保存裁剪后的图片文件
            File pictureFileDir = new File(Environment.getExternalStorageDirectory(), "/CramBaa");
            if (!pictureFileDir.exists()) {
                pictureFileDir.mkdirs();
            }
            File picFile = new File(pictureFileDir, "head.jpeg");
            if (!picFile.exists()) {
                picFile.createNewFile();
            }
            photoUri = Uri.fromFile(picFile);

            if (type == PIC_FROM＿LOCALPHOTO) {
                Intent intent = getCropImageIntent();
                startActivityForResult(intent, PIC_FROM＿LOCALPHOTO);
            } else {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(cameraIntent, PIC_FROM_CAMERA);
            }

        } catch (Exception e) {
            Log.i("HandlerPicError", "处理图片出现错误");
        }
    }

    /**
     * 调用图片剪辑程序
     */
    public Intent getCropImageIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        setIntentParams(intent);
        return intent;
    }

    //相册
    public void photoAlbum() {
        doHandlerPhoto(PIC_FROM＿LOCALPHOTO);
    }

    //拍照
    public void photograph() {
        doHandlerPhoto(PIC_FROM_CAMERA);
    }

    public Setting getSettingData() {
        DBOperation dboperation = new DBOperation(getActivity().getApplicationContext());
        Setting setting = dboperation.querySet();
        if (setting.getID() == null || setting.getName() == null || setting.getSex() == null
                || setting.getRegion() == null || setting.getAge() == null || setting.getSchool() == null || setting.getGrade() == null
                || setting.getClbum() == null) {
            Setting newset = new Setting();
            newset.setName("未填写");
            newset.setSex("未填写");
            newset.setRegion("未填写");
            newset.setAge("未填写");
            newset.setSchool("未填写");
            newset.setGrade("未填写");
            newset.setClbum("未填写");
            dboperation.insertSet(newset);
            setting = dboperation.querySet();
        }
        return setting;
    }
}

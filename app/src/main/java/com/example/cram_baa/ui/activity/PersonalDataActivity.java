package com.example.cram_baa.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cram_baa.Entity.Setting;
import com.example.cram_baa.R;
import com.example.cram_baa.ui.fragment.MyFragment;
import com.example.cram_baa.util.DBOperation;
import com.example.cram_baa.view.TakePhotoPopWin;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2017/10/11 0011.
 * 个人资料页面
 */
public class PersonalDataActivity extends BaseActivity implements View.OnClickListener {
    public static PersonalDataActivity personalData;
    private RelativeLayout rl_heard,rl_rwm,rl_background,rl_name,rl_sex,rl_region,rl_age,rl_school,rl_class,rl_clbum;
    private TextView tv_name,tv_sex,tv_region,tv_age,tv_school,tv_class,tv_clbum;
    private ImageView iv_head;
    public final int PIC_FROM_CAMERA = 1;
    public final int PIC_FROM＿LOCALPHOTO = 0;
    public Uri photoUri;
    private Setting setting;//保存设置字段实体类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        //
        personalData=this;
        setting = (Setting) this.getIntent().getSerializableExtra("Setting");
        initView();
        setSettingData(setting);
    }

    public void getPicture(){
        File pictureFileDir = new File(Environment.getExternalStorageDirectory(), "/CramBaa");
        if (!pictureFileDir.exists()) {
            pictureFileDir.mkdirs();
        }
        File picFile = new File(pictureFileDir, "head.jpg");
        if (picFile.exists()) {
            photoUri = Uri.fromFile(picFile);
            Bitmap bitmap = decodeUriAsBitmap(photoUri);
            iv_head.setImageBitmap(bitmap);
        }
    }

    private void initView() {
        iv_head= (ImageView) findViewById(R.id.iv_head);
        TileActivity tileActivity=new TileActivity(getWindow().getDecorView());
        tileActivity.tv_title.setText("个人资料");
        tileActivity.btn_right.setVisibility(View.GONE);
        tileActivity.iv_left.setImageResource(R.drawable.back);
        tileActivity.tv_left.setVisibility(View.INVISIBLE);
        tileActivity.line_left.setOnClickListener(this);
        getPicture();

        tv_name= (TextView) findViewById(R.id.tv_name);
        tv_sex= (TextView) findViewById(R.id.tv_sex);
        tv_region= (TextView) findViewById(R.id.tv_region);
        tv_age= (TextView) findViewById(R.id.tv_age);
        tv_school= (TextView) findViewById(R.id.tv_school);
        tv_class= (TextView) findViewById(R.id.tv_class);
        tv_clbum= (TextView) findViewById(R.id.tv_clbum);

        rl_heard= (RelativeLayout) findViewById(R.id.rl_heard);
        rl_rwm= (RelativeLayout) findViewById(R.id.rl_rwm);
        rl_background= (RelativeLayout) findViewById(R.id.rl_background);
        rl_name= (RelativeLayout) findViewById(R.id.rl_name);
        rl_sex= (RelativeLayout) findViewById(R.id.rl_sex);
        rl_region= (RelativeLayout) findViewById(R.id.rl_region);
        rl_age= (RelativeLayout) findViewById(R.id.rl_age);
        rl_school= (RelativeLayout) findViewById(R.id.rl_school);
        rl_class= (RelativeLayout) findViewById(R.id.rl_class);
        rl_clbum= (RelativeLayout) findViewById(R.id.rl_clbum);

        rl_heard.setOnClickListener(this);
        rl_rwm.setOnClickListener(this);
        rl_background.setOnClickListener(this);
        rl_name.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_region.setOnClickListener(this);
        rl_age.setOnClickListener(this);
        rl_school.setOnClickListener(this);
        rl_class.setOnClickListener(this);
        rl_clbum.setOnClickListener(this);
    }

    public void setSettingData(Setting newset){
        tv_name.setText(newset.getName());
        tv_sex.setText(newset.getSex());
        tv_region.setText(newset.getRegion());
        tv_age.setText(newset.getAge());
        tv_school.setText(newset.getSchool());
        tv_class.setText(newset.getGrade());
        tv_clbum.setText(newset.getClbum());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.line_left:
                insertDataBase();//把保存的数据存入数据库
                MyFragment.fragment.setting=setting;//同步两边的setting
                MyFragment.fragment.tv_name.setText(setting.getName());
                MyFragment.fragment.getPicture();
                finish();
                break;
            case R.id.rl_heard:
                TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(this, onClickListener, 1);//第三个参数1是标志位，用于区分是从哪个页面进入的
                takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM, 0, 0);//125
                break;
            case R.id.rl_rwm:
                break;
            case R.id.rl_background:
                break;
            case R.id.rl_name:
                showEditDialog("姓名", tv_name.getText().toString(), tv_name);
                break;
            case R.id.rl_sex:
                showEditDialog("性别", tv_sex.getText().toString(), tv_sex);
                break;
            case R.id.rl_region:
                showEditDialog("地区", tv_region.getText().toString(), tv_region);
                break;
            case R.id.rl_age:
                showEditDialog("年龄", tv_age.getText().toString(), tv_age);
                break;
            case R.id.rl_school:
                showEditDialog("学校", tv_school.getText().toString(), tv_school);
                break;
            case R.id.rl_class:
                showEditDialog("年级", tv_class.getText().toString(), tv_class);
                break;
            case R.id.rl_clbum:
                showEditDialog("班级", tv_clbum.getText().toString(), tv_clbum);
                break;
        }
    }

    //保存数据的方法
    public void insertDataBase() {
        //将数据先保存到实体类
        setting.setName(tv_name.getText().toString());
        setting.setSex(tv_sex.getText().toString());
        setting.setRegion(tv_region.getText().toString());
        setting.setAge(tv_age.getText().toString());
        setting.setSchool(tv_school.getText().toString());
        setting.setGrade(tv_class.getText().toString());
        setting.setClbum(tv_clbum.getText().toString());

        DBOperation dboperation = new DBOperation(this);
        dboperation.deleteSet();
        dboperation.insertSet(setting);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
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
                        iv_head.setImageBitmap(bitmap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
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

    //相册
    public void photoAlbum() {
        doHandlerPhoto(PIC_FROM＿LOCALPHOTO);
    }

    //拍照
    public void photograph() {
        doHandlerPhoto(PIC_FROM_CAMERA);
    }

    private void doHandlerPhoto(int type) {
        try {
            //保存裁剪后的图片文件
            File pictureFileDir = new File(Environment.getExternalStorageDirectory(), "/CramBaa");
            if (!pictureFileDir.exists()) {
                pictureFileDir.mkdirs();
            }
            File picFile = new File(pictureFileDir, "head.jpg");
            if (!picFile.exists()) {
                picFile.createNewFile();
            }
            photoUri = Uri.fromFile(picFile);

            if (type == PIC_FROM＿LOCALPHOTO) {//相册
                Intent intent = getCropImageIntent();
                startActivityForResult(intent, PIC_FROM＿LOCALPHOTO);
            } else {//拍照
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

    public void showEditDialog(String title, String edit, final View v) {
        final EditText et = new EditText(this);
        if (!edit.equals("未填写")){
            et.setText(edit);
            et.setSelection(edit.length());//将光标定位到最后
        }
        new AlertDialog.Builder(this).
                // 设置标题
                 setTitle(title).
                // 添加输入的文本框
                setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        ((TextView) v).setText(et.getText());
                        //点击确定后隐藏键盘
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点击取消后隐藏键盘
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                })
                .create().show();
    }
}

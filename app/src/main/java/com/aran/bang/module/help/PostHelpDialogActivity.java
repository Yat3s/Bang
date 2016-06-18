package com.aran.bang.module.help;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;

import com.aran.bang.R;
import com.aran.bang.base.BaseActivity;
import com.aran.bang.module.UserModel;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joanzapata.iconify.widget.IconTextView;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by Yat3s on 6/17/16.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class PostHelpDialogActivity extends BaseActivity {
    private static final int SELECT_ORIGINAL_PIC = 0x0010;
    @Bind(R.id.upload_image)
    SimpleDraweeView uploadImage;
    @Bind(R.id.plus_icon)
    IconTextView plusIcon;
    @Bind(R.id.title_et)
    EditText titleEt;
    @Bind(R.id.description_et)
    EditText descriptionEt;
    private String mUploadImageUrl;
    private boolean isUpload;

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_post_help;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    private void selectFromGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);//Pick an item fromthe data
        intent.setType("image/*");//从所有图片中进行选择
        startActivityForResult(intent, SELECT_ORIGINAL_PIC);
    }

    private void uploadImage(File image) {
        final BmobFile bmobFile = new BmobFile(image);
        isUpload = true;
        bmobFile.uploadblock(this, new UploadFileListener() {

            @Override
            public void onSuccess() {
                mUploadImageUrl = bmobFile.getFileUrl(PostHelpDialogActivity.this);
                isUpload = false;
            }

            @Override
            public void onProgress(Integer value) {
                showToast(value + "%");
            }

            @Override
            public void onFailure(int code, String msg) {
                showToast("上传文件失败：" + msg);
                isUpload = false;
            }
        });
    }

    private void postHelp() {
        if (isUpload) {
            showToast("Wait a moment beautiful girl!");
            return;
        }
        final UserModel userModel = BmobUser.getCurrentUser(this, UserModel.class);
        final HelpModel helpModel = new HelpModel();
        helpModel.setHost(userModel);
        helpModel.image = mUploadImageUrl;
        helpModel.title = titleEt.getText().toString();
        helpModel.description = descriptionEt.getText().toString();

        helpModel.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                BmobRelation helps = new BmobRelation();
                helps.add(helpModel);
                userModel.helps = helps;
                userModel.update(PostHelpDialogActivity.this);
                showToast("Post Success!");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }

    @OnClick({R.id.upload_image, R.id.post_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upload_image:
                selectFromGallery();
                break;
            case R.id.post_btn:
                postHelp();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_ORIGINAL_PIC:
                if (resultCode == RESULT_OK) {//从相册选择照片不裁切
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        uploadImage.setImageURI(selectedImage);
                        plusIcon.setVisibility(View.GONE);

                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        uploadImage(new File(picturePath));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}

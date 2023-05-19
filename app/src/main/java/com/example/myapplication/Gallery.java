package com.example.myapplication;


import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;

import android.widget.ImageView;

import android.widget.Toast;

import com.yalantis.ucrop.UCrop;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Gallery extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private static final int REQUEST_GALLERY = 201;
    private static final int REQUEST_CAMERA = 202;
    private static final int REQUEST_CROP = 203;

    ImageView imageView;
//    ImageView imageView2;
    AppCompatButton captureButton;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        imageView = findViewById(R.id.G_image);
//        imageView2 = findViewById(R.id.G_image2);
        captureButton = findViewById(R.id.G_button);

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCameraPermission()) {
                    openMediaSelectionDialog();
                } else {
                    requestCameraPermission();
                }
            }
        });
    }

    private boolean checkCameraPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = createImageFile();
            if (photoFile != null) {
                imageUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        }
    }

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES);
        try {
            File imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
            return imageFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    private void openMediaSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Image Source");
        builder.setItems(new CharSequence[]{"Camera", "Gallery"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    openCamera();
                } else {
                    openGallery();
                }
            }
        });
        builder.show();
    }

    private void startCropActivity(Uri sourceUri) {
        String destinationFileName = "crop_image";
        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setCompressionQuality(90);
        UCrop.of(sourceUri, Uri.fromFile(new File(getCacheDir(), destinationFileName)))
                .withOptions(options)
                .start(this, REQUEST_CROP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GALLERY) {
                Uri selectedImage = data.getData();
                if (selectedImage != null) {
                    String imagePath = getRealPathFromURI(selectedImage);
//                    imageView2.setImageURI(imageUri);
                    if (imagePath != null) {
                        File imageFile = new File(imagePath);
                        startCropActivity(Uri.fromFile(imageFile));
                        imageUri = UCrop.getOutput(data);
//                        imageView2.setImageURI(imageUri);
                        imageView.setImageURI(imageUri);
                    }
                }
            } else if (requestCode == REQUEST_CAMERA) {
                if (imageUri != null) {
                    startCropActivity(imageUri);
//                    imageView2.setImageURI(imageUri);
                    imageView.setImageURI(imageUri);
                }
            } else if (requestCode == REQUEST_CROP) {
                imageUri = UCrop.getOutput(data);
                if (imageUri != null) {
//                    imageView2.setImageURI(imageUri);
                    imageView.setImageURI(imageUri);

                }

            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            Toast.makeText(this, "Error cropping image", Toast.LENGTH_SHORT).show();
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(columnIndex);
            cursor.close();
            return path;
        }
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openMediaSelectionDialog();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
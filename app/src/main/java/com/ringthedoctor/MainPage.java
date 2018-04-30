package com.ringthedoctor;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainPage extends AppCompatActivity  {




    GridView grid;
    String[] web = {
            "Book Appointment",
            "Call Ambulance",
            "PharmacyActivity",
            "Health Tips",
            "Blood",
            "Hospitals"

    } ;
    int[] imageId = {
            R.drawable.appointment_,
            R.drawable.ambulance_,
            R.drawable.medicine_reminder,
            R.drawable.health_tips,
            R.drawable.blood_,
            R.drawable.hospital_


    };

    FirebaseDatabase database=FirebaseDatabase.getInstance();



    ImageView profileUserImage;

    public static final String MY_PREFS_NAME = "USERNAME";



    StorageReference mStorageRef;

    ImageView imageViewedit;
    String[] PERMISSIONS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        TextView nam = (TextView)findViewById(R.id.name);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String username = prefs.getString("name", "");
        if (!username.equals(""))
        {
            nam.setText(username);
        }
        else {
            nam.setText("Patient");
        }


        mStorageRef = FirebaseStorage.getInstance().getReference();


        profileUserImage = (CircleImageView)findViewById(R.id.profile);
        imageViewedit = (ImageView)findViewById(R.id.edit);
        imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PERMISSIONS = new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA};


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(!hasPermissions(MainPage.this,PERMISSIONS)) {
                        ActivityCompat.requestPermissions(MainPage.this,
                                PERMISSIONS,
                                231);
                    }
                }
                showDialog();
            }
        });

        setprofiledata();
        CustomGrid adapter = new CustomGrid(MainPage.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position==0)
                {

                    startActivity(new Intent(MainPage.this, Categories.class));
//                    MainPage.this.finish();
                }

                if (position==1)
                {

                    startActivity(new Intent(MainPage.this, CallAmbulance.class));

                }

                if (position==2)
                {

                    startActivity(new Intent(MainPage.this, PharmacyActivity.class));

                }

                if (position==3)
                {

                    startActivity(new Intent(MainPage.this, Diet_Plan.class));

                }
                if (position==4)
                {

                    startActivity(new Intent(MainPage.this, BloodActivity.class));

                }

                if (position==5)
                {

                    startActivity(new Intent(MainPage.this, HospitalActivity.class));
//                    MainPage.this.finish();
                }

            }
        });


    }



    private void showDialog()
    {
        final Dialog selectimage = new Dialog(this);

        selectimage.setContentView(R.layout.dialog_box);

        selectimage.setTitle("Add New Photo");

        final Button camera = (Button) selectimage.findViewById(R.id.camera);
        final Button gallery = (Button) selectimage.findViewById(R.id.gallery);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 0);
                selectimage.dismiss();

            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
                selectimage.dismiss();
            }
        });

        // AlertDialog alertDialogAndroid = selectimage.create();
        selectimage.show();


    }

    public boolean hasPermissions(Context context, String...permissions){
        for(String permission:permissions){
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 231: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

//                    Toast.makeText(MainPage.this,"Permission Granted ",Toast.LENGTH_LONG).show();

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
//                    Toast.makeText(MainPage.this,"Permission Denied ",Toast.LENGTH_LONG).show();

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = null;
            String path="";
            File file;


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N || Build.VERSION.SDK_INT==Build.VERSION_CODES.KITKAT || Build.VERSION.SDK_INT==23){

                //   Toast.makeText(getContext(),"Nougat",Toast.LENGTH_LONG).show();

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                        "/PhysicsSketchpad";
                File dir = new File(file_path);
                if(!dir.exists())
                    dir.mkdirs();

                File filee = new File(dir, System.currentTimeMillis()+".png");
                FileOutputStream fOut = null;
                try {
                    fOut = new FileOutputStream(filee);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, fOut);
                    fOut.flush();
                    fOut.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                path=filee.getPath();
                file=new File(path);


            }else{

                path= compressImage(data.getDataString());
                file=new File(path);
            }





            photo = BitmapFactory.decodeFile(file.getAbsolutePath());
            profileUserImage.setImageBitmap(photo);



            upload(path);


        }


        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            Bitmap photo = null;


            String path= compressImage(data.getDataString());
            File file=new File(path);
            Log.d("Path File ",data.getDataString()+"   "+path);
            try {
                //photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());

                photo = BitmapFactory.decodeFile(file.getAbsolutePath());

            }   catch (Exception e)
            {
                e.printStackTrace();
            }


            profileUserImage.setImageBitmap(photo);

            upload(path);
        }



    }



    public void setprofiledata ()
    {
        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        final DatabaseReference myRef = database.getReference("users/"+user.getUid());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String result;

                DataSnapshot propertiesRef;

                if (dataSnapshot.child("image").exists()) {
                    propertiesRef = dataSnapshot.child("image");
                    result = propertiesRef.getValue(String.class);
                    Log.d("TAG", "User Name is: " + result);

                    if (!result.isEmpty()) {
                        Log.d("URL Adpater  not  ", result);

                        Picasso.with(getBaseContext())
                                .load(result)
                                .placeholder(R.drawable.man)
                                .error(R.drawable.man)
                                .into(profileUserImage);   //setting resourced to image

                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    private String upload(String path)
    {
        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        final DatabaseReference myRef = database.getReference("users/"+user.getUid());


        //  Toast.makeText(this,key+"   "+ images.size(),Toast.LENGTH_SHORT).show();
        // if(i==images.size())
        {
            //  Toast.makeText(this,"End ",Toast.LENGTH_SHORT).show();

        }
        final String[] url = new String[1];
        Uri file = Uri.fromFile(new File(path));
        StorageReference riversRef = mStorageRef.child(file.getLastPathSegment()+"/image.jpg");

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // taskSnapshot.getDownloadUrl();


                        // Toast.makeText(SellDetails.this,"Success ",Toast.LENGTH_SHORT).show();
                        //   count=count+1;
                        @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        //  Toast.makeText(SellDetails.this,"Success  "+downloadUrl,Toast.LENGTH_SHORT).show();
                        url[0] =downloadUrl+"";
                        myRef.child("image").setValue(url[0]);
                        //  Log.d("URL ",downloadUrl+"  "+images.size()+"  "+i);
                        // if(imageUrl.size()==count)
                        {
                            //  pd.dismiss();
                           /* UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()

                                    .setPhotoUri(Uri.parse(url[0]))
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("TAG", "User profile updated.");
                                            }
                                        }
                                    });*/
                            Toast.makeText(getApplicationContext(),"Profile Images Updated Successfully ", Toast.LENGTH_SHORT).show();
                           /* Intent i=new Intent(SellDetails.this,Upload_Complete.class);
                            startActivity(i);
                            finish();*/
                        }

                        //insertUrl(url[0],key,count);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
        return url[0];

    }

    public String compressImage(String imageUri) {

        String filePath = getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {               imgRatio = maxHeight / actualHeight;                actualWidth = (int) (imgRatio * actualWidth);               actualHeight = (int) maxHeight;             } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight,Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;

    }

    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "ringthedoctor/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }


    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public String getRealPathFromURI(Uri contentUri)
    {
        try
        {
            String[] proj = {MediaStore.Images.Media.DATA};

            Cursor cursor = this.managedQuery(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        catch (Exception e)
        {
            return contentUri.getPath();
        }
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height/ (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;      }       final float totalPixels = width * height;       final float totalReqPixelsCap = reqWidth * reqHeight * 2;       while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }




    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main,menu);
//        menu.add(MainPage.NONE, MENU_DELETE, menuactivity.NONE, "Delete");
        return super.onCreateOptionsMenu(menu);


    }


    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId()) {
            case R.id.voicecommands:
                //do cool stuff
                signOut();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void signOut() {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}

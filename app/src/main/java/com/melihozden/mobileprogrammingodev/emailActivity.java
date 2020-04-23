package com.melihozden.mobileprogrammingodev;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class emailActivity extends AppCompatActivity {


    Uri fileUri = null;
    String filePath;

    private static final int PICK_FROM_GALLERY = 101;

    EditText emailTo ;
    EditText emailTopic ;
    EditText emailDesc ;
    TextView fileUpload ;

    Button sendButton ;
    Button fileUploadButton ;

    String stringEmailTo ;
    String stringEmailTopic ;
    String stringEmailDesc ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        emailTo = findViewById(R.id.emailTo);
        emailTopic = findViewById(R.id.subject);
        emailDesc = findViewById(R.id.description);
        fileUpload = findViewById(R.id.fileUploadTextView);


        sendButton = findViewById(R.id.sendButton);
        fileUploadButton= findViewById(R.id.fileUploadButton);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String to = emailTo.getText().toString().trim();
                String topic = emailTopic.getText().toString().trim();
                String desc = emailDesc.getText().toString().trim();


                if(to.equals("")){
                    Toast.makeText(emailActivity.this,"Please enter 'to' section", Toast.LENGTH_LONG).show();
                }
                else if(topic.equals("")){
                    Toast.makeText(emailActivity.this,"Please enter 'topic' section",Toast.LENGTH_LONG).show();
                }
                else if(desc.equals("")){
                    Toast.makeText(emailActivity.this,"Please enter 'description' section",Toast.LENGTH_LONG).show();
                }
                else{
                   sendMail();
                }
            }
        });

        fileUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFolder();

               // Intent chooseFile = new Intent(Intent.ACTION_OPEN_DOCUMENT);
               // chooseFile.setType("*/*");
               // chooseFile = Intent.createChooser(chooseFile, "Choose a file");
               // startActivityForResult(chooseFile,PICKFILE_RESULT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            fileUri = data.getData();
            assert fileUri != null;
            fileUpload.setText(fileUri.getLastPathSegment());
            fileUpload.setVisibility(View.VISIBLE);
        }
    }

    public void sendMail(){

        try {
            stringEmailTo = emailTo.getText().toString();
            stringEmailTopic = emailTopic.getText().toString();
            stringEmailDesc = emailDesc.getText().toString();
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{stringEmailTo});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, stringEmailTopic);
            if (fileUri != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
            }
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, stringEmailDesc);
            this.startActivity(Intent.createChooser(emailIntent, "Araciligiyla gonder..."));
        } catch (Throwable t) {
            Toast.makeText(this, "Lutfen tekrar deneyiniz: "+ t.toString(), Toast.LENGTH_LONG).show();
        }

    }
    public void openFolder(){
        Intent intent = new Intent();
        intent.setType("*/*") ;
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data",true) ;
        startActivityForResult(Intent.createChooser(intent,"Choose File Please"),PICK_FROM_GALLERY);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(emailActivity.this,MenuActivity.class);
        startActivity(intent);
    }
}

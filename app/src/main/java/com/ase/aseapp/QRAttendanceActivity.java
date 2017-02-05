package com.ase.aseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;


import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.otto.Subscribe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by alex on 19.01.17.
 */

public class QRAttendanceActivity extends Activity {

    private Person person;
    private Communicator communicator;

    List<Session> dates;
    Calendar c = Calendar.getInstance();
    int day = c.get(Calendar.DAY_OF_YEAR);
    String sessionDate;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);

        Intent intent = getIntent();
        person = (Person) intent.getExtras().get("PERSON");

        communicator = new Communicator();

        communicator.getSessions(person.getToken(), person.getGroupID());

    }

    @Override
    public void onResume(){
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){

        if(serverEvent.getGroupSessions() != null){

            //look vor the mose close session to get a valid QR code
            dates = serverEvent.getGroupSessions().subList(0,serverEvent.getGroupSessions().size());

            //index of the most close session
            int maxDateIndex = 0;
            //difference between actual date and session date
            int maxDate = 380;

            for(int i = 0; i< dates.size(); i++){
                sessionDate = dates.get(i).getEndDate();

                //parse string to Date
                DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                Date createdOn = new Date(Long.parseLong(sessionDate));

                //create calendar time
                Calendar cal = Calendar.getInstance();
                cal.setTime(createdOn);

                //calculate the difference between actual date and session date
                int tutDayDiff = Math.abs(cal.get(Calendar.DAY_OF_YEAR)-day);
                //if the difference is smaller update session index to be used as session ID
                if (maxDate > tutDayDiff){
                    maxDate = tutDayDiff;
                    maxDateIndex = i;
                }

            }

            String sessionID = dates.get(maxDateIndex).getId();


            //String sessionID = "5176457257025536";

            communicator.getAttendanceCode(person.getToken(), sessionID);
        }

        if (serverEvent.getQrBasisCode() != null) {

            QRBasisCode codeForAttendance = serverEvent.getQrBasisCode();
            System.out.println(codeForAttendance.getAttendanceCode());


            // generate QRcode and analyze
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3/4;

            //Encode with a QR Code image
            QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(codeForAttendance.getAttendanceCode(),
                    null,
                    Contents.Type.TEXT,
                    BarcodeFormat.QR_CODE.toString(),
                    smallerDimension);
            try {
                Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
                ImageView myImage = (ImageView) findViewById(R.id.imageView1);
                myImage.setImageBitmap(bitmap);

            } catch (WriterException e) {
                e.printStackTrace();
            }

        }

    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        Toast.makeText(this,""+errorEvent.getErrorMsg(),Toast.LENGTH_SHORT).show();
    }
}

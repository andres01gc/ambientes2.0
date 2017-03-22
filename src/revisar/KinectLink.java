package revisar;

import KinectPV2.*;
import pantallas.visualInterface.Hand;
import processing.core.PApplet;
import processing.core.PVector;
import processing.data.Table;
import processing.data.TableRow;
import root.Logica;

import java.util.ArrayList;

/**
 * Created by andre on 3/5/2017.
 */
public class KinectLink {
    private static KinectPV2 kinect;
    PApplet app = Logica.getApp();
    private static KinectLink kl;
    private boolean sentadilla;
    KJoint cabeza;
    public static PVector manoDer = null;
    private Table table;
    private float distanciaHypeRodillas;

    public boolean isSentadilla() {
        return sentadilla;
    }

    public KJoint[] joints;

    public void drawSkeleton() {
        ArrayList<KSkeleton> skeletonArray = kinect.getSkeletonDepthMap();
        //individual joints
        for (int i = 0; i < skeletonArray.size(); i++) {
            KSkeleton skeleton = (KSkeleton) skeletonArray.get(i);
            //if the skeleton is being tracked compute the skleton joints
            if (skeleton.isTracked()) {
                KJoint[] joints = skeleton.getJoints();



                int col = skeleton.getIndexColor();
                app.fill(col);
                app.stroke(col);
                drawBody(joints);
            }
        }

        if( sentadilla) {
            app.fill(255, 0, 0);
            app.text(distanciaHypeRodillas, 1000, 50);
        }
    }

    //draw the body
    void drawBody(KJoint[] joints) {
        drawJoint(joints, KinectPV2.JointType_HipRight);
        drawJoint(joints, KinectPV2.JointType_HipLeft);

        drawJoint(joints, KinectPV2.JointType_KneeLeft);
        drawJoint(joints, KinectPV2.JointType_KneeRight);

        drawJoint(joints, KinectPV2.JointType_HipRight);
        drawJoint(joints, KinectPV2.JointType_HipLeft);

        drawJoint(joints, KinectPV2.JointType_KneeLeft);
        drawJoint(joints, KinectPV2.JointType_KneeRight);


        //Single joints
        drawJoint(joints, KinectPV2.JointType_HandTipLeft);
        drawJoint(joints, KinectPV2.JointType_HandTipRight);
        drawJoint(joints, KinectPV2.JointType_FootLeft);
        drawJoint(joints, KinectPV2.JointType_FootRight);

        drawJoint(joints, KinectPV2.JointType_ThumbLeft);
        drawJoint(joints, KinectPV2.JointType_ThumbRight);

        drawJoint(joints, KinectPV2.JointType_Head);
    }


    private void sentadilla(KJoint hipeR, KJoint hipeL, KJoint kneeR, KJoint kneeL) {
        float dkneelY = kneeL.getY() - hipeL.getY();
        float dHype = app.dist(hipeL.getX(), hipeL.getY(), hipeR.getX(), hipeR.getY());

        distanciaHypeRodillas = dkneelY / dHype;
        app.fill(255);
        app.textSize(50);


        if (distanciaHypeRodillas < 1.7f) {
            sentadilla = true;
        } else {
            sentadilla = false;
        }
    }

    //draw a single joint
    void drawJoint(KJoint[] joints, int jointType) {
        app.pushMatrix();
        app.translate(joints[jointType].getX(), joints[jointType].getY(), joints[jointType].getZ());
        app.ellipse(0, 0, 25, 25);
        app.popMatrix();
    }

    //draw a bone from two joints
    void drawBone(KJoint[] joints, int jointType1, int jointType2) {
        app.pushMatrix();
        app.translate(joints[jointType1].getX(), joints[jointType1].getY(), joints[jointType1].getZ());
        app.ellipse(0, 0, 25, 25);
        app.popMatrix();
        app.line(joints[jointType1].getX(), joints[jointType1].getY(), joints[jointType1].getZ(), joints[jointType2].getX(), joints[jointType2].getY(), joints[jointType2].getZ());
    }

    //draw a ellipse depending on the hand state
    void drawHandState(KJoint joint) {
        float x = app.map(joint.getX(), 0, 512, 0, 1920);
        float y = app.map(joint.getY(), 0, 424, 0, 1080);

        Hand.pos.x = x;
        Hand.pos.y = y;

//        app.noStroke();
//        handState(joint.getState());
//        app.pushMatrix();
//        app.translate(joint.getX(), joint.getY(), joint.getZ());
//        app.ellipse(0, 0, 70, 70);
//        app.popMatrix();
    }

/*
Different hand state
 KinectPV2.HandState_Open
 KinectPV2.HandState_Closed
 KinectPV2.HandState_Lasso
 KinectPV2.HandState_NotTracked
 */


    //Depending on the hand state change the color
    void handState(int handState) {
        switch (handState) {
            case KinectPV2.HandState_Open:
                app.fill(0, 255, 0);
                break;
            case KinectPV2.HandState_Closed:
                app.fill(255, 0, 0);
                break;
            case KinectPV2.HandState_Lasso:
                app.fill(0, 0, 255);
                break;
            case KinectPV2.HandState_NotTracked:
                app.fill(100, 100, 100);
                break;
        }
    }


    public boolean sentadilla() {
        return sentadilla;
    }

    public static KinectLink getInstance() {
        if (kl == null) kl = new KinectLink();
        return kl;
    }


    public void saveSkeletonByFrame() {
        TableRow newRow = table.addRow();

        newRow.setFloat("Head-x", joints[KinectPV2.JointType_Head].getX());
        newRow.setFloat("Head-y", joints[KinectPV2.JointType_Head].getY());
        newRow.setFloat("Head-z", joints[KinectPV2.JointType_Head].getZ());

        newRow.setFloat("Neck-x", joints[KinectPV2.JointType_Neck].getX());
        newRow.setFloat("Neck-y", joints[KinectPV2.JointType_Neck].getY());
        newRow.setFloat("Neck-z", joints[KinectPV2.JointType_Neck].getZ());

        newRow.setFloat("SpineShoulder-x", joints[KinectPV2.JointType_SpineShoulder].getX());
        newRow.setFloat("SpineShoulder-y", joints[KinectPV2.JointType_SpineShoulder].getY());
        newRow.setFloat("SpineShoulder-z", joints[KinectPV2.JointType_SpineShoulder].getZ());

        newRow.setFloat("SpineMid-x", joints[KinectPV2.JointType_SpineMid].getX());
        newRow.setFloat("SpineMid-y", joints[KinectPV2.JointType_SpineMid].getY());
        newRow.setFloat("SpineMid-z", joints[KinectPV2.JointType_SpineMid].getZ());

        newRow.setFloat("SpineBase-x", joints[KinectPV2.JointType_SpineBase].getX());
        newRow.setFloat("SpineBase-y", joints[KinectPV2.JointType_SpineBase].getY());
        newRow.setFloat("SpineBase-z", joints[KinectPV2.JointType_SpineBase].getZ());

        newRow.setFloat("HipRight-x", joints[KinectPV2.JointType_HipRight].getX());
        newRow.setFloat("HipRight-y", joints[KinectPV2.JointType_HipRight].getY());
        newRow.setFloat("HipRight-z", joints[KinectPV2.JointType_HipRight].getZ());

        newRow.setFloat("HipLeft-x", joints[KinectPV2.JointType_HipLeft].getX());
        newRow.setFloat("HipLeft-y", joints[KinectPV2.JointType_HipLeft].getY());
        newRow.setFloat("HipLeft-z", joints[KinectPV2.JointType_HipLeft].getZ());

        newRow.setFloat("ShoulderRigh-x", joints[KinectPV2.JointType_ShoulderRight].getX());
        newRow.setFloat("ShoulderRigh-y", joints[KinectPV2.JointType_ShoulderRight].getY());
        newRow.setFloat("ShoulderRigh-z", joints[KinectPV2.JointType_ShoulderRight].getZ());

        newRow.setFloat("ElbowRight-x", joints[KinectPV2.JointType_ElbowRight].getX());
        newRow.setFloat("ElbowRight-y", joints[KinectPV2.JointType_ElbowRight].getY());
        newRow.setFloat("ElbowRight-z", joints[KinectPV2.JointType_ElbowRight].getZ());

        newRow.setFloat("WristRight-x", joints[KinectPV2.JointType_WristRight].getX());
        newRow.setFloat("WristRight-y", joints[KinectPV2.JointType_WristRight].getY());
        newRow.setFloat("WristRight-z", joints[KinectPV2.JointType_WristRight].getZ());

        newRow.setFloat("HandRight-x", joints[KinectPV2.JointType_HandRight].getX());
        newRow.setFloat("HandRight-y", joints[KinectPV2.JointType_HandRight].getY());
        newRow.setFloat("HandRight-z", joints[KinectPV2.JointType_HandRight].getZ());

        newRow.setFloat("HandTipRight-x", joints[KinectPV2.JointType_HandTipRight].getX());
        newRow.setFloat("HandTipRight-y", joints[KinectPV2.JointType_HandTipRight].getY());
        newRow.setFloat("HandTipRight-z", joints[KinectPV2.JointType_HandTipRight].getZ());

        newRow.setFloat("ThumbRight-x", joints[KinectPV2.JointType_ThumbRight].getX());
        newRow.setFloat("ThumbRight-y", joints[KinectPV2.JointType_ThumbRight].getY());
        newRow.setFloat("ThumbRight-z", joints[KinectPV2.JointType_ThumbRight].getZ());

        newRow.setFloat("ShoulderLeft-x", joints[KinectPV2.JointType_ShoulderLeft].getX());
        newRow.setFloat("ShoulderLeft-y", joints[KinectPV2.JointType_ShoulderLeft].getY());
        newRow.setFloat("ShoulderLeft-z", joints[KinectPV2.JointType_ShoulderLeft].getZ());

        newRow.setFloat("ElbowLeft-x", joints[KinectPV2.JointType_ElbowLeft].getX());
        newRow.setFloat("ElbowLeft-y", joints[KinectPV2.JointType_ElbowLeft].getY());
        newRow.setFloat("ElbowLeft-z", joints[KinectPV2.JointType_ElbowLeft].getZ());

        newRow.setFloat("WristLeft-x", joints[KinectPV2.JointType_WristLeft].getX());
        newRow.setFloat("WristLeft-y", joints[KinectPV2.JointType_WristLeft].getY());
        newRow.setFloat("WristLeft-z", joints[KinectPV2.JointType_WristLeft].getZ());

        newRow.setFloat("HandLeft-x", joints[KinectPV2.JointType_HandLeft].getX());
        newRow.setFloat("HandLeft-y", joints[KinectPV2.JointType_HandLeft].getY());
        newRow.setFloat("HandLeft-z", joints[KinectPV2.JointType_HandLeft].getZ());

        newRow.setFloat("HandTipLeft-x", joints[KinectPV2.JointType_HandTipLeft].getX());
        newRow.setFloat("HandTipLeft-y", joints[KinectPV2.JointType_HandTipLeft].getY());
        newRow.setFloat("HandTipLeft-z", joints[KinectPV2.JointType_HandTipLeft].getZ());

        newRow.setFloat("ThumbLeft-x", joints[KinectPV2.JointType_ThumbLeft].getX());
        newRow.setFloat("ThumbLeft-y", joints[KinectPV2.JointType_ThumbLeft].getY());
        newRow.setFloat("ThumbLeft-z", joints[KinectPV2.JointType_ThumbLeft].getZ());

        newRow.setFloat("KneeRight-x", joints[KinectPV2.JointType_ThumbLeft].getX());
        newRow.setFloat("KneeRight-y", joints[KinectPV2.JointType_ThumbLeft].getY());
        newRow.setFloat("KneeRight-z", joints[KinectPV2.JointType_ThumbLeft].getZ());

        newRow.setFloat("AnkleRight-x", joints[KinectPV2.JointType_AnkleRight].getX());
        newRow.setFloat("AnkleRight-y", joints[KinectPV2.JointType_AnkleRight].getZ());
        newRow.setFloat("AnkleRight-z", joints[KinectPV2.JointType_AnkleRight].getY());

        newRow.setFloat("FootRight-x", joints[KinectPV2.JointType_FootRight].getX());
        newRow.setFloat("FootRight-y", joints[KinectPV2.JointType_FootRight].getZ());
        newRow.setFloat("FootRight-z", joints[KinectPV2.JointType_FootRight].getY());

        newRow.setFloat("KneeLeft-x", joints[KinectPV2.JointType_KneeLeft].getX());
        newRow.setFloat("KneeLeft-y", joints[KinectPV2.JointType_KneeLeft].getZ());
        newRow.setFloat("KneeLeft-z", joints[KinectPV2.JointType_KneeLeft].getY());

        newRow.setFloat("AnkleLeft-x", joints[KinectPV2.JointType_AnkleLeft].getX());
        newRow.setFloat("AnkleLeft-y", joints[KinectPV2.JointType_AnkleLeft].getZ());
        newRow.setFloat("AnkleLeft-z", joints[KinectPV2.JointType_AnkleLeft].getY());

        newRow.setFloat("FootLeft-x", joints[KinectPV2.JointType_FootLeft].getX());
        newRow.setFloat("FootLeft-y", joints[KinectPV2.JointType_FootLeft].getZ());
        newRow.setFloat("FootLeft-z", joints[KinectPV2.JointType_FootLeft].getY());
        app.saveTable(table, "../data/kinect.csv");
    }




    public void createColums() {

        table = new Table();
        table.addColumn("frame");
        table.addColumn("equilibrio-x");
        table.addColumn("equilibrio-y");
        table.addColumn("equilibrio-z");

        table.addColumn("Head-x");
        table.addColumn("Head-y");
        table.addColumn("Head-z");


        table.addColumn("Neck-x");
        table.addColumn("Neck-y");
        table.addColumn("Neck-z");


        table.addColumn("SpineShoulder-x");
        table.addColumn("SpineShoulder-y");
        table.addColumn("SpineShoulder-z");

        table.addColumn("SpineMid-x");
        table.addColumn("SpineMid-y");
        table.addColumn("SpineMid-z");


        table.addColumn("SpineBase-x");
        table.addColumn("SpineBase-y");
        table.addColumn("SpineBase-z");

//RIGTH

        table.addColumn("HipRight-x");
        table.addColumn("HipRight-y");
        table.addColumn("HipRight-z");

        table.addColumn("HipLeft-x");
        table.addColumn("HipLeft-y");
        table.addColumn("HipLeft-z");

        table.addColumn("ShoulderRigh-x");
        table.addColumn("ShoulderRigh-y");
        table.addColumn("ShoulderRigh-z");


        table.addColumn("ElbowRight-x");
        table.addColumn("ElbowRight-y");
        table.addColumn("ElbowRight-z");


        table.addColumn("WristRight-x");
        table.addColumn("WristRight-y");
        table.addColumn("WristRight-z");

        table.addColumn("HandRight-x");
        table.addColumn("HandRight-y");
        table.addColumn("HandRight-z");

        table.addColumn("HandTipRight-x");
        table.addColumn("HandTipRight-y");
        table.addColumn("HandTipRight-z");

        table.addColumn("ThumbRight-x");
        table.addColumn("ThumbRight-y");
        table.addColumn("ThumbRight-z");

//LEFT

        table.addColumn("ShoulderLeft-x");
        table.addColumn("ShoulderLeft-y");
        table.addColumn("ShoulderLeft-z");

        table.addColumn("ElbowLeft-x");
        table.addColumn("ElbowLeft-y");
        table.addColumn("ElbowLeft-z");


        table.addColumn("WristLeft-x");
        table.addColumn("WristLeft-y");
        table.addColumn("WristLeft-z");

        table.addColumn("HandLeft-x");
        table.addColumn("HandLeft-y");
        table.addColumn("HandLeft-z");

        table.addColumn("HandTipLeft-x");
        table.addColumn("HandTipLeft-y");
        table.addColumn("HandTipLeft-z");

        table.addColumn("ThumbLeft-x");
        table.addColumn("ThumbLeft-y");
        table.addColumn("ThumbLeft-z");


        table.addColumn("KneeRight-x");
        table.addColumn("KneeRight-y");
        table.addColumn("KneeRight-z");

        table.addColumn("AnkleRight-x");
        table.addColumn("AnkleRight-y");
        table.addColumn("AnkleRight-z");

        table.addColumn("FootRight-x");
        table.addColumn("FootRight-y");
        table.addColumn("FootRight-z");

        table.addColumn("KneeLeft-x");
        table.addColumn("KneeLeft-y");
        table.addColumn("KneeLeft-z");

        table.addColumn("AnkleLeft-x");
        table.addColumn("AnkleLeft-y");
        table.addColumn("AnkleLeft-z");

        table.addColumn("FootLeft-x");
        table.addColumn("FootLeft-y");
        table.addColumn("FootLeft-z");

    }


    KinectLink() {
        kinect = new KinectPV2(Logica.getApp());
        //Enables depth and Body tracking (mask image)
        kinect.enableDepthMaskImg(true);
        kinect.enableSkeletonDepthMap(true);
        kinect.init();

        new Thread(new Runnable() {

            @Override
            public synchronized void run() {
                while (true) {
                    ArrayList<KSkeleton> skeletonArray = kinect.getSkeletonDepthMap();
                    //individual joints
                    for (int i = 0; i < skeletonArray.size(); i++) {
                        KSkeleton skeleton = (KSkeleton) skeletonArray.get(i);
                        //if the skeleton is being tracked compute the skleton joints
                        if (skeleton.isTracked()) {
                            joints = skeleton.getJoints();
                            sentadilla(joints[KinectPV2.JointType_HipRight], joints[KinectPV2.JointType_HipLeft],
                                    joints[KinectPV2.JointType_KneeLeft], joints[KinectPV2.JointType_KneeRight]);

                            drawHandState(joints[KinectPV2.JointType_HandRight]);
                        }
                    }

                    try {
                        Thread.sleep(33);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        createColums();
    }

    public void startRec() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("guarda!");
                        saveSkeletonByFrame();
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

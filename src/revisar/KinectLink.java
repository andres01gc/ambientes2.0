package revisar;

import KinectPV2.*;
import pantallas.visualInterface.Hand;
import processing.core.PApplet;
import processing.core.PVector;
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

    public boolean isSentadilla() {
        return sentadilla;
    }


    public void drawSkeleton() {
        app.image(kinect.getDepthMaskImage(), 0, 0);

        //    System.out.println("añsfljnbañfnbañsfb" + kinect.getDepthMaskImage().height + "  " + kinect.getDepthImage().width);

        //get the skeletons as an Arraylist of KSkeletons
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
//                drawHandState(joints[KinectPV2.JointType_HandRight]);
//                drawHandState(joints[KinectPV2.JointType_HandLeft]);
            }
        }
        app.fill(255, 0, 0);
        app.text(app.frameRate, 50, 50);
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

        float distanciaHypeRodillas = dkneelY / dHype;
        app.fill(255);
        app.textSize(50);

        if (distanciaHypeRodillas < 2f) {
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


        return false;
    }

    public static KinectLink getInstance() {
        if (kl == null) kl = new KinectLink();
        return kl;
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
                            KJoint[] joints = skeleton.getJoints();
                            sentadilla(joints[KinectPV2.JointType_HipRight], joints[KinectPV2.JointType_HipLeft], joints[KinectPV2.JointType_KneeLeft], joints[KinectPV2.JointType_KneeRight]);


                            //                        drawBody(joints);
                            drawHandState(joints[KinectPV2.JointType_HandRight]);
//                        drawHandState(joints[KinectPV2.JointType_HandLeft]);
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

    }
}

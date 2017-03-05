package resources;

import KinectPV2.*;
import processing.core.PApplet;
import root.Logica;

import java.util.ArrayList;

/**
 * Created by andre on 3/5/2017.
 */
public class KinectLink {
    private final KinectPV2 kinect;
    PApplet app = Logica.getApp();
    private static KinectLink kl;

    public void drawBody(KJoint[] joints) {
        drawBone(joints, KinectPV2.JointType_Head, KinectPV2.JointType_Neck);
        drawBone(joints, KinectPV2.JointType_Neck, KinectPV2.JointType_SpineShoulder);
        drawBone(joints, KinectPV2.JointType_SpineShoulder, KinectPV2.JointType_SpineMid);

        drawBone(joints, KinectPV2.JointType_SpineMid, KinectPV2.JointType_SpineBase);
        drawBone(joints, KinectPV2.JointType_SpineShoulder, KinectPV2.JointType_ShoulderRight);
        drawBone(joints, KinectPV2.JointType_SpineShoulder, KinectPV2.JointType_ShoulderLeft);
        drawBone(joints, KinectPV2.JointType_SpineBase, KinectPV2.JointType_HipRight);
        drawBone(joints, KinectPV2.JointType_SpineBase, KinectPV2.JointType_HipLeft);

        // Right Arm
        drawBone(joints, KinectPV2.JointType_ShoulderRight, KinectPV2.JointType_ElbowRight);
        drawBone(joints, KinectPV2.JointType_ElbowRight, KinectPV2.JointType_WristRight);
        drawBone(joints, KinectPV2.JointType_WristRight, KinectPV2.JointType_HandRight);
        drawBone(joints, KinectPV2.JointType_HandRight, KinectPV2.JointType_HandTipRight);
        drawBone(joints, KinectPV2.JointType_WristRight, KinectPV2.JointType_ThumbRight);

        // Left Arm
        drawBone(joints, KinectPV2.JointType_ShoulderLeft, KinectPV2.JointType_ElbowLeft);
        drawBone(joints, KinectPV2.JointType_ElbowLeft, KinectPV2.JointType_WristLeft);
        drawBone(joints, KinectPV2.JointType_WristLeft, KinectPV2.JointType_HandLeft);
        drawBone(joints, KinectPV2.JointType_HandLeft, KinectPV2.JointType_HandTipLeft);
        drawBone(joints, KinectPV2.JointType_WristLeft, KinectPV2.JointType_ThumbLeft);

        // Right Leg
        drawBone(joints, KinectPV2.JointType_HipRight, KinectPV2.JointType_KneeRight);
        drawBone(joints, KinectPV2.JointType_KneeRight, KinectPV2.JointType_AnkleRight);
        drawBone(joints, KinectPV2.JointType_AnkleRight, KinectPV2.JointType_FootRight);

        // Left Leg
        drawBone(joints, KinectPV2.JointType_HipLeft, KinectPV2.JointType_KneeLeft);
        drawBone(joints, KinectPV2.JointType_KneeLeft, KinectPV2.JointType_AnkleLeft);
        drawBone(joints, KinectPV2.JointType_AnkleLeft, KinectPV2.JointType_FootLeft);

        drawJoint(joints, KinectPV2.JointType_HandTipLeft);
        drawJoint(joints, KinectPV2.JointType_HandTipRight);
        drawJoint(joints, KinectPV2.JointType_FootLeft);
        drawJoint(joints, KinectPV2.JointType_FootRight);

        drawJoint(joints, KinectPV2.JointType_ThumbLeft);
        drawJoint(joints, KinectPV2.JointType_ThumbRight);

        drawJoint(joints, KinectPV2.JointType_Head);
    }

    void drawJoint(KJoint[] joints, int jointType) {
        app.strokeWeight(2.0f + joints[jointType].getZ() * 8);
        app.point(joints[jointType].getX(), joints[jointType].getY(), joints[jointType].getZ());
    }

    void drawBone(KJoint[] joints, int jointType1, int jointType2) {
        app.strokeWeight(2.0f + joints[jointType1].getZ() * 8);
        app.point(joints[jointType2].getX(), joints[jointType2].getY(), joints[jointType2].getZ());
    }

    void drawHandState(KJoint joint) {
        handState(joint.getState());
        app.strokeWeight(5.0f + joint.getZ() * 8);
        app.point(joint.getX(), joint.getY(), joint.getZ());
    }

    void handState(int handState) {
        switch (handState) {
            case KinectPV2.HandState_Open:
                app.stroke(0, 255, 0);
                break;
            case KinectPV2.HandState_Closed:
                app.stroke(255, 0, 0);
                break;
            case KinectPV2.HandState_Lasso:
                app.stroke(0, 0, 255);
                break;
            case KinectPV2.HandState_NotTracked:
                app.stroke(100, 100, 100);
                break;
        }
    }

    private KinectLink() {
        kinect = new KinectPV2(app);
        kinect.enableColorImg(true);
        //enable 3d  with (x,y,z) position
        kinect.enableSkeleton3DMap(true);
        kinect.init();
    }

    public void drawSkeleton() {
   app.image(kinect.getColorImage(), 0, 0,640,480);
        System.out.println(app.mouseX);
        app.pushMatrix();
        app.translate(app.width / 2, app.height / 2, 0);
        float zVal = app.mouseX;
        float rotX = app.PI;
        app.scale(zVal);
        app.rotateX(rotX);

        ArrayList<KSkeleton> skeletonArray = kinect.getSkeleton3d();

        //individual JOINTS
        for (int i = 0; i < skeletonArray.size(); i++) {
            KSkeleton skeleton = (KSkeleton) skeletonArray.get(i);
            if (skeleton.isTracked()) {
                KJoint[] joints = skeleton.getJoints();

                //draw different color for each hand state
                drawHandState(joints[KinectPV2.JointType_HandRight]);
                drawHandState(joints[KinectPV2.JointType_HandLeft]);

                //Draw body
                int col = skeleton.getIndexColor();
                app.stroke(255);
                drawBody(joints);
            }
        }

        app.popMatrix();
    }

    public static KinectLink getInstance() {
        if (kl == null) kl = new KinectLink();
        return kl;
    }
}

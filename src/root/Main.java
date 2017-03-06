package root;

import jssc.SerialPortList;
import processing.core.PApplet;
import setup.Pantalla;
import setup.ProcessingEvent;
import processing.serial.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends PApplet {
    private Logica logica;
    //protected static PApplet app;
    public static List<ProcessingEvent> processingEvents = Collections
            .synchronizedList(new ArrayList<ProcessingEvent>());
    public static float[] valorFloats = null;
    Serial myPort;
    private String[] valString = new String[3];
    private String inString;

    // metodo para poder exportarse como una aplicaci�n, NO TOCAR
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"root.Main"};
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }


    }

    public void settings() {
        fullScreen(P3D);
        //size(1920, 1080,FX2D);
        // smooth();
    }

    /**
     * Configuraciones directas de processing con su lienzo.
     */

    public void setup() {

        Pantalla.app = this;
        //	app = this;
        logica = new Logica(this);


        // size(400, 400);
        frameRate(200);
        println(SerialPortList.getPortNames());

        valorFloats = null;

        myPort = new Serial(this, Serial.list()[0], 115200);
        myPort.bufferUntil('\n');

        valorFloats = new float[]{
                0, 0, 0
        };

    }

    public void draw() {
        background(255);

        if (inString != null) {
            valString = inString.split(",");
            System.out.println("linea completa: " + inString);
            System.out.println("strings " + valString[0] + "  " + valString[1] + "  " + valString[2] + "  ");

            valorFloats = new float[]{
                    valorFloats[0] = Float.parseFloat(valString[0]),
                    valorFloats[1] = Float.parseFloat(valString[1]),
                    valorFloats[2] = Float.parseFloat(valString[2])
            };

            System.out.println("float " + valorFloats[0] + "  " + valorFloats[1] + "  " + valorFloats[2] + "  ");
        }
        logica.pintar();
    }

    public void mousePressed() {
        for (ProcessingEvent p : processingEvents) {
            p.mousePressed();
        }
        logica.mousePressed();
    }

    public void mouseReleased() {
        for (ProcessingEvent p : processingEvents) {
            p.mouseReleased();
        }
        logica.mouseReleased();
    }

    public void mouseDragged() {
        for (ProcessingEvent p : processingEvents) {
            p.mouseDragged();
        }
        logica.mouseDragged();
    }

    public void keyPressed() {
        for (ProcessingEvent p : processingEvents) {
            p.keyPressed();
        }
        logica.keyPressed();
    }

    public void serialEvent(Serial myPort) {
        inString = myPort.readStringUntil('\n');
    }
}

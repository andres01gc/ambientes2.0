package info;

import setup.Pantalla;
import processing.core.*;

import java.util.ArrayList;

public class Info {
    static PApplet app = Pantalla.app;
    private static Info info;

    public static PImage PLAYER;

    private Info() {
        // PLAYER = app.loadImage("");
    }

    public static Info getInstance() {
        if (info == null) {
            info = new Info();
        }
        return info;
    }

}

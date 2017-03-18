package info;

import setup.Pantalla;
import processing.core.*;

import java.util.ArrayList;

public class Info {
    static PApplet app = Pantalla.app;
    private static Info info;

    public static PImage PLAYER;

    public PImage ima1, ima2, ima3, ima4;

    public PImage c1, c2, c3, cEngrane1, cEngrane2, cEngrane3;
    public PImage perfil1;
    public PImage perfil2;
    public PImage perfil3;

    public PImage pp1;

    public PImage pp2;

    public PImage pp3;

    public PImage level1;
    public PImage level2;
    public PImage level3;
    public PImage level4;

    public PImage onF;
    public PImage onL;
    public PImage inst1;
    public PImage inst2;

    public PImage[] obstaculos;

    public void loadInicio() {
        ima1 = app.loadImage("../data/resources/pantallaInicial/1.png");
        ima2 = app.loadImage("../data/resources/pantallaInicial/2.png");
        ima3 = app.loadImage("../data/resources/pantallaInicial/3.png");
        ima4 = app.loadImage("../data/resources/pantallaInicial/4.png");
    }

    public void loadPprincipal() {
        pp1 = app.loadImage("../data/resources/pprincipal/13.png");
        pp2 = app.loadImage("../data/resources/pprincipal/14.png");
        pp3 = app.loadImage("../data/resources/pprincipal/15.png");
    }

    public void loadConfiguracion() {
        c1 = app.loadImage("../data/resources/configuracion/4.png");
        c2 = app.loadImage("../data/resources/configuracion/5.png");
        c3 = app.loadImage("../data/resources/configuracion/6.png");

        cEngrane1 = app.loadImage("../data/resources/configuracion/7.png");
        cEngrane2 = app.loadImage("../data/resources/configuracion/8.png");
        cEngrane3 = app.loadImage("../data/resources/configuracion/9.png");
    }

    public void loadPerfil() {
        perfil1 = app.loadImage("../data/resources/perfil/10.png");
        perfil2 = app.loadImage("../data/resources/perfil/11.png");
        perfil3 = app.loadImage("../data/resources/perfil/12.png");

        onL = app.loadImage("../data/resources/perfil/21.png");
        onF = app.loadImage("../data/resources/perfil/22.png");

    }


    public void loadLevel() {
        level1 = app.loadImage("../data/resources/level/16.png");
        level2 = app.loadImage("../data/resources/level/17.png");
        level3 = app.loadImage("../data/resources/level/18.png");
        level4 = app.loadImage("../data/resources/level/18.1.png");
    }


    private Info() {
        PLAYER = app.loadImage("");
    }


    public static Info getInstance() {
        if (info == null) {
            info = new Info();
        }

        return info;
    }

    public void loadInstrucciones() {

        inst1 = app.loadImage("../data/resources/instrucciones/28.png");
        inst2 = app.loadImage("../data/resources/instrucciones/29.png");


    }


    public void cargarObstaculos() {
        obstaculos = new PImage[]{
                app.loadImage("../data/resources/juego/enemigos/ene1.png"),
                app.loadImage("../data/resources/juego/enemigos/ene2.png"),
                app.loadImage("../data/resources/juego/enemigos/ene3.png"),
                app.loadImage("../data/resources/juego/enemigos/ene4.png"),

        };

    }
}

package pantallas;

import info.Info;
import pantallas.visualInterface.Hand;
import processing.core.PImage;
import setup.AdministradorPantalla;
import setup.Pantalla;

public class PantallaInicial extends Pantalla {

    private PImage fondo;
    //  private KinectLink kl;
    float t;
    private float vibra = 2;


    @Override
    public void iniciar() {
        // TODO Auto-generated method stub
        app.textSize(50);
        inicializarImgs();
        app.textAlign(app.CENTER, app.CENTER);

        Info.getInstance().loadInicio();

    }

    @Override
    public void pintar() {
        app.background(36, 31, 45);

        app.image(Info.getInstance().ima2, app.map(app.noise(t + 7378), 0, 1, -vibra, vibra), app.map(app.noise(t + 457), 0, 1, -vibra, vibra));
        app.image(Info.getInstance().ima3, app.map(app.noise(t + 836), 0, 1, -vibra, vibra), app.map(app.noise(t + 2698), 0, 1, -vibra, vibra));
        app.image(Info.getInstance().ima1, app.map(app.noise(t + 9849), 0, 1, -vibra, vibra), app.map(app.noise(t + 524), 0, 1, -vibra, vibra));

        if (app.dist(Hand.pos.x, Hand.pos.y, 1623, 943) < 300) {
            app.image(Info.getInstance().ima4, app.map(app.noise(t + t + 624), 0, 1, -vibra, vibra), app.map(app.noise(t + 6998), 0, 1, -vibra, vibra));
            cargarMano();
        }

        //kl.drawSkeleton();
        update();
        Hand.pintar();
    }

    public void update() {
        t += 0.06;
    }

    @Override
    public void finalizar() {

    }


    public void inicializarImgs() {
        // fondo = app.loadImage("data/inicio/fondo.png");
    }


    @Override
    public void pressHandRight() {
        if (app.dist(Hand.pos.x, Hand.pos.y, 1623, 943) < 300) {
            app.image(Info.getInstance().ima4, app.map(app.noise(t + t + 624), 0, 1, -vibra, vibra), app.map(app.noise(t + 6998), 0, 1, -vibra, vibra));
            AdministradorPantalla.cambiarPantalla(new Configuraciones());
        }
    }

    @Override
    public void mousePressed() {
        // TODO Auto-generated method stub
        //AdministradorPantalla.cambiarPantalla(new Configuraciones());
        super.mousePressed();
    }

    @Override
    public void KeyPressed() {
        super.KeyPressed();
        //AdministradorPantalla.cambiarPantalla(new Configuraciones());
    }
}

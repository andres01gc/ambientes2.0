package pantallas;

import info.Info;
import juego.PantallaJuego;
import setup.AdministradorPantalla;
import setup.Pantalla;

public class Configuraciones extends Pantalla {


    Info info = Info.getInstance();

    float t = 0;
    float vibra = 4;
    float angle = 0;

    @Override
    public void iniciar() {
        // TODO Auto-generated method stub
        System.out.println("hey");
        info.loadConfiguracion();

    }

    @Override
    public void pintar() {
        app.background(36, 31, 45);
        app.fill(0);
        app.image(Info.getInstance().c1, app.map(app.noise(t + t + 7378), 0, 1, -vibra, vibra), app.map(app.noise(t + 457), 0, 1, -vibra, vibra));
        app.image(Info.getInstance().c2, 0, 0);
        app.image(Info.getInstance().c3, app.map(app.noise(t + 7828), 0, 1, -vibra, vibra), app.map(app.noise(t + 524), 0, 1, -vibra, vibra));
        pintarEngranes();
        update();
    }

    public void pintarEngranes() {
        app.imageMode(app.CENTER);
        app.pushMatrix();
        app.translate(986, 537);
        app.rotate(angle / 2);
        app.image(info.cEngrane1, 0, 0);

        app.popMatrix();
        app.pushMatrix();
        app.translate(1268, 326);
        app.rotate(-angle / 3);

        app.image(info.cEngrane2, 0, 0);

        app.popMatrix();
        app.pushMatrix();
        app.translate(1318, 567);
        app.rotate(angle / 2.1f);

        app.image(info.cEngrane3, 0, 0);

        app.popMatrix();
        app.imageMode(app.CORNER);
    }

    @Override
    public void finalizar() {
        // TODO Auto-generated method stub
    }


    public void update() {

        t += 0.06;
        angle += 0.03;
    }


    @Override
    public void mousePressed() {
        AdministradorPantalla.cambiarPantalla(new Perfil());
    }
}

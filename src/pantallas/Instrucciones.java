package pantallas;

import processing.core.PImage;
import setup.AdministradorPantalla;
import setup.Pantalla;

public class Instrucciones extends Pantalla {



    @Override
    public void iniciar() {
        // TODO Auto-generated method stub
        System.out.println("hey");
    }

    @Override
    public void pintar() {
        app.fill(0);

    }

    @Override
    public void finalizar() {
        // TODO Auto-generated method stub
    }

    public void inicializarImgs() {


    }

    @Override
    public void mousePressed() {
        AdministradorPantalla.cambiarPantalla(new PantallaJuego());

    }
}

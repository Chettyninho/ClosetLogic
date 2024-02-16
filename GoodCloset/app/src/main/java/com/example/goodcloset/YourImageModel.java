package com.example.goodcloset;

public class YourImageModel {

    private byte[] fotoArriba;
    private byte[] fotoMedio;
    private byte[] fotoAbajo;

    public YourImageModel(byte[] fotoArriba, byte[] fotoMedio, byte[] fotoAbajo) {
        this.fotoArriba = fotoArriba;
        this.fotoMedio = fotoMedio;
        this.fotoAbajo = fotoAbajo;
    }

    public byte[] getFotoArriba() {
        return fotoArriba;
    }

    public void setFotoArriba(byte[] fotoArriba) {
        this.fotoArriba = fotoArriba;
    }

    public byte[] getFotoMedio() {
        return fotoMedio;
    }

    public void setFotoMedio(byte[] fotoMedio) {
        this.fotoMedio = fotoMedio;
    }

    public byte[] getFotoAbajo() {
        return fotoAbajo;
    }

    public void setFotoAbajo(byte[] fotoAbajo) {
        this.fotoAbajo = fotoAbajo;
    }
}

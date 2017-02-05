package com.ase.aseapp;

/**
 * Created by alex on 31.01.17.
 */



import com.squareup.otto.Bus;

public class BusProvider {

    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    public BusProvider(){}
}

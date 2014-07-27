/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kpirolt.mygame;

import com.jme3.input.controls.AnalogListener;

/**
 *
 * @author Kevin Pirolt
 */
public class MasterAnalogListener implements AnalogListener{
    
    private Reactor reactor;

    MasterAnalogListener(Reactor reactor) {
        this.reactor = reactor;
    }

    public void onAnalog(String name, float value, float tpf) {
        if(name.equals("forward"))
            this.reactor.moveForward(tpf);
    }
    
}

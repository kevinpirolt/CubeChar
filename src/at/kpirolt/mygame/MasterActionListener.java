/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kpirolt.mygame;

import com.jme3.input.controls.ActionListener;

/**
 *
 * @author Kevin Pirolt
 */
public class MasterActionListener implements ActionListener{

    private Reactor reactor;
    
    MasterActionListener(Reactor reactor) {
        this.reactor = reactor;
    }

    public void onAction(String name, boolean isPressed, float tpf) {
        if(name.equals("forward") && !isPressed)
            this.reactor.forwardStopped();
    }
    
}

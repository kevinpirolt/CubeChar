/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kpirolt.mygame;


/**
 *
 * @author Kevin Pirolt
 */
public class MasterListener{
    
    private Reactor reactor;
    private MasterActionListener mal;
    private MasterAnalogListener manal;
    
    public MasterListener(Reactor _reactor) {
        this.reactor = _reactor;
        this.mal = new MasterActionListener(reactor);
        this.manal = new MasterAnalogListener(reactor);
    }


    public MasterActionListener getMal() {
        return mal;
    }


    public void setMal(MasterActionListener mal) {
        this.mal = mal;
    }


    public MasterAnalogListener getManal() {
        return manal;
    }

 
    public void setManal(MasterAnalogListener manal) {
        this.manal = manal;
    }
}

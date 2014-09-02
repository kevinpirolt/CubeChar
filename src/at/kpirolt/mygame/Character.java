/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kpirolt.mygame;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Kevin Pirolt
 */
public class Character implements Reactor, AnimEventListener{
    
    private Spatial model;
    private AnimChannel channel;
    private AnimControl control;
    
    private boolean left = false,
                    right = false,
                    forward = false,
                    back = false;
    
    private float velX, velY;
    private float weight;
    
    public Character(Spatial _model, float velX, float velY, float weight) {
        this.model = _model;
        this.initAnimations();
        this.velX = velX;
        this.velY = velY;
        this.weight = weight;
    }
    
    private void initAnimations() {
        this.control = this.model.getControl(AnimControl.class);
        this.channel = this.control.createChannel();
    }

    public void addToNode(Node node) {
        node.attachChild(model);
    }
    
    public void removeFromNode(Node node) {
        node.detachChild(model);
    }
    
    public void moveLeft(float tpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void moveRight(float tpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void moveForward(float tpf) {
        if(!this.forward && (this.channel.getAnimationName() == null || !this.channel.getAnimationName().equals("CubeWalk"))){
            this.forward = true;
            this.channel.setAnim("CubeWalk");
            this.channel.setLoopMode(LoopMode.Loop);
            this.model.move((float)((this.velX*tpf)/(0.5*this.weight)), 0, 0);
        }
    }

    public void moveBackwards(float tpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setRunning() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void releaseRunning() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void jump() {
        
    }

    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        if(animName.equals("CubeWalk") && !forward) {
            this.channel.setAnim("CubeStand");
            this.channel.setLoopMode(LoopMode.DontLoop);
        }
            
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
    }    

    public void leftStopped() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void rightStopped() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void forwardStopped() {
        this.channel.setLoopMode(LoopMode.DontLoop);
        this.channel.setAnim("CubeStand");
        this.forward = false;
    }

    public void backwardsStopped() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

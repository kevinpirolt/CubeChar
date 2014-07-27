/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kpirolt.mygame;

/**
 *
 * @author Kevin Pirolt
 */
public interface Reactor {
    void moveLeft(float tpf);
    void leftStopped();
    void moveRight(float tpf);
    void rightStopped();
    void moveForward(float tpf);
    void forwardStopped();
    void moveBackwards(float tpf);
    void backwardsStopped();
    void setRunning();
    void releaseRunning();
    void jump();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kpirolt.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;

/**
 *
 * @author Kevin Pirolt
 */
public class Game extends SimpleApplication{
    
    private Character character;
    private MasterListener ml;
    private TerrainQuad terrain;
    
    @Override
    public void simpleInitApp() {
        
        setUpLighting();
        loadCharacter();
        mapKeys();
        createHightmap();
        flyCam.setMoveSpeed(100f);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void setUpLighting() {
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -0.7f, -1).normalizeLocal());
        dl.setColor(new ColorRGBA(1f, 1f, 1f, 1.0f));
        rootNode.addLight(dl);
    }

    private void loadCharacter() {
        Spatial c = assetManager.loadModel("Models/cubeChar/cubeChar.mesh.xml");
        c.center();
        this.character = new Character(c, 10f, 10f, 1);
        character.addToNode(rootNode);
    }

    private void mapKeys() {
        this.ml = new MasterListener(character);
        inputManager.addMapping("left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("back", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("forward", new KeyTrigger(KeyInput.KEY_W));
        
        inputManager.addListener(this.ml.getManal(), 
                new String[]{"left", "right", "back", "forward"});
        inputManager.addListener(this.ml.getMal(), 
                new String[]{"left", "right", "back", "forward"});
    }

    private void createHightmap() {
        
        int patchSize = 65;
        terrain = HeightMapLoader.loadHeightMap("Textures/Hightmap/heightmap.png", 513, 
                                                                        "Textures/Hightmap/texturesplat.png", 
                                                                        "Textures/Hightmap/grass.jpg",
                                                                        "Textures/Hightmap/dirt.jpg", 
                                                                        "Textures/Hightmap/rock.jpg", 
                                                                        assetManager, patchSize);
        
        terrain.setLocalTranslation(0, -100, 0);
        terrain.setLocalScale(2f, 1f, 2f);
        rootNode.attachChild(terrain);
        
        TerrainLodControl control = new TerrainLodControl(terrain, getCamera());
        terrain.addControl(control);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kpirolt.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

/**
 *
 * @author Kevin Pirolt
 */
public class Game extends SimpleApplication{
    
    private Character character;
    private MasterListener ml;
    private TerrainQuad terrain;
    private Material mat_terrain;
    
    @Override
    public void simpleInitApp() {
        
        setUpLighting();
        loadCharacter();
        mapKeys();
        createHightmap();
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
        this.character = new Character(c);
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
        mat_terrain = new Material(assetManager, 
            "Common/MatDefs/Terrain/Terrain.j3md");
        
        mat_terrain.setTexture("Alpha", assetManager.loadTexture(
            "Textures/Hightmap/splat.png"));
        
        Texture grass = assetManager.loadTexture(
            "Textures/Hightmap/grass.jpg");
        grass.setWrap(WrapMode.Repeat);
        mat_terrain.setTexture("Tex1", grass);
        mat_terrain.setFloat("Tex1Scale", 64f);
        
        Texture dirt = assetManager.loadTexture(
            "Textures/Hightmap/dirt.jpg");
        dirt.setWrap(WrapMode.Repeat);
        mat_terrain.setTexture("Tex2", dirt);
        mat_terrain.setFloat("Tex2Scale", 32f);
        
        Texture rock = assetManager.loadTexture(
            "Textures/Hightmap/rock.jpg");
        rock.setWrap(WrapMode.Repeat);
        mat_terrain.setTexture("Tex3", rock);
        mat_terrain.setFloat("Tex3Scale", 128f);
        
        AbstractHeightMap hightmap = null;
        Texture heightMapImage = assetManager.loadTexture(
            "Textures/Hightmap/hightmap.png");
        hightmap = new ImageBasedHeightMap(heightMapImage.getImage());
        hightmap.load();
        hightmap.normalizeTerrain(10f);
        
        int patchSize = 65;
        terrain = new TerrainQuad("my terrain", patchSize, 513, hightmap.getHeightMap());
        
        terrain.setMaterial(mat_terrain);
        terrain.setLocalTranslation(0, -100, 0);
        terrain.setLocalScale(2f, 1f, 2f);
        rootNode.attachChild(terrain);
        
        TerrainLodControl control = new TerrainLodControl(terrain, getCamera());
        terrain.addControl(control);
    }
}

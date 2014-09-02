/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kpirolt.mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;

/**
 *
 * @author Kevin Pirolt
 */
public class HeightMapLoader {
    
    public static TerrainQuad loadHeightMap(String heightMapPath, int heightMapSize, AssetManager am, int patchSize) {
        Material mat_terrain = new Material(am, "Common/MatDefs/Light/Lighting.j3md"); 
        mat_terrain.setBoolean("UseMaterialColors", true); 
        mat_terrain.setColor("Ambient", ColorRGBA.Green); 
        mat_terrain.setColor("Diffuse", ColorRGBA.Green);
        mat_terrain.setTexture("Alpha", am.loadTexture(
            "Textures/Hightmap/texturesplat.png"));
        
        Texture heightMapImage = am.loadTexture(
            "Textures/Hightmap/heightmap.png");
        ImageBasedHeightMap hMap = new ImageBasedHeightMap(heightMapImage.getImage());
        return createTerrain(mat_terrain, heightMapSize, hMap, patchSize);
    }
    
    public static TerrainQuad loadHeightMap(String heightMapPath, int heightMapSize, String splatMap, String redTexturePath, String greenTexturePath, String blueTexturePath, AssetManager am, int patchSize) {
         
        Material mat_terrain = new Material(am, 
            "Common/MatDefs/Terrain/Terrain.j3md");
        
        mat_terrain.setTexture("Alpha", am.loadTexture(
            splatMap));
        
        Texture redTexture = am.loadTexture(
            redTexturePath);
        redTexture.setWrap(Texture.WrapMode.Repeat);
        mat_terrain.setTexture("Tex1", redTexture);
        mat_terrain.setFloat("Tex1Scale", 64f);
        
        Texture greenTexture = am.loadTexture(
            greenTexturePath);
        greenTexture.setWrap(Texture.WrapMode.Repeat);
        mat_terrain.setTexture("Tex2", greenTexture);
        mat_terrain.setFloat("Tex2Scale", 32f);
        
        Texture blueTexture = am.loadTexture(
            blueTexturePath);
        blueTexture.setWrap(Texture.WrapMode.Repeat);
        mat_terrain.setTexture("Tex3", blueTexture);
        mat_terrain.setFloat("Tex3Scale", 128f);
        
        Texture heightMapImage = am.loadTexture(
            heightMapPath);
        ImageBasedHeightMap hMap = new ImageBasedHeightMap(heightMapImage.getImage());
        return createTerrain(mat_terrain, heightMapSize, hMap, patchSize);
    }

    private static TerrainQuad createTerrain(Material mat_terrain, int heightMapSize, ImageBasedHeightMap hMap, int patchSize) {
        TerrainQuad terrain = new TerrainQuad("terrain", patchSize, heightMapSize, hMap.getHeightMap());
        terrain.setMaterial(mat_terrain);
        return terrain;
    }
}

package coursework.parminder_saini_190145026;

import org.lwjgl.opengl.GL11;

import GraphicsLab.FloatBuffer;

/**
 * Computes and applies the appropriate lightning settings, which includes: 
 * <li> global lighting
 * <li> scene light(s)
 * <li> material properties (i.e. response to lighting)
 */
public class LightingManager {
	
	// the type of object for which material properties need to be set
	public enum ObjectType {
		HOLE,
		ANDROID_DARK,
		ANDROID_LIGHT, 
		ANDROID_DEAD,
		EYE,
		BLACK
	}
	
	/**
	 * Initialises the global ambient light as well as all scene lights
	 */
	public static void initSceneLights() {
		// global ambient light 
        float globalAmbient[]   = {0.2f,  0.2f,  0.2f, 1f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));
	
        // create the light
        float diffuseSpecular0[]  = {0.6f,  0.6f, 0.6f, 1.0f}; 	//light-grey diffuse contribution 
        float ambient0[]  = {0.2f,  0.2f, 0.2f, 1.0f};  //dim ambient contribution  
        float position0[] = {35.0f, 25.0f, 20f, 1.0f};  //above and to the right of the viewpoint

        // provide the properties of light0 to OpenGL
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuseSpecular0));
  		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuseSpecular0));
        
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);
        
        // enable the lighting model
        GL11.glEnable(GL11.GL_LIGHTING);
        
        // enable automatic re-normalisation of surface normals
        GL11.glEnable(GL11.GL_NORMALIZE);
	}
	
	/**
	 * Applies specific material properties based on the ID that has
	 * been passed and the object type.
	 * 
	 * @param callerID the id number of the caller
	 * @param objectType the type of object for which material properties need to be applied
	 */
	public static void setMaterialProperties(int callerID, ObjectType objectType) {
		
		float shininess  = 0f;
		float[] ambient  = new float[4];
		float[] diffuse  = new float[4];
		float[] specular = new float[4];
			
		switch (objectType) {
		
			case ANDROID_LIGHT:				
				if (callerID == 1) { 		// 1st android, light-blue
					ambient = new float[] {0.23f, 0.69f, 0.77f, 1f};
					specular = new float[] {0.2f, 0.2f, 0.6f, 1f};
					
				} else if (callerID == 2) { // 2nd android, light yellow 
					ambient = new float[] {0.77f, 0.69f, 0.23f, 1f};
					specular = new float[] {0.4f, 0.4f, 0.1f, 1f};
			
				} else if (callerID == 3) { // 3rd android, light red
					ambient = new float[] {0.77f, 0.31f, 0.31f, 1f};
					specular = new float[] {0.6f, 0.2f, 0.2f, 1f};
					
				} else if (callerID == 4) { // 4th android, light green
					ambient = new float[] {0.29f, 0.89f, 0.29f, 1f};
					specular = new float[] {0.1f, 0.6f, 0.1f, 1f};
				}
				
				shininess = 100f;
				diffuse = ambient;				
				break;
				
			case ANDROID_DARK:	
				
				if (callerID == 1) {		// 1st android, dark blue 
					ambient = new float[] {0.18f, 0.34f, 0.51f, 1f};
					specular = new float[] {0.2f, 0.2f, 0.6f, 1f};
					
				} else if (callerID == 2) {	// 2nd android, dark yellow  (brownish)
					ambient = new float[] {0.51f, 0.34f, 0.18f, 1f};
					specular = new float[] {0.35f, 0.35f, 0.1f, 1f};
					
				} else if (callerID == 3) { // 3rd android, dark red
					ambient = new float[] {0.43f, 0.06f, 0.06f, 1f};
					specular = new float[] {0.6f, 0.2f, 0.2f, 1f};
					
				} else if (callerID == 4) { // 4th android, dark green
					ambient = new float[] {0.11f, 0.35f, 0.11f, 1f};
					specular = new float[] {0.1f, 0.6f, 0.1f, 1f};
				}
				
				shininess = 100f;
				diffuse = ambient;				
				break;
			
			// if the android has been hit, make it
			// appear dark blue and less shiny.
			case ANDROID_DEAD: 	
				shininess = 50f;
				ambient = new float[] {0.04f, 0.04f, 0.26f, 1f};
				diffuse = ambient;
				specular = new float[] {0.1f, 0.1f, 0.5f, 1f};
				break;
			
			// if the object is the android's eye...
			case EYE:
				shininess = 127f;
				ambient = new float[] {0.05f, 0.05f, 0.05f, 1f};
				diffuse = ambient;
				specular = new float[] {1f, 1f, 1f, 1f};
				break;
				
			// if the object is a ground hole,
			// make it appear dark blue with no shine.
			case HOLE:
				shininess = 0f;
				ambient = new float[] {0.02f, 0.02f, 0.1f, 1f};
				diffuse = ambient;
				specular = new float[] {0.0f, 0.0f, 0.0f, 1f};
				break;
				
			case BLACK:
			default:
				shininess = 0f;
				ambient = new float[] {0.0f, 0.0f, 0.0f, 1f};
				diffuse = ambient;
				specular = new float[] {0.0f, 0.0f, 0.0f, 1f};
				break;
		}
		
		// provide the calculated material properties to OpenGL
		GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular)); 
	}	
}

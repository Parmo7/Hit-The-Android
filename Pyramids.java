package coursework.parminder_saini_190145026;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.Colour;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

/**
 * Renders and updates two Pyramid objects 
 */
public class Pyramids {
	
	private final int PYRAMID_LIST = 5;  // display list ID for a pyramid 	
	private final Texture texture;		 // the texture to be applied on pyramids
	private final float scale;			 // the given animation scale
	
	private float rotationAngle = 0f;	 // the rotation angle
	
	public Pyramids(CS2150Coursework scene, float scale) {
		this.texture = scene.tryAndLoadTexture("coursework/parminder_saini_190145026/textures/pyramid_neon.bmp");
		this.scale = scale;
		
		// initialise the pyramid display list 
		GL11.glNewList(PYRAMID_LIST, GL11.GL_COMPILE);
			drawUnitPyramid();
		GL11.glEndList();
	}
	
	/**
	 * Renders two moving pyramids
	 */
	public void render() {
		GL11.glPushMatrix();
		{
			
			// scale, translate and rotate the first pyramid
			GL11.glPushMatrix();
			{
				GL11.glRotatef(rotationAngle + 10, 0f, 1f, 0f);
				GL11.glTranslatef(10f, 0f, 26.5f);
				GL11.glScalef(1.4f, 1.4f, 1.4f);
				GL11.glCallList(PYRAMID_LIST);
			}
			GL11.glPopMatrix();
			
			// scale, translate, rotate and then 
			// translate again the second pyramid
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(-3f, 0f, 47f);
				GL11.glRotatef(-rotationAngle + 50, 0f, 1f, 0f);
				GL11.glTranslatef(0f, 0f, -40f);
				GL11.glScalef(1.4f, 1.4f, 1.4f);
				GL11.glCallList(PYRAMID_LIST);
			}
			GL11.glPopMatrix();
			
		}
		GL11.glPopMatrix();		
	}	
	
	/**
	 * Update the rotation animation of all pyramids
	 */
	public void update() {
		float rotationScale = scale;
		rotationAngle = (float) ((rotationAngle + 1.0 * rotationScale) % 120);
	}
	
	/**
	 * Renders a unit pyramid positioned at the origin.
	 */
	private void drawUnitPyramid() {		
		GL11.glPushMatrix();
		{              
			Vertex v1 = new Vertex(-0.5f, 0.0f, 0.5f); // bottom left of base 
	    	Vertex v2 = new Vertex( 0.5f, 0.0f, 0.5f); // bottom right of base
	        Vertex v3 = new Vertex( 0.5f, 0.0f,-0.5f); // top right of base
	    	Vertex v4 = new Vertex(-0.5f, 0.0f,-0.5f); // top left of base
	    	Vertex vTop = new Vertex( 0.0f, 1.0f,  0.0f); // top vertex of pyramid
	    	
	    	Vertex[] vertices = new Vertex[] {v1, v2, v3, v4};	    	
	    	
	    	
	    	GL11.glPushAttrib(GL11.GL_LIGHTING_BIT); //push the current lightning settings into attribute stack
            GL11.glDisable(GL11.GL_LIGHTING);  		 //disable lighting
            
            // set the colour to white to make texture bright
            Colour.WHITE.submit();
            
            // enable textures and bind the appropriate image
            GL11.glEnable(GL11.GL_TEXTURE_2D);	
    		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
            
	    	// render all lateral faces: for each set of
    		// left vertex, right vertex and top vertex,
    		// draw a triangular polygon.
	    	for (int i = 0; i < vertices.length; i++) {
	    		Vertex vLeft = vertices[i];
				Vertex vRight = vertices[(i + 1) % vertices.length];
				
				GL11.glBegin(GL11.GL_POLYGON);
				{
					new Normal(vLeft.toVector(), vRight.toVector(), vTop.toVector()).submit();
					
		            GL11.glTexCoord2f(0.0f, 0.0f);
					vLeft.submit();
					
		            GL11.glTexCoord2f(1.0f, 0.0f);
					vRight.submit();
					
		            GL11.glTexCoord2f(0.49f, 0.97f);
					vTop.submit();
				}
				GL11.glEnd();
	    	}
	    	
	    	// disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}
	
	/**
	 * Cleans up all resources used (display lists and textures)
	 */
	public void cleanupResources() {
		GL11.glDeleteLists(PYRAMID_LIST, 1);
		GL11.glDeleteTextures(texture.getTextureID());
	}
}

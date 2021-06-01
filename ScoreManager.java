package coursework.parminder_saini_190145026;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.Colour;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

/**
 * Keeps track of the current score 
 * and renders it to the screen 
 */
public class ScoreManager {

	private static int score = 0; 				// current score
	private static Texture[] numbersTextures;	// array containing all score textures
	
	// keeps track of whether the score is to be displayed in white or red
	private static boolean isScoreWhite = true;
									
	// how much time the score remains the same for
	private static float scoreTimer = 0.0f;		
	
	/**
	 * Renders the score.
	 * 
	 * @param scene the scene in which the score is to be rendered
	 * @param planeSize the size of the sky plane
	 */
	public static void render(CS2150Coursework scene, float planeSize) {
		
		scoreTimer += 0.05f;
		
		// if not yet initialised, initialise the textures array
		if (numbersTextures == null) {
			numbersTextures = initScoreNumbers(scene);
		}
		
		GL11.glPushMatrix();
		{			
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT); // save the current lightning settings
            GL11.glDisable(GL11.GL_LIGHTING);		 // disable lighting
            
            // set the appropriate colour for the score, either
            // white or red (if the player has made a mistake)
            if (isScoreWhite) {
            	Colour.WHITE.submit();
            } else {
            	Colour.RED.submit();
            	if (scoreTimer > 5.0f) 	 // if the score has been red for enough
            		isScoreWhite = true; // time, set it back to white.
            }
            
            // enable texturing
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            
            // extract the 2 digits from the score
            char[] scoreString = String.format("%02d", score).toCharArray();
            int digit1 = Character.getNumericValue(scoreString[0]);
            int digit2 = Character.getNumericValue(scoreString[1]);
            
            // render the first digit
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, numbersTextures[digit1].getTextureID());
            GL11.glTranslatef(planeSize/4.5f, 11.9f, 0.1f);  // place it on the middle left 
            												 // of the sky plane
            GL11.glScalef(3.0f, 3.0f, 3.0f);
            drawUnitNumber();
            
            // render the second digit
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, numbersTextures[digit2].getTextureID());
            GL11.glTranslatef(0.8f, 0f, 0f);
            drawUnitNumber();

            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}
	
	/**
	 * Applies the assigned texture on a unit rectangular 
	 * polygon and draws it at the origin, facing towards
	 * the positive Z-axis.
	 */
	private static void drawUnitNumber() {
		
		Vertex v1 = new Vertex(-0.5f,-0.5f, 0.0f); // bottom left
		Vertex v2 = new Vertex( 0.5f,-0.5f, 0.0f); // bottom right
		Vertex v3 = new Vertex( 0.5f, 0.5f, 0.0f); // top right
		Vertex v4 = new Vertex(-0.5f, 0.5f, 0.0f); // top left
		
		// draw a rectangle
		GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v1.toVector(), v2.toVector(), v3.toVector(), v4.toVector()).submit();
            
            GL11.glTexCoord2f(0.0f, 0.0f);
            v1.submit();
            
            GL11.glTexCoord2f(1.0f, 0.0f);
            v2.submit();
            
            GL11.glTexCoord2f(1.0f, 1.0f);
            v3.submit();
            
            GL11.glTexCoord2f(0.0f, 1.0f);
            v4.submit();
        }
        GL11.glEnd();
		
	}
	
	/**
	 * Returns an array of textures displaying numbers.
	 * 
	 * @param scene the scene on which the textures are to be drawn
	 * @return the texture array containing all number textures
	 */
	public static Texture[] initScoreNumbers(CS2150Coursework scene) {
		Texture txt0 = scene.tryAndLoadTexture("coursework/parminder_saini_190145026/textures/numbers/0.bmp");
		Texture txt1 = scene.tryAndLoadTexture("coursework/parminder_saini_190145026/textures/numbers/1.bmp");
		Texture txt2 = scene.tryAndLoadTexture("coursework/parminder_saini_190145026/textures/numbers/2.bmp");
		Texture txt3 = scene.tryAndLoadTexture("coursework/parminder_saini_190145026/textures/numbers/3.bmp");
		Texture txt4 = scene.tryAndLoadTexture("coursework/parminder_saini_190145026/textures/numbers/4.bmp");
		Texture txt5 = scene.tryAndLoadTexture("coursework/parminder_saini_190145026/textures/numbers/5.bmp");
		Texture txt6 = scene.tryAndLoadTexture("coursework/parminder_saini_190145026/textures/numbers/6.bmp");
		Texture txt7 = scene.tryAndLoadTexture("coursework/parminder_saini_190145026/textures/numbers/7.bmp");
		Texture txt8 = scene.tryAndLoadTexture("coursework/parminder_saini_190145026/textures/numbers/8.bmp");
		Texture txt9 = scene.tryAndLoadTexture("coursework/parminder_saini_190145026/textures/numbers/9.bmp");
		
		return new Texture[] {txt0, txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9};
	}
	
	/**
	 * Deletes all resources that have been employed for the score
	 */
	public static void cleanupResources() {
		// cleans up all textures used
		for (Texture texture : numbersTextures) {
			int id = texture.getTextureID();
			GL11.glDeleteTextures(id);
		}
	}
	
	/**
	 * Increments the score. 
	 * Upon reaching 99, it resets to 0.
	 */
	public static void incrementScore() {
		score = (score + 1) % 100;
		isScoreWhite = true;
		scoreTimer = 0.0f;
	}

	/**
	 * Reduces the score (if higher than 0)
	 * and sets the score to be drawn in red.
	 */
	public static void decrementScore() {
		if (score > 0)
			score--;
		isScoreWhite = false;
		scoreTimer = 0.0f;
	}
	
	public static int getScore() {
		return score;
	}
	
	public static boolean isScoreWhite() {
		return isScoreWhite;
	}
}

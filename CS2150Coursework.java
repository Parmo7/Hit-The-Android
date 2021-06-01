/* CS2150Coursework.java
 * 
 * STUDENT NUMBER: 190145026
 * FULL NAME: Parminder Saini
 * COURSE: BSc Computer Science
 * YEAR OF STUDY: 2
 * DECLARATION: I confirm that this assignment is my own work.
 *
 * PLEASE NOTE:
 * In the below scene graph, labels put within brackets indicate that no 
 * physical object is drawn at that stage. Instead, these labels are
 * place holders for elements to be drawn further down the hierarchy. 
 * E.g. "(Score)" at line 21 indicates that the score element is not rendered
 * 		right away: the first digit is in fact drawn at the computed position, 
 * 		but the second digit is drawn after applying a translation. 
 * 	
 * SCENE GRAPH:
 *  Scene origin
 *  |
 *	+-- [S(50,1,50) T(25,0,25)] Ground plane
 * 	|
 * 	+-- [Rx(90) S(50,50,1) T(25,21,0)] Sky plane
 * 	|
 * 	+-- [S(3,3,3) T(11.11,11.9,0.1)] (Score)
 * 	|	|
 * 	|	+-- [] First score digit
 * 	|	|
 * 	|	+-- [T(0.8,0,0)] Second score digit
 * 	|
 * 	+-- [S(1.4,1.4,1.4) T(10,0,26.5) Ry(rotationAngle+10)] First pyramid
 * 	|
 * 	+-- [S(1.4,1.4,1.4) T(0,0,-40) Ry(-rotationAngle+50) T(-3,0,47)] Second pyramid
 * 	|
 * 	+-- [S(2,2,2) T(15.02,0,16)] (Android 1 and shelter)
 * 	|	|
 * 	|	+-- [] Ground Hole
 * 	|	|
 * 	|	+-- [Ry(spinningAngle) T(0,posY,0)] (Android 1 - Blue)
 * 	|		|
 * 	|		+-- [S(1,0.5,1)] Octagonal Body
 * 	|		|
 * 	|		+-- [S(0.6,0.6,0.6) T(0,0.5,0)] (Face)
 * 	|		|	|
 * 	|		|	+-- [S(1,0.5,1)] Octagonal Head
 * 	|		|	|
 * 	|		|	+-- [T(0.25,0.3,0.4)] (Eyes)
 * 	|		|		|
 * 	|		|		+-- [] Right eye
 * 	|		|		|
 * 	|		|		+-- [T(-0.5,0,0)] Left eye 
 * 	|		|
 * 	|		+-- [Rz(handsAngle) S(0.1,0.1,0.1) T(0.5,0.25,0)] (Right Arm)
 * 	|		|	|
 * 	|		|	+-- [Ry(90) T(0,0,0.7)] (Arm)
 * 	|		|		|
 * 	|		|		+-- [] Sphere, i.e. joint
 * 	|		|		|
 * 	|		|		+-- [] Cylinder, i.e. arm
 * 	|		|		|
 * 	|		|		+-- [T(0,0,3.5)] Sphere, i.e. hand
 * 	|		|
 * 	|		|
 * 	|		+-- [Rz(180-handsAngle) S(0.1,0.1,0.1) T(-0.5f,0.25,0)] (Left Arm)
 * 	|			|
 * 	|			+-- [Ry(90) T(0,0,0.7)] (Arm)
 * 	|				|
 * 	|				+-- [] Sphere, i.e. joint
 * 	|				|
 * 	|				+-- [] Cylinder, i.e. arm
 * 	|				|
 * 	|				+-- [T(0,0,3.5)] Sphere, i.e. hand
 * 	|
 * 	|
 *  +-- [S(2,2,2) T(22.03,0,8)] (Android 2 and shelter)
 * 	|	|
 * 	|	+-- [] Ground Hole
 * 	|	|
 * 	|	+-- [Ry(spinningAngle) T(0,posY,0)] (Android 2 - Yellow)
 * 	|		|
 * 	|		+-- [S(1,0.5,1)] Octagonal Body
 * 	|		|
 * 	|		+-- [S(0.6,0.6,0.6) T(0,0.5,0)] (Face)
 * 	|		|	|
 * 	|		|	+-- [S(1,0.5,1)] Octagonal Head
 * 	|		|	|
 * 	|		|	+-- [T(0.25,0.3,0.4)] (Eyes)
 * 	|		|		|
 * 	|		|		+-- [] Right eye
 * 	|		|		|
 * 	|		|		+-- [T(-0.5,0,0)] Left eye 
 * 	|		|
 * 	|		+-- [Rz(handsAngle) S(0.1,0.1,0.1) T(0.5,0.25,0)] (Right Arm)
 * 	|		|	|
 * 	|		|	+-- [Ry(90) T(0,0,0.7)] (Arm)
 * 	|		|		|
 * 	|		|		+-- [] Sphere, i.e. joint
 * 	|		|		|
 * 	|		|		+-- [] Cylinder, i.e. arm
 * 	|		|		|
 * 	|		|		+-- [T(0,0,3.5)] Sphere, i.e. hand
 * 	|		|
 * 	|		|
 * 	|		+-- [Rz(180-handsAngle) S(0.1,0.1,0.1) T(-0.5f,0.25,0)] (Left Arm)
 * 	|			|
 * 	|			+-- [Ry(90) T(0,0,0.7)] (Arm)
 * 	|				|
 * 	|				+-- [] Sphere, i.e. joint
 * 	|				|
 * 	|				+-- [] Cylinder, i.e. arm
 * 	|				|
 * 	|				+-- [T(0,0,3.5)] Sphere, i.e. hand
 * 	|
 * 	|
 *  +-- [S(2,2,2) T(25.64,0,27)] (Android 3 and shelter)
 * 	|	|
 * 	|	+-- [] Ground Hole
 * 	|	|
 * 	|	+-- [Ry(spinningAngle) T(0,posY,0)] (Android 3 - Red)
 * 	|		|
 * 	|		+-- [S(1,0.5,1)] Octagonal Body
 * 	|		|
 * 	|		+-- [S(0.6,0.6,0.6) T(0,0.5,0)] (Face)
 * 	|		|	|
 * 	|		|	+-- [S(1,0.5,1)] Octagonal Head
 * 	|		|	|
 * 	|		|	+-- [T(0.25,0.3,0.4)] (Eyes)
 * 	|		|		|
 * 	|		|		+-- [] Right eye
 * 	|		|		|
 * 	|		|		+-- [T(-0.5,0,0)] Left eye 
 * 	|		|
 * 	|		+-- [Rz(handsAngle) S(0.1,0.1,0.1) T(0.5,0.25,0)] (Right Arm)
 * 	|		|	|
 * 	|		|	+-- [Ry(90) T(0,0,0.7)] (Arm)
 * 	|		|		|
 * 	|		|		+-- [] Sphere, i.e. joint
 * 	|		|		|
 * 	|		|		+-- [] Cylinder, i.e. arm
 * 	|		|		|
 * 	|		|		+-- [T(0,0,3.5)] Sphere, i.e. hand
 * 	|		|
 * 	|		|
 * 	|		+-- [Rz(180-handsAngle) S(0.1,0.1,0.1) T(-0.5f,0.25,0)] (Left Arm)
 * 	|			|
 * 	|			+-- [Ry(90) T(0,0,0.7)] (Arm)
 * 	|				|
 * 	|				+-- [] Sphere, i.e. joint
 * 	|				|
 * 	|				+-- [] Cylinder, i.e. arm
 * 	|				|
 * 	|				+-- [T(0,0,3.5)] Sphere, i.e. hand
 * 	|
 *  |
 *  +-- [S(2,2,2) T(32.26,0,21)] (Android 4 and shelter)
 * 		|
 * 		+-- [] Ground Hole
 * 		|
 * 		+-- [Ry(spinningAngle) T(0,posY,0)] (Android 4 - Green)
 * 			|
 * 			+-- [S(1,0.5,1)] Octagonal Body
 * 			|
 * 			+-- [S(0.6,0.6,0.6) T(0,0.5,0)] (Face)
 * 			|	|
 * 			|	+-- [S(1,0.5,1)] Octagonal Head
 * 			|	|
 * 			|	+-- [T(0.25,0.3,0.4)] (Eyes)
 * 			|		|
 * 			|		+-- [] Right eye
 * 			|		|
 * 			|		+-- [T(-0.5,0,0)] Left eye 
 * 			|
 * 			+-- [Rz(handsAngle) S(0.1,0.1,0.1) T(0.5,0.25,0)] (Right Arm)
 * 			|	|
 * 			|	+-- [Ry(90) T(0,0,0.7)] (Arm)
 * 			|		|
 * 			|		+-- [] Sphere, i.e. joint
 * 			|		|
 * 			|		+-- [] Cylinder, i.e. arm
 * 			|		|
 * 			|		+-- [T(0,0,3.5)] Sphere, i.e. hand
 * 			|
 * 			|
 * 			+-- [Rz(180-handsAngle) S(0.1,0.1,0.1) T(-0.5f,0.25,0)] (Left Arm)
 * 				|
 * 				+-- [Ry(90) T(0,0,0.7)] (Arm)
 * 					|
 * 					+-- [] Sphere, i.e. joint
 * 					|
 * 					+-- [] Cylinder, i.e. arm
 * 					|
 * 					+-- [T(0,0,3.5)] Sphere, i.e. hand
 * 
 */

package coursework.parminder_saini_190145026;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;
import coursework.parminder_saini_190145026.LightingManager.ObjectType;

/**
 * Author: Parminder Saini
 * 
 * <p>
 *  GAME DESCRIPTION: <br>
 *  The game interface consists of 4 Androids located in a futuristic land
 *  with neon graphics and pyramids moving around. Androids will pop up from
 *  the ground for a limited time in random order. The goal for the player
 *  is to hit them (using number keys) as quickly as possible before they 
 *  get back to their safe shelter beneath the ground! If the attempt is 
 *  successful, the score increases. Otherwise, it is reduced. 
 * </p>
 *  
 * <p> 
 *  EXTRAS <br>
 *  While an Android is shown on the screen,  it is possible to make it 
 *  spin around its axis as well as change the direction/speed using 
 *  the designated key(s).
 * </p>
 * 
 * <p>
 * 	CONTROLS:
 * 	  <ul>
 * 		<li>Press the escape key to exit the application.
 * 		<li>Hold the X, Y and Z keys to view the scene along the x, y and z axis,
 * 		respectively
 * 		<li>While viewing the scene along the x, y or z axis, use the UP and DOWN
 * 		cursor keys to increase or decrease the viewpoint's distance from the scene
 * 		origin
 * 		<li>If an Android is active (i.e. rising or staying on the screen): 
 * 	  		<ul>
 * 				<li>press the R key to make the Android spin around its axis
 * 				<li>while spinning, press the LEFT or RIGHT cursor keys to change
 *   			the direction/speed of rotation
 * 				<li>press the S key to stop the rotation animation
 * 				<li>press the number key associated with the Android* 
 * 				(1, 2, 3 or 4) to hit it.
 * 	  		</ul>
 * 	  </ul>
 * * The number associated with an Android is given by its position along the y axis.
 * <br>That said, the numbering is as follows:
 * 	  <ul>
 * 		<li>1 > Blue Android
 * 		<li>2 > Yellow Android
 * 		<li>3 > Red Android
 * 		<li>4 > Green Android
 * 	  </ul> 
 * </p>
 */

public class CS2150Coursework extends GraphicsLab {
	
	// unique ID for instances of class CS2150Coursework
	private final static int ID = 0;
	
	private final static float GROUND_SIZE = 50f; 		  // width and height of the ground
	private final static float ANDROID_SCALE_FACTOR = 2f; // scaling factor for Androids
	private final static float GROUND_REPEAT_FACTOR = 6f; // number of repetitions for ground texture
	private final static float SKY_REPEAT_FACTOR = 1f;	  // number of repetitions for sky texture
	
	// IDs associated with display lists
	private final int groundPlaneList = 1;
	private final int skyPlaneList = 2;
	private final int groundHoleList = 3; //display list ID for circle from which Androids pop up 

	// instance of class Random for choosing the android to be shown on the screen 
	private final Random rdm = new Random();
	
	// textures for the ground and sky planes
	private Texture groundTexture;
	private Texture skyTexture;
		
	private Android android1; 	// first Android  (BLUE)
	private Android android2; 	// second Android (YELLOW)
	private Android android3; 	// third Android (RED)
	private Android android4; 	// fourth Android (GREEN)
	private Android[] androids; // array to store all Androids
	
	// pyramids that will decorate the scene
	private Pyramids pyramids;
	
	// Counts the number of Androids that have been activated so far
	private int androidPopUpCount = 0;
	/* Counts the successful hitting attempts made by the player.
	 * It should normally be the same as AndroidPopUpCount, unless
	 * the user has pressed the wrong number key. In that case, 
	 * it will be temporarily higher than androidPopUpCount. */
	private int attemptCount = 0;
	
	/**
	 * Initialises all relevant elements of the scene. 
	 */
	protected void initScene() throws Exception {
		
		// load textures for the ground and sky planes
		groundTexture = loadTexture("coursework/parminder_saini_190145026/textures/neon_grid.bmp");
		skyTexture = loadTexture("coursework/parminder_saini_190145026/textures/minimal_sky.bmp");
		
		// initialise all Android instances
		android1 = new Android(1, getAnimationScale());
		android2 = new Android(2, getAnimationScale());
		android3 = new Android(3, getAnimationScale());
		android4 = new Android(4, getAnimationScale());
		// store all Androids in an array
		androids = new Android[] {android1, android2, android3, android4};
		
		// initialise Pyramids object
		pyramids = new Pyramids(this, getAnimationScale());

		// initialise all display lists
		initDisplayLists();
		// initialise scene lights
		LightingManager.initSceneLights();
	}

	/**
	 * Prepares all display lists for later use
	 */
	private void initDisplayLists() {
		GL11.glNewList(groundPlaneList, GL11.GL_COMPILE); 
			drawUnitPlane(GROUND_REPEAT_FACTOR);	
		GL11.glEndList();
		
		GL11.glNewList(skyPlaneList, GL11.GL_COMPILE); 
			drawUnitPlane(SKY_REPEAT_FACTOR);	
		GL11.glEndList();
		
		GL11.glNewList(groundHoleList, GL11.GL_COMPILE);
			drawCircle(10.0f);
		GL11.glEndList();		
	}
	
	/**
	 * Draws all relevant elements of the scene
	 */
	protected void renderScene() {
		GL11.glPushMatrix();
		{	
			// render the ground and sky planes
			renderGroundAndSky();
			
			// render all pyramids
			pyramids.render();
			
			// render the score
			ScoreManager.render(this, GROUND_SIZE);;
			
			// position, scale and draw the 1st android
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(GROUND_SIZE/3.33f, 0, 16f);
				GL11.glScalef(ANDROID_SCALE_FACTOR, ANDROID_SCALE_FACTOR, ANDROID_SCALE_FACTOR);
				// set the appropriate material properties for the ground hole
				LightingManager.setMaterialProperties(ID, ObjectType.HOLE);
				GL11.glCallList(groundHoleList);
				android1.draw();
			}
			GL11.glPopMatrix();
			
			// position, scale and draw the 2nd android
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(GROUND_SIZE/2.27f, 0, 8f);
				GL11.glScalef(ANDROID_SCALE_FACTOR, ANDROID_SCALE_FACTOR, ANDROID_SCALE_FACTOR);
				// set the appropriate material properties for the ground hole
				LightingManager.setMaterialProperties(ID, ObjectType.HOLE);
				GL11.glCallList(groundHoleList);
				android2.draw();
			}
			GL11.glPopMatrix();
			
			// position, scale and draw the 3rd android
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(GROUND_SIZE/1.95f, 0, 27f);
				GL11.glScalef(ANDROID_SCALE_FACTOR, ANDROID_SCALE_FACTOR, ANDROID_SCALE_FACTOR);
				// set the appropriate material properties for the ground hole
				LightingManager.setMaterialProperties(ID, ObjectType.HOLE);
				GL11.glCallList(groundHoleList);
				android3.draw();
			}
			GL11.glPopMatrix();
			
			// position, scale and draw the 4th android
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(GROUND_SIZE/1.55f, 0, 21f);
				GL11.glScalef(ANDROID_SCALE_FACTOR, ANDROID_SCALE_FACTOR, ANDROID_SCALE_FACTOR);
				// set the appropriate material properties for the ground hole
				LightingManager.setMaterialProperties(ID, ObjectType.HOLE);
				GL11.glCallList(groundHoleList);
				android4.draw();
			}
			GL11.glPopMatrix();			
		}
		GL11.glPopMatrix();
	}
	
	/**
	 * Renders the planes for the ground and the sky
	 */	
	private void renderGroundAndSky() {
		GL11.glPushMatrix(); 
		{
			// push current lighting settings into attribute stack
			// and temporarily disable lighting
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);             
            GL11.glDisable(GL11.GL_LIGHTING);
            
            // check if the score being displayed is white
            // (i.e. the user has not made an error)
            if (ScoreManager.isScoreWhite())
            	Colour.WHITE.submit(); // if so, set colour to white to make the texture bright
            else 
            	Colour.RED.submit();   // otherwise, set colour to red
            
            // enable texturing
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            // set the texture to repeat itself
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
            
            // position, scale and draw the ground plane using its display list
            GL11.glPushMatrix();
            {
            	// bind the appropriate texture
	            GL11.glBindTexture(GL11.GL_TEXTURE_2D, groundTexture.getTextureID());
	            GL11.glTranslatef(GROUND_SIZE/2, 0.0f, GROUND_SIZE/2);
	            GL11.glScaled(GROUND_SIZE, 1.0f, GROUND_SIZE);
	            GL11.glCallList(groundPlaneList);
            }
            GL11.glPopMatrix();
            
            // position, scale and draw the back plane (i.e. sky) using its display list
            GL11.glPushMatrix();
            {
            	// bind the appropriate texture
	            GL11.glBindTexture(GL11.GL_TEXTURE_2D, skyTexture.getTextureID());
	            GL11.glTranslatef(GROUND_SIZE/2, 21.0f, 0.0f);
	            GL11.glScaled(GROUND_SIZE, GROUND_SIZE, 1);
	            GL11.glRotatef(90f, 1f, 0f, 0f);
	            GL11.glCallList(skyPlaneList);
            }
            GL11.glPopMatrix();            

            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
		}		
		GL11.glPopMatrix();
	}
	
	/**
	 * Renders a unit plane (positioned at the origin, facing upwards)
	 * and applies a texture to it <i>n</i> times, <i>n</i> being the 
	 * float value passed as a parameter.
	 * 
	 * @param repeatFactor the number of repetitions of the texture 
	 * being applied to the plane 
	 */
	private void drawUnitPlane(float repeatFactor) {
		
		Vertex v1 = new Vertex(-0.5f, 0.0f, 0.5f); 	// bottom left vertex
		Vertex v2 = new Vertex( 0.5f, 0.0f, 0.5f); 	// bottom right vertex
        Vertex v3 = new Vertex( 0.5f, 0.0f,-0.5f); 	// top right vertex
        Vertex v4 = new Vertex(-0.5f, 0.0f,-0.5f); 	// top left vertex
        
        // draw the plane so that it faces upwards
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v1.toVector(), v2.toVector(), v3.toVector(), v4.toVector()).submit();
            
            GL11.glTexCoord2f(0.0f, 0.0f);
            v1.submit();
            
            GL11.glTexCoord2f(repeatFactor, 0.0f);
            v2.submit();
            
            GL11.glTexCoord2f(repeatFactor, repeatFactor);
            v3.submit();
            
            GL11.glTexCoord2f(0.0f, repeatFactor);
            v4.submit();
        }
        GL11.glEnd();
        
        // in case the user is viewing an axis, draw this plane as lines
        if (isViewingAxis()) {
            GL11.glPushAttrib(GL11.GL_TEXTURE_2D);  // save current texture settings
	        GL11.glDisable(GL11.GL_TEXTURE_2D); 	// disable textures
	        GL11.glBegin(GL11.GL_LINE_LOOP); 
	        {
	        	v1.submit();
                v2.submit();
                v3.submit();
                v4.submit();
            }
            GL11.glEnd();
            GL11.glPopAttrib();
        }
	}

	/**
	 * Renders a circle with the given radius, positioned at the origin
	 * 
	 * @param radius the radius of the circle to be drawn
	 */
	private void drawCircle(float radius) {
		GL11.glPushMatrix();
		{		
			// translate the circle higher by a very small value 
			// to separate it from the surface
			GL11.glTranslatef(0.0f, 0.01f, 0.0f);
			
			// compute the coordinates of each vertex 
			// and store them in an array list 
			ArrayList<Vertex> vertices = new ArrayList<>();
			for (int i = 0; i <= 300; i++) {
				float angle =  (float) (2 * Math.PI * i / 300);
				float x = (float) Math.sin(angle);
				float z = (float) Math.cos(angle);
				Vertex v = new Vertex(x, 0, z);
				vertices.add(v);
			}
			
			// draw the circle
			GL11.glBegin(GL11.GL_POLYGON);
			
				new Normal(vertices.get(0).toVector(), 
						vertices.get(1).toVector(), 
						vertices.get(2).toVector()).submit();;
				
				// render all vertices previously stored in the array list 
				for (Vertex v : vertices) 
					v.submit();				
				
			GL11.glEnd();
			
		} 
		GL11.glPopMatrix();
	}

	protected void updateScene() {
		
		pyramids.update(); // update animations on pyramids
		
		boolean isAnyAndroidActiveOrRising = false;
		// update all Androids
		for (Android android : androids) {
			android.update();
			if (android.isActiveOrRising()) {		// check if any Android is currently active or 
				isAnyAndroidActiveOrRising = true; 	// rising and update the variable accordingly
			}
		}
		
		// if no Android is active/rising, activate one
		if (!isAnyAndroidActiveOrRising) {
			// Search for a suitable Android to activate. 
			// If the chosen android is descending, search for a new one. 
			Android chosen;
			do {
				int rdmIdx = rdm.nextInt(androids.length);
				chosen = androids[rdmIdx];
			} while (chosen.isDescending());
			chosen.popUp(); // activate the chosen Android 
			
			// increase the counter of Androids activated so far by 1
			androidPopUpCount++;
			// reset the attempt counter so that it matches the number of active androids
			attemptCount = androidPopUpCount;
		}
		
		// update the amount of time for which Androids are active
		updateActiveTimer();
	}
	
	/**
	 * Depending on the current <i>score</i>, updates the active timer of all Androids.
	 * (i.e. the amount of time they stay on the screen for before starting descending)
	 */
	private void updateActiveTimer() {
		float activeTimer;
		
		// decrease the active timer as score increases
		if (ScoreManager.getScore() >= 80) { 
			activeTimer = 0.5f;
		} else if (ScoreManager.getScore() >= 60) {
			activeTimer = 1.0f;
		} else if (ScoreManager.getScore() >= 40) {
			activeTimer = 2.0f;
		} else if (ScoreManager.getScore() >= 20) {
			activeTimer = 4.0f;
		} else { 
			activeTimer = 8.0f;
		}
		
		// assign the computed active timer to all Androids
		for (Android android : androids) {
			android.setActiveTimer(activeTimer);
		}
	}

	/**
	 * Propagates the input checking to all Androids. 
	 * Also checks if any of the number keys 1, 2, 3 or 4
	 * is pressed and tries to hit the associated Android. 
	 */
	protected void checkSceneInput() {
		for (Android android: androids) {  // propagates the input check to all Androids
			android.checkInput();
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
			tryToHitAndroid(android1);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
			tryToHitAndroid(android2);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
			tryToHitAndroid(android3);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
			tryToHitAndroid(android4);
		}
	}
	
	/**
	 * Checks if the given Android is active/rising:
	 * <li>If it is, hit the Android.
	 * <li>If not, record the failed attempt.
	 *  
	 * @param android the Android that is to be hit
	 */
	private void tryToHitAndroid(Android android) {
		
		// if the Android is active or rising
		if (android.isActiveOrRising()) { 
			android.hit(); // hit the Android
			ScoreManager.incrementScore(); //increase the score
			
		// otherwise, if the Android is not descending and a
		// failed attempt to hit it has not already been made
		} else if (!android.isDescending() && attemptCount == androidPopUpCount) {
			ScoreManager.decrementScore(); //reduce the score
			attemptCount++; //record the failed attempt
		}
	}

	/**
	 * Adjusts the perspective projection and camera settings
	 */	
	protected void setSceneCamera() {
		// call the default behaviour from class GraphicsLabs
		super.setSceneCamera();
		
		// set the viewpoint using gluLookAt
		GLU.gluLookAt( GROUND_SIZE/2, 6.0f,  40f,	//viewer's position
					   GROUND_SIZE/2, 0.0f, 0.0f,	//view point location
					   0.0f, 1.0f, 0.0f );			//view-up vector
	}

	/**
	 * Disposes all relevant resources used throughout the scene 
	 */
	protected void cleanupScene() {
		// delete all display lists
		GL11.glDeleteLists(groundPlaneList, 3);
		
		// delete all textures
		GL11.glDeleteTextures(groundTexture.getTextureID());
		GL11.glDeleteTextures(skyTexture.getTextureID());
		
		// make the ScoreManager class as well as the
		// pyramids instance clean up their resources 
		ScoreManager.cleanupResources(); 
		pyramids.cleanupResources();
	}
	
	/**
	 * Tries to load a texture with the specified String path.
	 * If not found, prints the stack trace and returns null.
	 * 
	 * @param path the path where the texture file is located
	 * @return the texture instance created
	 */
	public Texture tryAndLoadTexture(String path) {
		try {
			return loadTexture(path);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Entry point for the game interface.
	 * 
	 * @param args any arguments to run the program
	 */
	public static void main(String args[]) {
		String title = "Hit the Android!";
		new CS2150Coursework().run(WINDOWED, title, 0.01f);
	}
}

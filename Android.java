package coursework.parminder_saini_190145026;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;

import GraphicsLab.Normal;
import GraphicsLab.Vertex;
import coursework.parminder_saini_190145026.LightingManager.ObjectType;

/** 
 * <p>
 * 	A unit robot, initially positioned below the origin 
 * 	and facing towards the positive z-axis. <br> It has
 *  4 possible states (<i>idle, rising, active, descending
 * 	</i>) and moves according to the current state.
 * </p>
 */
public class Android {	
	
	// the default scale factor by which the program animation 
	// scale is to be multiplied
	private static final float DEFAULT_SPINNING_FACTOR = 6;
			
	// the default initial Y position (below the origin)
	private static final float DEFAULT_IDLE_POS = -1.0f;
	
	// the default time after which the android starts descending
	private static final float DEFAULT_ACTIVE_TIMER = 3.0f;

	private final int id; 	// ID assigned to this instance
	private final float scale;  // the given animation scale
	
	private enum State { IDLE, RISING, ACTIVE, DESCENDING }  // possible states of the android
	private State currentState = State.IDLE;
	private boolean isSinking = false; 		// keeps track of whether the android has been hit
	private float stateTimer = 0.0f; 		// time passed since the start of the current state 
	private float activeTimer = DEFAULT_ACTIVE_TIMER;
	
	// variables for the translation animation
	private float posY = DEFAULT_IDLE_POS; 	// position along the Y axis

	// variables for the spinning animation
	private boolean isSpinning = false;
	private float spinningAngle = 0f;
	private float spinningFactor = DEFAULT_SPINNING_FACTOR;
	
	// variables for the hands rotation animation
	private float handsAngle = 0f;
	private boolean handsGoingUp = true;
	
	/**
	 * Creates an instance of android, given an ID and an animation scale
	 * 
	 * @param id the ID to be assigned to this android
	 * @param scale the animation scale of the game/program
	 */
	public Android(int id, float scale ) {
		this.id = id;		
		this.scale = scale;
	}
	
	/**
	 * Renders the android based on current state and animation parameters
	 */
	public void draw() {	
		// check if the Android is idle: if not, draw it
		if (currentState != State.IDLE) {			
			float bodyHeight = 0.5f;
			
			// translate and rotate the android according 
			// to the current animation parameters
			GL11.glTranslatef(0.0f, posY, 0.0f);
			GL11.glRotatef(spinningAngle, 0.0f, 1.0f, 0.0f);
			GL11.glPushMatrix();
			{
				// scale and draw the body
				GL11.glPushMatrix();
				{
					GL11.glScalef(1f, bodyHeight, 1f);
					drawUnitOctagonalBody();
				}
				GL11.glPopMatrix();
				
				// translate, scale and draw the face
				GL11.glPushMatrix();
				{
					// position it above the body and scale it down
					GL11.glTranslatef(0.0f, bodyHeight, 0.0f); 
					GL11.glScalef(0.6f, 0.6f, 0.6f);
					
					// scale and draw the head
					GL11.glPushMatrix();
					{
						GL11.glScalef(1f, 0.5f, 1f);
						drawUnitOctagonalBody();
					}
					GL11.glPopMatrix();

					// set the appropriate material properties for the eyes
					LightingManager.setMaterialProperties(id, ObjectType.EYE);
					
					// translate and draw the eyes
					GL11.glTranslatef(0.25f, 0.3f, 0.4f);
					new Sphere().draw(0.08f, 10, 10); 		// right eye
	
					GL11.glTranslatef(-0.5f, 0.0f, 0.0f); 	
					new Sphere().draw(0.08f, 10, 10);		// left eye
				}
				GL11.glPopMatrix();
				
				// rotate (based on the current rotation angle), 
				// scale and translate both arms.					
				GL11.glPushMatrix(); //right arm
				{
					GL11.glTranslatef(0.5f, 0.25f, 0.0f);
					GL11.glScalef(0.1f, 0.1f, 0.1f);
					GL11.glRotatef(handsAngle, 0f, 0f, 1f);
					drawArm();
				}
				GL11.glPopMatrix();
				
				GL11.glPushMatrix(); //left arm
				{
					GL11.glTranslatef(-0.5f, 0.25f, 0.0f);
					GL11.glScalef(0.1f, 0.1f, 0.1f);
					GL11.glRotatef(180-handsAngle, 0f, 0f, 1f);
					drawArm();
				}
				GL11.glPopMatrix();			
			}
			GL11.glPopMatrix();
		}
	}
	
	/**
	 * Updates the state timer, the animation parameters 
	 * and the state of the android, if required
	 */
	public void update() {
		
		stateTimer += 0.005f;  // increase the state timer
		
		if (currentState == State.ACTIVE) {
			updateAnimationParameters();	
			
			// if the android has been active for 
			// enough time, start descending
			if (stateTimer >= activeTimer)
				setState(State.DESCENDING);
			
		} else if (currentState == State.RISING) {
			updateAnimationParameters();
			posY += 0.4f * scale; // increase the Y position
			
			if (posY >= 0f) 			// max position reached,
				setState(State.ACTIVE); // change state to active

		} else if (currentState == State.DESCENDING) {
			updateAnimationParameters();
			posY -= 0.4f * scale; // decrease the Y position
			
			if (posY <= DEFAULT_IDLE_POS) // min position reached,
				setState(State.IDLE);	  // change state to idle
		}
	}
	
	/**
	 * Updates the parameters for the spinning and 
	 * hand rotation animations
	 */
	private void updateAnimationParameters() {
				
		if (handsGoingUp) { 		  // If hands are going up, increase the angle 
			handsAngle += 20 * scale; 		 
		} else { 					  // Otherwise, decrease the angle
			handsAngle -= 20 * scale; 	
		}
		
		if (handsAngle > 45) {     	   // Max angle reached, change direction to down
			handsGoingUp = false;
		} else if (handsAngle < -15) { // Min angle reached, change direction to up
			handsGoingUp = true;
		}
		
		// if the android is spinning, update spinning parameters as well
		if (isSpinning) {
			spinningAngle = (float) ((spinningAngle + scale * spinningFactor) % 360);
		}	
	}
	
	/**
	 * If the android is currently active, checks for user input
	 * and reacts accordingly.
	 */
	public void checkInput() {
		if (currentState == State.ACTIVE) {
			if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
				isSpinning = true;  // set spinning animation to true
				
			} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				isSpinning = false; // set spinning animation to false
				spinningFactor = DEFAULT_SPINNING_FACTOR; // reset spinning speed
				
			} else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				spinningFactor -= 0.5f;  // reduce spinning speed
				
			} else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) ) { 
				spinningFactor += 0.5f;  // increase spinning speed
			}
		}
	}
	
	/**
	 * Draws a unit octagonal-shaped body positioned at the origin 
	 */
	private void drawUnitOctagonalBody() {
		
		// the absolute value of the x and y coordinate of 
		// the 4 middle vertices in an octagonal polygon
		float midV = (float) Math.sqrt((0.5f*0.5f)/2.0f);
		float height;
		
		
		// draw the bottom face
		height = 0.0f;		
		Vertex v1 = new Vertex( 0.0f, height, 0.5f); // bottom vertex
		Vertex v2 = new Vertex( midV, height, midV); // bottom-right middle vertex 
		Vertex v3 = new Vertex( 0.5f, height, 0.0f); // right vertex
		Vertex v4 = new Vertex( midV, height,-midV); // top-right middle vertex
		Vertex v5 = new Vertex( 0.0f, height,-0.5f); // top vertex
		Vertex v6 = new Vertex(-midV, height,-midV); // top-left middle vertex
		Vertex v7 = new Vertex(-0.5f, height, 0.0f); // left vertex
		Vertex v8 = new Vertex(-midV, height, midV); // bottom-left middle vertex
		Vertex[] bottomVertices = new Vertex[] {v1, v2, v3, v4, v5, v6, v7, v8};
		
		// set the appropriate material properties
		if (!isSinking)
			LightingManager.setMaterialProperties(id, ObjectType.ANDROID_DARK);
		else 
			LightingManager.setMaterialProperties(id, ObjectType.ANDROID_DEAD);
		
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v6.toVector(), v4.toVector(), v2.toVector()).submit();			
			v8.submit();
			v7.submit();
			v6.submit();
			v5.submit();
			v4.submit();
			v3.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();

		
		// draw the top triangular faces
		height = 1.0f;
		Vertex v9  = new Vertex( 0.0f, height, 0.5f);
		Vertex v10 = new Vertex( midV, height, midV);
		Vertex v11 = new Vertex( 0.5f, height, 0.0f);
		Vertex v12 = new Vertex( midV, height,-midV);
		Vertex v13 = new Vertex( 0.0f, height,-0.5f);
		Vertex v14 = new Vertex(-midV, height,-midV);
		Vertex v15 = new Vertex(-0.5f, height, 0.0f);
		Vertex v16 = new Vertex(-midV, height, midV);
		Vertex[] topVertices = new Vertex[] {v9, v10, v11, v12, v13, v14, v15, v16};
		
		// the highest point of the body (center vertex)
		Vertex vCenter = new Vertex(0f, height + 0.3f, 0f); 
		
		// for each set of left vertex, right vertex  
		// and center vertex, draw a triangle
		for (int i = 0; i < topVertices.length; i++) {
			Vertex vLeft = topVertices[i];
			Vertex vRight = topVertices[ (i + 1) % topVertices.length ];
			
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(vLeft.toVector(), vRight.toVector(), vCenter.toVector()).submit();;
				vLeft.submit();
				vRight.submit();
				vCenter.submit();
			}
			GL11.glEnd();
		}
		
		// set the appropriate material properties and
		// draw all the lateral faces.	
		if (!isSinking)
			LightingManager.setMaterialProperties(id, ObjectType.ANDROID_LIGHT);
		else
			LightingManager.setMaterialProperties(id, ObjectType.ANDROID_DEAD);
		
		// for each set of bottom left, bottom right, top right and 
		// top left vertex draw a rectanglular polygon
		for (int i = 0, j = 0; i < bottomVertices.length; i++, j++) {
			
			Vertex vBottomLeft = bottomVertices[i];
			Vertex vBottomRight = bottomVertices[(i + 1) % bottomVertices.length];
			Vertex vTopRight = topVertices[(j + 1) % topVertices.length];
			Vertex vTopLeft = topVertices[j];
			
			GL11.glBegin(GL11.GL_POLYGON); 
			{
				new Normal(vBottomLeft.toVector(), vBottomRight.toVector(), vTopRight.toVector(), vTopLeft.toVector()).submit();
				vBottomLeft.submit();
				vBottomRight.submit();
				vTopRight.submit();
				vTopLeft.submit();
			}
			GL11.glEnd();
		}
	}

	/**
	 * Draws an arm, consisting of a sphere (joint), 
	 * a cylinder (arm) and another sphere (hand). 
	 */
	private void drawArm() {
    	
    	float radius = 0.7f; // radius of spheres and cylinder
    	int slices = 10; 	 // stacks and sectors of spheres/cylinder  
    	float length = 3.5f; // length of the cylinder 
    	
    	
    	GL11.glPushMatrix(); 
    	{    	
    		GL11.glTranslatef(0.0f, 0.0f, radius);
        	GL11.glRotatef(90f, 0f, 1f, 0f);
        	
    		// set the appropriate material properties
    		// and draw 1st sphere (i.e. joint)
    		if (!isSinking)
    			LightingManager.setMaterialProperties(id, ObjectType.ANDROID_DARK);
    		else
    			LightingManager.setMaterialProperties(id, ObjectType.ANDROID_DEAD);
    		
    		new Sphere().draw(radius, slices, slices);
    		
    		// draw cylinder (i.e. arm)
   			new Cylinder().draw(radius, radius, length, slices, slices);
    		
   			// set the appropriate material properties,
    		// translate and draw 2nd sphere (i.e. hand)
    		if (!isSinking)
    			LightingManager.setMaterialProperties(id, ObjectType.ANDROID_LIGHT);
    		else 
    			LightingManager.setMaterialProperties(id, ObjectType.ANDROID_DEAD);
    			
    		GL11.glTranslatef(0.0f, 0.0f, length);
			new Sphere().draw(radius, slices, slices);
    	}
    	GL11.glPopMatrix(); 	
	}
	
	/**
	 * Updates the current state with the given one 
	 * and resets the state timer.
	 * 
	 * @param state the new state to be assigned to the android
	 */
	private void setState(State state) {
		currentState = state;
		stateTimer = 0; // reset timer
		
		if (state == State.IDLE) {
			isSinking = false; // reset the variable
		}
	}

	/**
	 * Updates the state of the android to <i>rising</i>.
	 */
	public void popUp() {
		setState(State.RISING);
	}

	/**
	 * Updates the state of the android to <i>descending</i>
	 * and records that it has been hit.
	 */
	public void hit() {
		isSinking = true;
		setState(State.DESCENDING);
	}
		
	public void setActiveTimer(float activeTimer) {
		this.activeTimer = activeTimer;
	}
	
	public int getID() {
		return id;
	}
	
	public boolean isActiveOrRising() {
		return currentState == State.ACTIVE || currentState == State.RISING;
	}
	
	public boolean isDescending() {
		return currentState == State.DESCENDING;
	}	
}

package edu.virginia.engine.display;

//import javafx.scene.transform.Affine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
//import javax.xml.bind.Element;

/**
 * A very basic display object for a java based gaming engine
 * change
 * */
public class DisplayObject {

	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	private BufferedImage displayImage;

	//defines whether or not the object is visible
	private boolean visible;

	//float which defines how transparent to draw this object.
	private float alpha, oldAlpha;

	//double which scales the image up or down
	private double scalex, scaley;

	//Point to store x,y where this object will be drawn
	private Point position;

	//The point, relative to the UI upper left corner of the image that is the origin of this object.
	// The object rotates around this point.
	private Point pivotPoint;

	//change in x
	private double dx, dy;

	//current change in velocity
	private double dvx, dvy;

	private double velocity;

	//defines the amount in radians to rotate this object.
	private double rotation;

	private int dFromParent;

	private double mass;

	private DisplayObject parent;

	//defines rotation speed set when init
	private double rotationSpeed;

	public void setMass(double mass){
		this.mass = mass;
	}

	public double getMass(){
		return this.mass;
	}

	public void setdFromParent(int dFromParent) {
		this.dFromParent = dFromParent;
	}

	public int getdFromParent() {
		return dFromParent;
	}

	public void setRotationSpeed(double rotationSpeed1){
		this.rotationSpeed = rotationSpeed1;
	}

	public double getRotationSpeed() {
		return rotationSpeed;
	}

	public void setParent(DisplayObject parent) {
		this.parent = parent;
	}

	public DisplayObject getParent() {
		return parent;
	}

	public Rectangle getHitbox() {

		Rectangle toret = new Rectangle(0, 0, //(int)Math.round(getPosition().getX()), (int) Math.round(getPosition().getY()),
				getUnscaledWidth(), getUnscaledHeight() );
		//System.out.print(getId() + ":");
		//System.out.println(toret);
		return toret;
	}

	public boolean collidesWith(DisplayObject other) {
		AffineTransform at = new AffineTransform();
		AffineTransform oat = new AffineTransform();
		hitAt(at);
		other.hitAt(oat);
		Shape me = at.createTransformedShape(getHitbox());
		Shape them = oat.createTransformedShape(other.getHitbox());
		Area a = new Area(me);
		Area b = new Area(them);
		a.intersect(new Area(them));
		return !a.isEmpty();
	}

	public Point localToGlobal(Point p) {
		Point me = getPosition(); //assume p is somewhere inside me. return me + p
		return new Point((int) Math.round(me.getX() + p.getX()), (int)Math.round(me.getY() + p.getY()));
	}

	public Point globalToLocal(Point p) {
		Point me = getPosition(); //assume p is an abstraction above me. p - me
		return new Point((int) Math.round(p.getX() - me.getX()), (int)Math.round(p.getY() - me.getY()));
	}

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 * Initialize visible to true, alpha to 1.0f, oldAlpha to 0.0f, and scaleX/scaleY to 1.0.
	 * Initialize position to (0, 0), pivotPoint to (0,0), and rotation to 0
	 */

	private void initPartner2() {
		visible = true;
		alpha = 1.0f;
		oldAlpha = 0.0f;
		scalex = 1.0;
		scaley = 1.0;
	}

	private void initPartner1() {
		position = new Point(0,0);
		pivotPoint = new Point(0,0);
		rotation = 0.0;
		rotationSpeed = 1.0;
		dx = 0;
		dy = 0;
		dvx = 0;
		dvy = 0;
		velocity = 0;
		mass = 0;
	}


	public DisplayObject(String id) {
		this.setId(id);
		initPartner2();
		initPartner1();
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
		initPartner2();
		initPartner1();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	/**
	 * partner 2 get/sets
	 *
	 */
	public void setVisible(boolean vis) { visible = vis; }
	public boolean getVisible() {return visible;}
	public void setAlpha(float a) {alpha = a;}
	public float getAlpha() {return alpha;}
	public void setOldAlpha(float a) {oldAlpha = a;}
	public float getOldAlpha() {return oldAlpha;}
	public double getScalex() { return scalex; }
	public void setScalex(double toset) { scalex = toset; }
	public double getScaley() { return scaley; }
	public void setScaley(double toset) { scaley = toset; }

	/**
	 * partner 1 get/sets
	 *
	 */

	public void setPosition (Point p){position = p;}
	public Point getPosition(){return position;}
	public void setPivotPoint(Point pp){pivotPoint = pp;}
	public Point getPivotPoint() {return pivotPoint;}
	public void setRotation(double r){rotation = r;}
	public double getRotation(){return rotation;}

	public double getDx() { return dx; }
	public double getDy() { return dy; }
	public void setDx(double Dx) { this.dx = Dx; }
	public void setDy(double Dy) { this.dy = Dy; }

	public double getDvx() { return dvx; }
	public double getDvy(){ return dvy; }
	public void setDvx (double Dvx){ this.dvx = Dvx; }
	public void setDvy (double Dvy) {this.dvy = Dvy; }
	public void resetDvx() {this.dvx = 0;};
	public void  resetDvy() {this.dvy = 0;};

	public double getVelocity() {return this.velocity;}
	public void setVelocity(double vel) {this.velocity = vel;}
	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}


	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}


	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	public void update(ArrayList<Integer> pressedKeys, double elapsedTime) {

		//System.out.println("in update for display object");
		double scaleTime = elapsedTime / 50;
		Point p = getPosition();
		//u = ut + 1/2(at**2)
		p.x += (int)(scaleTime * getDx() + 1/2 * (getDvx() * scaleTime * scaleTime));
		p.y += (int)(scaleTime * getDy() + 1/2 * (getDvy() * scaleTime * scaleTime));

		//set new velocity
		double dx = (getDx() + getDvx() * scaleTime);
		double dy = (getDy() + getDvy() * scaleTime);

		setDx(dx);
		setDy(dy);

		this.velocity = Math.sqrt((dx*dx + dy*dy));

		//reset acceleration
		resetDvx();
		resetDvy();

		//System.out.println("x:" + p.x + " y:" + p.y + " elapsedtime:" + scaleTime + " vx" + getDx() + " dy" + getDy());

		setPosition(p);

	}

	//function to handle collision

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {
		
		if (displayImage != null) {
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;

			applyTransformations(g2d);
			g2d.draw(getHitbox());
			/* Actually draw the image, perform the pivot point translation here */
			//pivot point works when sprite is not rotated??

			if (this.getVisible()) {
				//g2d.drawImage(displayImage, this.position.x, this.position.y,
				g2d.drawImage(displayImage, 0, 0,
						(int) (getUnscaledWidth()),
						(int) (getUnscaledHeight()), null);
			}


			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		//translate, rotate, scale, alpha
		g2d.scale(getScalex(), getScaley());
		float curAlpha;
		this.oldAlpha = curAlpha = ((AlphaComposite)
				g2d.getComposite()).getAlpha();
		g2d.setComposite(AlphaComposite.getInstance(3, curAlpha *
				this.alpha));
		g2d.translate(this.position.x, this.position.y);
		g2d.rotate(Math.toRadians(this.getRotation()), this.pivotPoint.x, this.pivotPoint.y);
	}

	protected void hitAt(AffineTransform g2d) {
		//translate, rotate, scale, alpha
		g2d.scale(getScalex(), getScaley());
		g2d.translate(this.position.x, this.position.y);
		g2d.rotate(Math.toRadians(this.getRotation()), this.pivotPoint.x, this.pivotPoint.y);
	}

	protected void hitUnAt(AffineTransform g2d) {
		//untranslate, unrotate, unscale, unalpha
		g2d.rotate(-(Math.toRadians(this.getRotation())),this.pivotPoint.x, this.pivotPoint.y);
		g2d.translate(-this.position.x, - this.position.y);
		g2d.scale(1/getScalex(), 1/getScaley());
	}




	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		//untranslate, unrotate, unscale, unalpha
		g2d.rotate(-(Math.toRadians(this.getRotation())),this.pivotPoint.x, this.pivotPoint.y);
		g2d.translate(-this.position.x, - this.position.y);
		g2d.scale(1/getScalex(), 1/getScaley());
		g2d.setComposite(AlphaComposite.getInstance(3,
				this.oldAlpha));

	}

}

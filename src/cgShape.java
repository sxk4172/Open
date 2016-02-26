/*
 * cgShape.java
 * 
 * Version: $Id: cgShape.java, v 1.1 2015/22/04 13:40:41
 * 
 * Revisions: 
 * 		
 *   	Initial Revision
 * 
 */
import java.awt.*;
import java.nio.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.io.*;

public class cgShape extends simpleShape {
	
	/**
	 * This program creates tessellations of various shapes
	 * 
	 * @author Sanika Kulkarni
	 * 
	 */
    public cgShape()
    {
    	 
    }

	/**
	 * makeCube - Create a unit cube, centered at the origin, with a given
	 * number of subdivisions in each direction on each face.
	 *
	 * @param subdivision
	 *            - number of equal subdivisons to be made in each direction
	 *            along each face
	 *
	 *            Can only use calls to addTriangle()
	 */
    public void makeCube(int subdivisions) {
		if (subdivisions < 1)
			subdivisions = 1;
		float delta= 1f/subdivisions ;
		//creating front,back,top,left,right,bottom of cube by subdivisions
		for (float i = 0; i < subdivisions; i++) {
            for (float j = 0; j < subdivisions; j++) {   
            	//calling all the functions to create cube
            	makeFront(i*delta - 0.5f,j*delta -0.5f,(i+1)*delta - 0.5f,(j+1)*delta -0.5f);
            	makeBack(i*delta - 0.5f,j*delta -0.5f,(i+1)*delta - 0.5f,(j+1)*delta -0.5f);
            	makeTop(i*delta - 0.5f,j*delta -0.5f,(i+1)*delta - 0.5f,(j+1)*delta -0.5f);
            	makeBottom(i*delta - 0.5f,j*delta -0.5f,(i+1)*delta - 0.5f,(j+1)*delta -0.5f);
            	makeLeft(i*delta - 0.5f,j*delta -0.5f,(i+1)*delta - 0.5f,(j+1)*delta -0.5f);
            	makeRight(i*delta - 0.5f,j*delta -0.5f,(i+1)*delta - 0.5f,(j+1)*delta -0.5f);	
            }  
        }   
	}
    //function to create back of cube
	private void makeBack(float x0, float z0, float x1, float z1) {
		  addTriangle (x0, -0.5f, z0, x0, -0.5f, z1, x1, -0.5f, z0);
          addTriangle( x1, -0.5f, z1, x1, -0.5f, z0, x0, -0.5f, z1);
	}
	 //function to create front of cube
	private void makeFront(float x0, float z0, float x1, float z1) {
		  addTriangle (x0, 0.5f, z0, x1,  0.5f, z0, x0, 0.5f, z1);
          addTriangle( x1, 0.5f, z1, x0,  0.5f, z1, x1, 0.5f, z0 ); 	
	}
	 //function to create right of cube
	private void makeRight(float y0, float z0, float y1, float z1) {
		addTriangle ( 0.5f, y0, z0, 0.5f, y0, z1, 0.5f, y1, z0);
        addTriangle( 0.5f, y1, z1, 0.5f, y1, z0, 0.5f, y0, z1);
	}
	 //function to create left of cube
	private void makeLeft(float y0, float z0, float y1, float z1) {
		 addTriangle ( -0.5f, y0, z0, -0.5f, y1, z0, -0.5f, y0, z1);
         addTriangle( -0.5f, y1, z1, -0.5f, y0, z1, -0.5f, y1, z0);
	}
	 //function to create bottom of cube
	private void makeBottom(float x0, float y0, float x1, float y1) {
		addTriangle (x0, y0, -0.5f, x1, y0, -0.5f, x0, y1, -0.5f);
        addTriangle( x1, y1, -0.5f, x0, y1, -0.5f, x1, y0, -0.5f);
	}
	 //function to create top of cube
	private void makeTop(float x0, float y0, float x1, float y1) {
		addTriangle (x0, y0, 0.5f, x0, y1, 0.5f, x1, y0, 0.5f);
		addTriangle( x1, y1, 0.5f, x1, y0, 0.5f, x0, y1, 0.5f);
	}

	/**
	 * makeCylinder - Create polygons for a cylinder with unit height, centered
	 * at the origin, with separate number of radial subdivisions and height
	 * subdivisions.
	 *
	 * @param radius
	 *            - Radius of the base of the cylinder
	 * @param radialDivision
	 *            - number of subdivisions on the radial base
	 * @param heightDivisions
	 *            - number of subdivisions along the height
	 *
	 *            Can only use calls to addTriangle()
	 */
	public void makeCylinder(float radius, int radialDivisions,
			int heightDivisions) {
		if (radialDivisions < 3)
			radialDivisions = 3;
		if (heightDivisions < 1)
			heightDivisions = 1;
		float i = 0;
		//calling the make height and base functions again to make cylinder
		while (i < radialDivisions) {
			float[] values = new float[]{radius * (float) Math.cos(Math.toRadians(i * 360f / radialDivisions))
					,radius * (float) Math.sin(Math.toRadians(i * 360f / radialDivisions))
					,radius * (float) Math.cos(Math.toRadians((i + 1) * 360f / radialDivisions))
					,radius * (float) Math.sin(Math.toRadians((i + 1) * 360f / radialDivisions))};
			baseCylinder(values[0], values[1], values[2], values[3]);
			heightCylinder(values[0], values[1], values[2], values[3],heightDivisions);
			i++;
		}
	}
	//function to create height of cylinder
	public void heightCylinder(float x0, float z0, float x1, float z1, int h) {
		float increment = 1f/h,y0 = -0.5f,j = 0;
		while (j < h) {
			float incremenY = y0 + increment;
			addTriangle(x0, incremenY, z0, x1, incremenY, z1, x0, y0, z0);
			addTriangle(x1, incremenY, z1, x1, y0, z1, x0, y0, z0);
			y0 = incremenY;
			j++;
		}
	}
	//function to create base of cylinder
	public void baseCylinder(float x0, float z0, float x1, float z1) {
		addTriangle(0f, -0.5f, 0f, x0, -0.5f, z0, x1, -0.5f, z1);
		addTriangle(x1, 0.5f, z1, x0, 0.5f, z0, 0f, 0.5f, 0f);
	}
	
	
	/**
	 * makeCone - Create polygons for a cone with unit height, centered at the
	 * origin, with separate number of radial subdivisions and height
	 * subdivisions.
	 *
	 * @param radius
	 *            - Radius of the base of the cone
	 * @param radialDivision
	 *            - number of subdivisions on the radial base
	 * @param heightDivisions
	 *            - number of subdivisions along the height
	 *
	 *            Can only use calls to addTriangle()
	 */
	public void makeCone(float radius, int radialDivisions, int heightDivisions) {
		float i = 0;
		//calling the make height and base functions again to make cone
		while (i < radialDivisions) {
			float[] values = new float[]{radius * (float) Math.cos(Math.toRadians(i * (360f / radialDivisions))),
					radius * (float) Math.sin(Math.toRadians(i * ( 360f / radialDivisions))),
					radius * (float) Math.cos(Math.toRadians((i + 1) * (360f / radialDivisions))),
					radius * (float) Math.sin(Math.toRadians((i + 1) * (360f / radialDivisions)))};
			baseCone(values[0], values[1], values[2], values[3]) ;
			heightCone(values[0], values[1], values[2], values[3], 1f /heightDivisions);
			i++;
		}
	}
	//function to create height of cone 
	public void heightCone(float x0, float z0, float x1, float z1, float h ) {
		float h2 = 0,hIncrement = 0, j=0;
		addTriangle(0, 0.5f, 0, h * x0, 0.5f - h, h * z0, h * x1, 0.5f - h, h * z1);
			while (j < (1/h)) {
				float[] values= new float[]{h,(h2 + h),0.5f - hIncrement};
					addTriangle(h2 * x0, values[2], h2 * z0, values[1] * x0, values[2]
					- values[0], values[1] * z0, h2 * x1, values[2], h2 * z1);
					addTriangle(values[1] * x0, values[2] - values[0], values[1] * z0, values[1]
					* x1, values[2] - values[0], values[1] * z1, h2
					* x1, values[2], h2 * z1);
				h2 = values[1];
				hIncrement = hIncrement + values[0];
				j++;
			}
	}
	//function to create base of cone
	private void baseCone(float x0, float z0, float x1, float z1) {
		addTriangle(0, -0.5f, 0, x1, -0.5f, z1, x0, -0.5f,z0);	
	}

	/**
	 * makeSphere - Create sphere of a given radius, centered at the origin,
	 * using spherical coordinates with separate number of thetha and phi
	 * subdivisions.
	 *
	 * @param radius
	 *            - Radius of the sphere
	 * @param slides
	 *            - number of subdivisions in the theta direction
	 * @param stacks
	 *            - Number of subdivisions in the phi direction.
	 *
	 *            Can only use calls to addTriangle
	 */

	public void makeSphere(float radius, int slices, int stacks) {
	
	}
}


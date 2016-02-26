import java.util.ArrayList;

public class cgShape1 extends simpleShape

{

	/**
	 * 
	 * makeCone - Create polygons for a cone with unit height, centered at the
	 * 
	 * origin, with separate number of radial subdivisions and height
	 * 
	 * subdivisions.
	 * 
	 *
	 * 
	 * @param radius
	 *            - Radius of the base of the cone
	 * 
	 * @param radialDivision
	 *            - number of subdivisions on the radial base
	 * 
	 * @param heightDivisions
	 *            - number of subdivisions along the height
	 * 
	 *
	 * 
	 *            Can only use calls to addTriangle()
	 */

	public void makeCone(float radius, int radialDivisions, int heightDivisions)

	{

		float angle = (float) 360 / radialDivisions;

		ArrayList<Float> circlePoints = new ArrayList<Float>();

		for (float i = 0; i < 360; i += angle) {

			circlePoints.add(i);

		}

		float step = (float) 1 / heightDivisions;

		float radiusStep = (float) radius / heightDivisions;

		for (int i = 0; i <= circlePoints.size() - 1; i++) {

			float rx = 0;

			float xi = (float) Math.cos(Math.toRadians(circlePoints.get(i)));

			float zi = (float) Math.sin(Math.toRadians(circlePoints.get(i)));

		

				float x0 = (float) Math
						.cos(Math.toRadians(circlePoints.get(0)));

				float z0 = (float) Math
						.sin(Math.toRadians(circlePoints.get(0)));

				addTriangle(0, -0.5f, 0,

				radius * x0, -0.5f, radius * z0,

				radius * xi, -0.5f, radius * zi);

				for (float h = 0; h < 1; h += step) {

					if (rx == 0) {

						addTriangle(0, 0.5f, 0,

						(rx + radiusStep) * xi, 0.5f - step, (rx + radiusStep)
								* zi,

						(rx + radiusStep) * x0, 0.5f - step, (rx + radiusStep)
								* z0);

					} else {

						addTriangle(rx * xi, (float) 0.5 - h, rx * zi,

						(rx + radiusStep) * xi, (float) 0.5 - h - step,
								(rx + radiusStep) * zi,

								rx * x0, (float) 0.5 - h, rx * z0);

						addTriangle((rx + radiusStep) * xi, (float) 0.5 - h
								- step, (rx + radiusStep) * zi,

						(rx + radiusStep) * x0, (float) 0.5 - h - step,
								(rx + radiusStep) * z0,

								rx * x0, (float) 0.5 - h, rx * z0);

					}

					rx += radiusStep;

				

				}}

		

	}

	/**
	 * 
	 * makeSphere - Create sphere of a given radius, centered at the origin,
	 * 
	 * using spherical coordinates with separate number of thetha and
	 * 
	 * phi subdivisions.
	 * 
	 *
	 * 
	 * @param radius
	 *            - Radius of the sphere
	 * 
	 * @param slides
	 *            - number of subdivisions in the theta direction
	 * 
	 * @param stacks
	 *            - Number of subdivisions in the phi direction.
	 * 
	 *
	 * 
	 *            Can only use calls to addTriangle
	 */

	public void makeSphere(float radius, int slices, int stacks)

	{

		float a = (float) (2 / (1 + Math.sqrt(5)));

		float[] v0 = { 0, a, -1 };

		float[] v1 = { -a, 1, 0 };

		float[] v2 = { a, 1, 0 };

		float[] v3 = { 0, a, 1 };

		float[] v4 = { -1, 0, a };

		float[] v5 = { 0, -a, 1 };

		float[] v6 = { 1, 0, a };

		float[] v7 = { 1, 0, -a };

		float[] v8 = { 0, -a, -1 };

		float[] v9 = { -1, 0, -a };

		float[] v10 = { -a, -1, 0 };

		float[] v11 = { a, -1, 0 };

		ArrayList<ArrayList<Float>> triangles = new ArrayList<ArrayList<Float>>();

		triangles.add(makeTriangle(v0, v1, v2));

		triangles.add(makeTriangle(v3, v2, v1));

		triangles.add(makeTriangle(v3, v4, v5));

		triangles.add(makeTriangle(v3, v5, v6));

		triangles.add(makeTriangle(v0, v7, v8));

		triangles.add(makeTriangle(v0, v8, v9));

		triangles.add(makeTriangle(v5, v10, v11));

		triangles.add(makeTriangle(v8, v11, v10));

		triangles.add(makeTriangle(v1, v9, v4));

		triangles.add(makeTriangle(v10, v4, v9));

		triangles.add(makeTriangle(v2, v6, v7));

		triangles.add(makeTriangle(v11, v7, v6));

		triangles.add(makeTriangle(v3, v1, v4));

		triangles.add(makeTriangle(v3, v6, v2));

		triangles.add(makeTriangle(v0, v9, v1));

		triangles.add(makeTriangle(v0, v2, v7));

		triangles.add(makeTriangle(v8, v10, v9));

		triangles.add(makeTriangle(v8, v7, v11));

		triangles.add(makeTriangle(v5, v4, v10));

		triangles.add(makeTriangle(v5, v11, v6));

		for (ArrayList<Float> tri : triangles) {

			addTriangle(tri.get(0), tri.get(1), tri.get(2), tri.get(3),
					tri.get(4), tri.get(5), tri.get(6), tri.get(7), tri.get(8));

		}

	}

	private ArrayList<Float> makeTriangle(float[] a, float[] b, float[] c) {

		ArrayList<Float> triangle = new ArrayList<Float>();

		for (int i = 0; i < 3; i++) {

			triangle.add(a[i]);

		}

		for (int i = 0; i < 3; i++) {

			triangle.add(b[i]);

		}

		for (int i = 0; i < 3; i++) {

			triangle.add(c[i]);

		}

		return triangle;

	}

}

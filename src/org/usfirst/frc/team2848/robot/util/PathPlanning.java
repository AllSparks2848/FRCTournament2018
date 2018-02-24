package org.usfirst.frc.team2848.robot.util;

public class PathPlanning {
	public double[] x, y, radii, distances, angles, directions;
	double forwardsX, forwardsY;
	double backwardsX, backwardsY;
	double lengthForwards, lengthBackwards;
	double averageX, averageY;
	double m1, m2;
	double radialX, radialY;
	double radius, angle;

	double forwardsOrthX, forwardsOrthY, backwardsOrthX, backwardsOrthY;

	public void calculatePoints() {
		radii = new double[((x.length - 1) + (x.length - 2))];
		distances = new double[((x.length - 1) + (x.length - 2))];
		angles = new double[((x.length - 1) + (x.length - 2))];
		
		System.out.println("SIZE: " + ((x.length - 1) + (x.length - 2)));

		for (int i = 0; i < radii.length; i++) {
			radii[i] = 0;
			distances[i] = 0;
			angles[i] = 0;
		}

		for (int i = 1; i < x.length - 1; i++) {
			forwardsX = x[i + 1] - x[i];
			forwardsY = y[i + 1] - y[i];
			backwardsX = x[i - 1] - x[i];
			backwardsY = y[i - 1] - y[i];

			lengthForwards = Math.sqrt(Math.pow(forwardsX, 2) + Math.pow(forwardsY, 2));
			lengthBackwards = Math.sqrt(Math.pow(backwardsX, 2) + Math.pow(backwardsY, 2));

			if (i == 1) {
				distances[0] += lengthBackwards / 2;
			}

			if (i == x.length - 2) {
				distances[((x.length - 2) + (x.length - 2))] += lengthForwards / 2;
			}

			if (lengthForwards >= lengthBackwards) {
				System.out.println("forwards longer");
				backwardsX = 0.5 * backwardsX;
				backwardsY = 0.5 * backwardsY;

				radii[i] = lengthBackwards;
				forwardsX = (forwardsX / lengthForwards) * lengthBackwards;
				forwardsY = (forwardsY / lengthForwards) * lengthBackwards;

				//distances[(i * 2) - 2] += (lengthForwards - lengthBackwards) / 2;
				distances[(i * 2)] += (lengthForwards - lengthBackwards) / 2;
			} else {
				System.out.println("backwards longer");
				forwardsX = 0.5 * forwardsX;
				forwardsY = 0.5 * forwardsY;

				backwardsX = (backwardsX / lengthBackwards) * lengthForwards;
				backwardsY = (backwardsY / lengthBackwards) * lengthForwards;

				//distances[(i * 2)] += (lengthForwards - lengthBackwards) / 2;
				distances[(i * 2) - 2] += (lengthBackwards - lengthForwards) / 2;
			}

			forwardsOrthX = -1 * forwardsY;
			forwardsOrthY = forwardsX;

			backwardsOrthX = -1 * backwardsY;
			backwardsOrthY = backwardsX;

			if (forwardsOrthX != 0) {
				m2 = forwardsOrthY / forwardsOrthX;
			} else {
				m2 = 100000;
			}

			if (backwardsOrthX != 0) {
				m1 = backwardsOrthY / backwardsOrthX;
			} else {
				m1 = 100000;
			}

			radialX = (m1 * x[i - 1] - m2 * x[i + 1] - y[i - 1] + y[i + 1]) / (m1 - m2);
			radialY = m1 * (radialX - x[i - 1]) + y[i - 1];

			radius = Math.sqrt(Math.pow(radialX - x[i - 1], 2) + Math.pow(radialY - y[i - 1], 2)) * directions[i];
			radii[(i * 2) - 1] = radius;

			if (forwardsX > 0) {
				if (forwardsY == 0) {
					angle = 0;
				} else if (forwardsY > 0) {
					angle = 0 + ((Math.atan(forwardsY / forwardsX) / Math.PI) * 180);
				} else {
					angle = 360 + ((Math.atan(forwardsY / forwardsX) / Math.PI) * 180);
				}
			} else if (forwardsX < 0) {
				if (forwardsY == 0) {
					angle = 0;
				} else if (forwardsY > 0) {
					angle = 180 + ((Math.atan(forwardsY / forwardsX) / Math.PI) * 180);
				} else {
					angle = 180 + ((Math.atan(forwardsY / forwardsX) / Math.PI) * 180);
				}
			} else {
				if (forwardsY > 0) {
					angle = 90;
				} else {
					angle = 270;
				}
			}

			angles[(i * 2) - 1] = angle;
		}
	}
}

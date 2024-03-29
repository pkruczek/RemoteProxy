package pl.edu.agh.remoteproxy.common.remote;

import java.io.Serializable;

public class Vector implements Serializable {

	private static final long serialVersionUID = 1L;

	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}

}

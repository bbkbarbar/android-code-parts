package hu.bandras.probafeladat;

public class PointOfRoute {
	
	/**
	 * Constant to store direction (where we can came from).
	 */
	public static byte NO_PARENT = -1, DOWN = 0, RIGHT = 1;
	
	/**
	 * Represents the position of PointOfRoute object on table matrix.
	 */
	int posR, posC;
	
	/**
	 * Represents the weight of field (PointOfRoute) object.
	 */
	short weight;
	
	/**
	 * Shows where we came from to the current field.
	 */
	byte cameFrom;
	
	
	
	
	/**
	 * @param row
	 * @param column
	 * @param weight
	 * @param cameFrom
	 */
	public PointOfRoute(int row, int column, short weight, byte cameFrom){
		
		this.posR = row;
		this.posC = column;
		this.weight = weight;
		this.cameFrom = cameFrom;
		
	}

	
	/*
	public String toString(){
		String cf = "";
		if(cameFrom == RIGHT)
			cf = "J";
		else
			if(cameFrom == DOWN){
				cf = "L";
			}else{
				if(cameFrom==NO_PARENT)
					cf = "-";
				else
					cf = "?";
			}
			
		return "(" + posR + "," + posC + ") weight: " + weight + " F: " + this.getF() + " " + cf;  
	}/**/
	

	/**
	 * @return the vertical (row) position of current field.
	 */
	public int getPosR() {
		return posR;
	}


	/**
	 * @return the horizontal (column) position of current field.
	 */
	public int getPosC() {
		return posC;
	}


	/**
	 * @return the weight of current field.<br>
	 * 0: simple field <br>
	 * 1: penalty field <br>
	 * 2: trap
	 */
	public short getWeight() {
		return weight;
	}


	/*
	public int getG() {
		return (posR + posC);
	}/**/
	
	
	/**
	 * @return the 'F' value of current field.
	 */
	public int getF(){
		//return (this.getG() + this.weight);
		return (this.weight);
	}
	
	
	/**
	 * @return the direction where we came from.
	 */
	public byte getCameFrom(){
		return this.cameFrom;
	}
	
	
}

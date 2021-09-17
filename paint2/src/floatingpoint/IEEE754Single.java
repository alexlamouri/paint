package ca.utoronto.utm.floatingpoint;

public class IEEE754Single {
	// See https://docs.oracle.com/javase/8/docs/api/java/lang/Float.html
	// as well as the lecture notes.txt for Week11.
	
	public static float POSITIVE_INFINITY;
	
	/**
	 * Complete the code below, so that when executed, the output should exactly match 
	 * IEEE754SingleOut.txt included in this project. Do not modify main in any way. 
	 * Implement the methods below so that they perform as expected. You can add additional
	 * static constants as well as static helper methods if it helps.
	 */
	public static void main(String[] args) {
		System.out.println("0 to 10");
		for (float i = 0.0f; i <= 10.0f; i++) {
			System.out.println(binRep(i));
		}
		System.out.println("misc");
		System.out.println(binRep(-6.8f));
		System.out.println(binRep(23.1f));
		System.out.println(binRep(14.625f));
		System.out.println(binRep(.1f));
		System.out.println(binRep(5.75f));
		System.out.println(binRep(1.0f / 3.0f));

		System.out.println("Machine Epsilon");
		float me = machineEpsilon();
		System.out.println("Machine Epsilon = " + binRep(me));
		System.out.println("1+machine epsilon = " + binRep(1.0f + me));
		System.out.println("Underflow");
		System.out.println("Underflow = " + binRep(underflow()));

		// System.out.println("rounds by");
		System.out.println("Overflow");
		System.out.println("Overflow = " + binRep(overflow()));
		System.out.println("MAX_VALUE = " + binRep(Float.MAX_VALUE));
	}
	/**
	 * Search for machine epsilon (eps), that is, the smallest
	 * float such that 1+eps>1. 
	 * Print out progress along the way.
	 * 
	 * @return machine epsilon
	 */
	public static float machineEpsilon() {
		float one = 1.0f, me = 1.0f, meNew = 1.0f;
		
		while (one + meNew != 1.0f) {
			System.out.println(binRep(one + meNew));
			me = meNew;
			meNew = meNew/2;
		}
		
		// When MachineEpsilon = 0
		System.out.println(binRep(one + meNew));
		
		// Return smallest MachineEpsilon != 0
		return (me);

	}

	/**
	 * Search for underflow, that is the smallest float
	 * number that is greater than 0. 
	 * Print out progress along the way.
	 * @return underflow
	 */
	public static float underflow() {
		float ufl = 1.0f, uflNew = 1.0f;
		
		while (uflNew > 0f) {
			System.out.println(binRep(uflNew));
			ufl = uflNew;
			uflNew = uflNew/2;
		}
		
		// When UnderFlow = 0
		System.out.println(binRep(uflNew));
		
		// Return smallest UnderFlow != 0
		return ufl;
	}

	/**
	 * Search for overflow, the largest float, 
	 * by first finding the largest exponent, and
	 * then finding the largest mantissa. 
	 * Print out progress along the way.
	 * @return overflow
	 */
	public static float overflow() {
		/*
		 * Algorithm: First find the maximum exponent and then the mantissa.
		 */
		System.out.println("Maximum Exponent");
		
		float ofl = 1.0f, oflNew = 1.0f;
		
		while (oflNew < Float.POSITIVE_INFINITY) {
			System.out.println(binRep(oflNew));
			ofl = oflNew;
			oflNew = oflNew * 2;
		}

		/*
		 * Add more (lower order) bits to the mantissa. We rely on round to even here to
		 * stop us.
		 */
		System.out.println("Maximum Mantissa");
		
		float add = ofl;
		oflNew = ofl;
		
		while (oflNew < Float.POSITIVE_INFINITY) {
			System.out.println(binRep(oflNew));
			ofl = oflNew;
			add = add/2;
			oflNew = oflNew + add;
		}
		
		// Return largest OverFlow != PositiveInfinity
		return ofl;
	}

	/**
	 * Take apart a floating point number and return a string representation of it.
	 * @param d the floating point number to investigate
	 * @return By example, this method returns strings like...
	 * 
	 * binRep(0.0f) returns "0[00000000]00000000000000000000000=+0.00000000000000000000000x2^(0)=0.0"
	 * binRep(1.0f) returns "0[01111111]00000000000000000000000=+1.00000000000000000000000x2^(0)=1.0"
	 * binRep(2.0f) returns "0[10000000]00000000000000000000000=+1.00000000000000000000000x2^(1)=2.0"
	 * binRep(14.625f) returns "0[10000010]11010100000000000000000=+1.11010100000000000000000x2^(3)=14.625"
	 * binRep(0.1f) returns "0[01111011]10011001100110011001101=+1.10011001100110011001101x2^(-4)=0.1"
	 */
	// Return information about the representation of floating point number d
	public static String binRep(float d) {
		
		// Represent number as a 32-bit Binary String
		int l = Float.floatToRawIntBits(d);
		String b = Integer.toBinaryString(l);
		b =  String.format("%32s", b).replace(' ', '0');
		
		// Divide the 32-bit Binary String into different components of IEEE-754 
		String sSign = b.substring(0,1); 
		String sExponent = b.substring(1,9); 
		String sMantissa = b.substring(9);
		
		// 32-bit Binary String representation
		String binString = sSign + "[" + sExponent + "]" + sMantissa;
		
		// Transform exponent from Binary to Decimal
		int exponent = Integer.parseInt(sExponent, 2);
		
		// Every nonzero normalized number has a +/- sign, leading number 1 and true exponent from -126 to 127
		String normSign = (d >= 0) ? "+" : "-";
		String normLeading = "1.";
		int trueExponent = exponent - 127;
		
		// If number is 0 : switch exponent to 0 & switch leading number to 0
		if (d == 0f) {
			trueExponent = 0;
			normLeading = "0.";
		}
		
		// If number is denormalized : switch exponent to -126 (excess 126) & switch leading number to 0
		if (trueExponent == -127) {
			trueExponent = -126;
			normLeading = "0.";
		}
		
		// Normalized String representation
		String normString = normSign + normLeading + sMantissa + "x2^(" + trueExponent + ")";
		
		return (binString + "=" + normString + "=" + d);	

	}
}

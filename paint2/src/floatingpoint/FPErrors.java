package ca.utoronto.utm.floatingpoint;

public class FPErrors {
	/**
	 * During a floating point calculation we arrive at result c.
	 * if we could perform the calculation
	 * on real numbers we would arrive at the actual result a. 
	 * 
	 * We define
	 * the absolute error = |a-c|
	 * the relative error = |a-c|/|a|
	 **/


	public static void main(String [] args){
		FPErrors e = new FPErrors();
		e.a();
		e.b();
	}
	
	/** 
	 * a) Give an example of a float computation which results in
	 *    an absolute error >= 1000 AND a relative error <= 10^-6.
	 *    Explain, using binRep and your understanding of floating
	 *    point calculations. 
	 *    
	 *    Note: This is a large absolute error and a small relative error.
	 */
	public void a() {
		float a = 2.219085e25f; 
		float c; 
		
		float arg1 = 2.3415e25f;
		float arg2 = 1.22415e24f;
		
		c = arg1 - arg2;

		System.out.println("question a: absoluteError="+absoluteError(a,c));
		System.out.println("question a: relativeError="+relativeError(a,c));
		
		/**
		 * 	 0[11010011]00110101111001010001110=+1.00110101111001010001110x2^(84)=2.3415E25			binRep(arg1)
		 * - 0[11001111]00000011001110010100111=+1.00000011001110010100111x2^(80)=1.22415E24		binRep(arg2)
		 * ---------------------------------------------------------------------------------
		 * 	 0[11010011]00100101101100011000100=+1.00100101101100011000100x2^(84)=2.2190852E25		binRep(c)
		 * 															   ^
		 *   0[11010011]00100101101100011000011=+1.00100101101100011000011x2^(84)=2.219085E25		binRep(a)
		 * 																^^
		 *
		 * In the above calculation. There is an error added in representing arg1, arg2 and a. 
		 * c=arg1+arg2 incorrectly captures all the bits in the expected subtraction which does not match the bits in a.
		 * That's why, in this case, both relativeError and absoluteError are not 0.
		 * 
		 * The error likely occurs due to borrowing or due to the magnitude difference.
		 * 
		 * In binary subtraction, when the digit subtracted is greater than the digit it subtracts from,
		 * it borrows a digit from the 1 to the left of it who then becomes 2 times the current digit.
		 * When borrowing happens and is followed by rounding, some precision may be lost in the rounding.
		 * 
		 * Since the magnitude of both numbers is not the same, the difference may have precision errors as the smaller digits
		 * of the smaller numbers after the subtraction may be omitted when rounding to maintain 23 bits in the mantissa.
		 */
	}
	
	/**   
	 * b) Give an example of a float computation which results in
	 *    a relative error >= 10^-1 AND an absolute error >= 10^2.
	 *    Explain, using binRep and your understanding of floating
	 *    point calculations. 
	 *    
	 *    Note: This is a large relative error and small absolute error.
	 */
	public void b() {
		float a = 2.445679e12f; 
		float c;
		
		float arg1 = 1.000000001e20f;
		float arg2 = 9.9999997654321e19f;
		
		c = arg1 - arg2;
		
		System.out.println("question b: absoluteError="+absoluteError(a,c));
		System.out.println("question b: relativeError="+relativeError(a,c));
		
		/**
		 *   0[11000001]01011010111100011101100=+1.01011010111100011101100x2^(66)=1.0E20		binRep(arg1)
		 * - 0[11000001]01011010111100011101100=+1.01011010111100011101100x2^(66)=1.0E20		binRep(arg2)
		 * -----------------------------------------------------------------------------
		 * 0[00000000]00000000000000000000000=+0.00000000000000000000000x2^(0)=0.0				binRep(c)
		 * 								
		 * 0[10101000]00011100101101101110101=+1.00011100101101101110101x2^(41)=2.445679E12		binRep(a)
		 * 									   ^    ^^^  ^ ^^ ^^ ^^^ ^ ^
		 * 
		 * In the above calculation. There is an error added in representing arg1, arg2 and a. 
		 * c=arg1+arg2 incorrectly captures all the bits in the expected subtraction which does not match the bits in a.
		 * That's why, in this case, both relativeError and absoluteError are not 0.
		 * 
		 * The error likely occurs due to the two values being very close to each other.
		 * 
		 * Since the two values are very close to each other, the calculation omits certain bits when borrowing
		 * and rounding which leads to an incorrect result of 0.
		 */
	}
	
	/**
	 * We approximate the absolute error behind a floating point
	 * calculation. Note that we can't actually compute
	 * the real absoluteError, since we don't have access
	 * to the real result of the calculation, and we are using
	 * floating point arithmetic to calculate.
	 * 
	 * @param a What the actual result of the calculation should be.
	 * @param c The computed result of the calculation.
	 * @return |a-c|
	 */
	public float absoluteError(float a, float c){
		return Math.abs(a-c);
	}

	/**
	 * We approximate the relative error behind a floating point
	 * calculation. Note that we can't actually compute
	 * the real relativeError, since we don't have access
	 * to the real result of the calculation, and we are using
	 * floating point arithmetic to calculate.
	 * 
	 * @param a What the actual result of the calculation should be.
	 * @param c The computed result of the calculation.
	 * @return
	 */
	public float relativeError(float a, float c){
		return Math.abs(a-c)/Math.abs(a);
	}

}

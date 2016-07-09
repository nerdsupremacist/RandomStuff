package dft;

/**
 * Klasse zum Darstellen komplexer Zahlen
 *
 * @author Sebastian Rettenberger
 */
public class Complex {

	private static final double MIN_FOR_NON_ZERO = Math.pow(10, -14);

	/** Realteil der Zahl */
	private double real;
	/** Imaginaerteil der Zahl */
	private double imaginary;

	public Complex() {
		this(0, 0);
	}

	public Complex(double real) {
		this(real, 0);
	}

	public Complex(double real, double imaginary) {
		if (Math.abs(real) < MIN_FOR_NON_ZERO) real = 0;
		if (Math.abs(imaginary) < MIN_FOR_NON_ZERO) imaginary = 0;
		this.real = real;
		this.imaginary = imaginary;
	}

	/**
	 * Addiert zwei komplexe Zahlen.
	 *
	 * @return "this + other"
	 */
	public Complex add(Complex other) {
		double real = this.real + other.real;
		double img = this.imaginary + other.imaginary;
		return new Complex(real, img);
	}

	/**
	 * Substrahiert zwei komplexe Zahlen
	 *
	 * @return "this - other"
	 */
	public Complex sub(Complex other) {
		double real = this.real - other.real;
		double img = this.imaginary - other.imaginary;
		return new Complex(real, img);
	}

	/**
	 * Multipliziert zwei komplexe Zahlen
	 *
	 * @return "this * other"
	 */
	public Complex mul(Complex other) {
		double re = this.real * other.real - this.imaginary * other.imaginary;
		double img = this.real * other.imaginary + this.imaginary * other.real;
		return new Complex(re, img);
	}

	/**
	 * Potzenziert die Zahl mit einem ganzzahligen Exponenten
	 *
	 * @return "this ^ n"
	 */
	public Complex power(int n) {
		return Complex.fromPolar(Math.pow(getRadius(), n), n * getPhi());
	}

	/**
	 * Gibt die komplex konjugierte Zahl zurueck
	 */
	public Complex conjugate() {
		return new Complex(real, -imaginary);
	}

	/**
	 * @return String representation of the number
	 */
	public String toString() {
		return real + "+" + imaginary + "i";
	}

	/**
	 * Gibt den Realteil der Zahl zurueck
	 */
	public double getReal() {
		return real;
	}

	/**
	 * Gibt den Imaginaerteil der Zahl zurueck
	 */
	public double getImaginaer() {
		return imaginary;
	}

	/**
	 * Berechnet den Radius der Zahl (fuer Polarkoordinaten)
	 */
	public double getRadius() {
		return Math.sqrt(real * real + imaginary * imaginary);
	}

	/**
	 * Berechnet den Winkel der Zahl (fuer Polarkoordinaten)
	 */
	public double getPhi() {
		if (real > 0)
			return Math.atan(imaginary / real);

		if (real < 0) {
			if (imaginary >= 0)
				return Math.atan(imaginary / real) + Math.PI;
			return Math.atan(imaginary / real) - Math.PI;
		}

		// real == 0
		if (imaginary > 0)
			return Math.PI / 2;

		return -Math.PI / 2;
	}

	/**
	 * Erstellt eine neue komplexe Zahl, gegeben durch den Radius r und den
	 * Winkel phi.
	 */
	public static Complex fromPolar(double r, double phi) {
		return eToI(phi).mul(new Complex(r));
	}

	public static Complex eToI(double factor) {
		double cos = Math.cos(factor);
		double sin = Math.sin(factor);
		return new Complex(cos, sin);
	}

	public static Complex createWForIFFT(int n) {
		double phi = 2 * Math.PI / n;
		return fromPolar(1.0, phi);
	}

}
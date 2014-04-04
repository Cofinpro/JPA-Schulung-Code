package de.cofinpro.training.jpa.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ISBN {

	@Id
	@GeneratedValue
	private Long id;

	@Basic(optional=false)
	String isbn;

	public String getNumber() {
		return isbn;
	}

	public void setNumber(String isbn) {
		this.isbn = isbn;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return isbn;
	}

	/**
	 * Check if an isbn is valid.
	 */
	public boolean isValidISBN(String isbn) {
		if (isbn == null) return false;
		
		try {
			switch (isbn.length()) {
			case 10:
				return isValidISBN10(isbn);
			case 13:
				return isValidISBN13(isbn);
			default:
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}

	protected boolean isValidISBN10(String isbn) {
		int[] digits = parseStringToIntArray(isbn);
		for (int i = 0; i < digits.length; i++) {
			digits[i] = Integer.parseInt(String.valueOf(digits[i]));
		}
		int i, a = 0, b = 0;
		for (i = 0; i < 10; i++) {
			a += digits[i];
			b += a;
		}
		return b % 11 == 0;
	}

	protected boolean isValidISBN13(String isbn) {
		assert isbn.length() == 13;
		int[] digits = parseStringToIntArray(isbn);
		int check = 0;
		for (int i = 0; i < 13; i += 2)
			check += digits[i];
		for (int i = 1; i < 12; i += 2)
			check += 3 * digits[i];
		return check % 10 == 0;
	}

	private int[] parseStringToIntArray(String s) {
		assert isbn.length() == 10;
		int[] digits = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			digits[i] = Integer.parseInt(s.substring(i, i + 1));
		}
		return digits;

	}
}

package com.zsgs.librarymanagement.models;

import java.util.Date;

public class Dues {
	private String bookId;
	private String bookName;
	private Date out; //checkout
	private Date in;
	private double overdueFee;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bName) {
		this.bookName = bName;
	}

	public Date getOut() {
		return out;
	}

	public void setOut(Date out) {
		this.out = out;
	}

	public Date getIn() {
		return in;
	}

	public void setIn(Date in) {
		this.in = in;
	}

	public double getOverdueFee() {
		return overdueFee;
	}

	public void setOverdueFee(double overdueFee) {
		this.overdueFee = overdueFee;
	}

}

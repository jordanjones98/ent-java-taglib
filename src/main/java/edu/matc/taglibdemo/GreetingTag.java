package edu.matc.taglibdemo;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class GreetingTag extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = getJspContext().getOut();

		int time = getCurrentTime();

		String message = generateMessage(time);
		writeMessage(message, out);
	}

	/**
	 * This function returns true if the num variable is equal to or between
	 * the given param numbers
	 * @param num the number to check against
	 * @param lower the lower number to check against
	 * @param higher number the higher number to check against
	 * @return boolean true or false based on if the number is between the given numbers
	 */
	public boolean isBetween(int num, int lower, int upper) {
		return lower <= num && num <= upper;
	}

	/**
	 * This function generates a message to write based on the time of day.
	 * @param time the time of the day in military time
	 * @return message the message to be written
	 */
	public String generateMessage(int time) {
		String message = null;
		if(isBetween(time, 1, 5)) {
			message = "It is way to early for you to be up!";
		} else if(isBetween(time, 6, 12)) {
			message = "Good morning!";
		} else if(isBetween(time, 12, 18)) {
			message = "Good afternoon!";
		} else if(isBetween(time, 18, 24)) {
			message = "Good night!";
		}

		return message;
	}

	/**
	 * This function writes the message to the tag
	 * @param message the message to be written.
	 * @param out the writer to write the message to.
	 */
	public void writeMessage(String message, JspWriter out) {
		try {
			out.println(message);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * This function gets the current hour of the day.
	 * @return int the current hour in military time
	 */
	public int getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("HH");
		String currentTime = df.format(date);
		return Integer.parseInt(currentTime);
	}
}

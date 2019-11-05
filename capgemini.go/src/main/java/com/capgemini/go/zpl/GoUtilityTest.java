package com.capgemini.go.zpl;

import java.util.Calendar;

import com.capgemini.go.utility.GoUtility;

public class GoUtilityTest {
	public static void main (String args []) {
		Calendar timestamp1 = Calendar.getInstance();
		timestamp1.set(2019, 5, 28);
		Calendar timestamp2 = Calendar.getInstance();
		timestamp2.set(2022, 11, 14);
		GoUtility.calculatePeriod(timestamp1, timestamp2);
	}
}

package com.username918r818.lab5;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.username918r818.lab5.commands.CommandPanel;


public class Test {
	public static void main(String[] args) throws JsonProcessingException {
		Scanner scanner1 = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		Scanner scanner3 = new Scanner(System.in);
		
		int[] a = {1};
		String[] c = new String[0];
		int[] b = Arrays.copyOfRange(a, 1, a.length);
		System.out.println(b.length);
		// System.out.println(b[0]);
		// System.out.println(b[2]);
		// System.out.println(b.isEmp);
		ZonedDateTime zdt = ZonedDateTime.now();
		String aString = zdt.toString();
		System.out.println(aString);
		ZonedDateTime zdt2 = ZonedDateTime.parse(aString);
		aString = zdt2.toString();
		System.out.println(aString);

		ObjectMapper om = new ObjectMapper();

		CommandPanel cp = new CommandPanel(new Executor(), new Invoker());
		String scp = om.writeValueAsString(cp);



		while(true) {
			if (scanner1.hasNext()) {
				System.out.println("Scanner 1: " + scanner1.nextLine());
			}
			if (scanner2.hasNext()) {
				System.out.println("Scanner 2: " + scanner2.nextLine());
			}
			if (scanner1.hasNext()) {
				System.out.println("Scanner 1: " + scanner1.nextLine());
			}
			if (scanner3.hasNext()) {
				System.out.println("Scanner 3: " + scanner3.nextLine());
			}
		}
		

	}
}

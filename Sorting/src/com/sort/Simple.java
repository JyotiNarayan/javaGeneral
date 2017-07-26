package com.sort;

import java.util.*;
import java.io.*;
public class Simple {

	private static final Integer Integer = null;

	public static void main(String[] args) {

		ArrayList<Student> al = new ArrayList<Student>();
		al.add(new Student(109, "ram kishor", 1216));
		al.add(new Student(111, "ashok", 123));
		al.add(new Student(112, "chuba", 2));
		al.add(new Student(13, "khusbu", 9));
		
		HashMap<Integer, Student> hm = new HashMap<Integer, Student>();
		hm.put(Integer, new Student(109, "ram kishor", 1216));
		hm.put(Integer, new Student(111, "ashok", 123));
		hm.put(Integer, new Student(112, "chuba", 2));
		hm.put(Integer, new Student(13, "khusbu", 9));
		
		System.out.println("Short by name.................");
		
		Collections.sort(al, new NameComparator());
		for(Student st : al){
			System.out.println(st.rollno + " " +st.name + "  " + st.age);
		}
		
		System.out.println("Short by age.................");
		
		Collections.sort(al, new AgeComparator());
		for(Student st : al){
			System.out.println(st.rollno + " " +st.name + "  " + st.age);
		}
		
		
		
	}

}

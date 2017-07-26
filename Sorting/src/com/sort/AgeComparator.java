package com.sort;

import java.util.*;
/*This class defines comparison logic based on the age.
 * If age of first object is greater than the second, 
 * we are returning positive value, it can be any one such as 1, 2 , 10 etc.
 * If age of first object is less than the second object, we are returning negative value, 
 * it can be any negative value and if age of both objects are equal, we are returning 0.
 * */

class AgeComparator implements Comparator<Student>{
	
	@Override
	public int compare(Student o1, Student o2) {

		if(o1.age==o2.age)
		return 0;
		else if(o1.age>o2.age)
			return 1;
		else
			return -1;
	}
}



/*
 * The MIT License
 *
 * Copyright 2015 Open Jahap Community.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jahap.entities.base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author russ
 */
public class DateFormats {
        private String dateformat;
	
	public DateFormats(String Dateformat) {
		dateformat=Dateformat;
	}
	
	public String applyFormat(LocalDate Ldate ){
	        DateTimeFormatter formatter;
		switch(dateformat){
			
		case "dd.mm.yyyy":
			formatter= DateTimeFormatter.ofPattern("dd.mm.yyyy");
			return 	Ldate.format(formatter);
		
		case "dd/mm/yyyy":
			 formatter= DateTimeFormatter.ofPattern("dd/mm/yyyy");
			return 	Ldate.format(formatter);
			
			  
		case "mm/dd/yyyy":
			 formatter= DateTimeFormatter.ofPattern("mm/dd/yyyy");
			return 	Ldate.format(formatter);
		
		case "mm-dd-yyyy":
			 formatter= DateTimeFormatter.ofPattern("mm-dd-yyyy");
			return 	Ldate.format(formatter);
			
		case "yyyy.mm.dd":
			 formatter= DateTimeFormatter.ofPattern("yyyy.mm.dd");
			return 	Ldate.format(formatter);
			
		}
		
		
		
	  return null;	
		
	}
	
}

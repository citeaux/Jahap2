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
package org.jahap.System;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author russ
 */
public class ExecuteShellCommand {
	
	Logger log = LoggerFactory.getLogger(ExecuteShellCommand.class);
	
	public String executeCommand(String command) {
		log.debug("Function entry ExecuteShellCommand");
 
		StringBuffer output = new StringBuffer();
                
		Process p;
		ProcessBuilder kl;
		try {   
			
                         log.trace(command);
			//p = Runtime.getRuntime().exec(command);
			kl=new ProcessBuilder("cmd","/c",command);
			kl.redirectErrorStream(true);
			p=kl.start();
			 BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			 BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			 
 
                        String line = "";
			while ((line = bre.readLine()) != null) {
        System.out.println(line);
      }
			bre.close();
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
				log.trace(reader.readLine());
			}
			reader.close();
			p.waitFor();
			log.trace("Exit VAlue " + p.exitValue());
 
		} catch (Exception e) {
			log.trace("Exception execution");
			e.printStackTrace();
		}
                 log.debug("Function exit ExecuteShellCommand");
		return output.toString();
 
	}
}

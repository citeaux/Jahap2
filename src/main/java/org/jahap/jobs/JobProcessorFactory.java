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
package org.jahap.jobs;

/**
 *
 * @author russ
 */
public class JobProcessorFactory {
	
	public JobProcessor getJobProcessor(String type){
		if(type==null){
			return null;
			
		}
		if(type.equals(Jobtypes.changehotelday.toString())){
			return new ChangeHotelDayJob();
		}
		
		if(type.equals(Jobtypes.servicecharging.toString())){
			return new ServiceChargerJob();
		}
		
		
		
		if(type.equals(Jobtypes.backup.toString())){
			return new BackupJob();
		}
		
		
		if(type.equals(Jobtypes.changeroomstate.toString())){
			return new ChangeroomstateJob();
		}
		
		
		return null;
	}
	
}

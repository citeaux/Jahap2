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

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Query;
import org.jahap.entities.views.Dayclose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author russ
 */
public class Dayclosebean extends JobJobschedulerbean {
	 Logger log = LoggerFactory.getLogger(Dayclosebean.class);
        List<Dayclose>alljobs = new ArrayList<>();
	public Dayclosebean() {
		this.alljobs=getListOfDaycloseJobs();
		
		
	}
	
	
	
	public List<Dayclose>getListOfDaycloseJobs(){
	   Query queryView;
	   List<Dayclose>alljobs=null;
	   int lines=0;
	   try {
           
            queryView = dbhook.getEntity().createQuery("select t from Dayclose t WHERE t.idJobscheduler=1 ORDER BY t.position");
            alljobs= queryView.getResultList();
	    lines=alljobs.size()-1;
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
	   if(lines<0){
		   return null;
	   }    
        }
	return alljobs;  
    }
	
	void addNewJob(long JobId){
		createNewEmptyRecord();
		Jobsbean kk= new Jobsbean();
		Jobschedulerbean kl = new Jobschedulerbean();
		this.setIdJob(kk.getDataRecord(JobId));
		this.setIdJobscheduler(kl.getDataRecord(1));
		int h=this.alljobs.get(this.alljobs.size()-1).getPosition();
		h++;
		this.setPosition(h);
		this.saveRecord();
	}
	
	
	public void startDayclosJobs(){
		Jobsbean jjl=new Jobsbean();
		JobProcessorFactory factory= new JobProcessorFactory();
		for(Dayclose k:this.getListOfDaycloseJobs()){
		  JobProcessor jj=factory.getJobProcessor(k.getType());
		  jj.execute(jjl.getDataRecord(k.getIdJob()));
	        }
	}
	
	
}

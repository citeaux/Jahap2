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

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import jakarta.persistence.Query;
import org.jahap.business.acc.accountsbean;
import org.jahap.business.acc.cscbean;
import org.jahap.business.base.Hotelbean;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.acc.Accounts;
import org.jahap.entities.jobs.Jobs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author russ
 */
public class ServiceChargerJob extends org.jahap.jobs.DatabaseOperations implements JobProcessor{
	 Logger log = LoggerFactory.getLogger(ServiceChargerJob.class);
        JahapDatabaseConnector dbhook;
	@Override
	public void execute(Jobs job) {
		dbhook = JahapDatabaseConnector.getConnector();
		cscbean cbean=new cscbean();
		 Hotelbean hbean= new Hotelbean();
	    accountsbean abean = new accountsbean();
	    LocalDateTime ldate;
	    Instant instant=Instant.from(hbean.getOperationdate().toInstant());
	    ldate=LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
	    log.trace( "select t from Accounts t where t.checkindate<='" + ldate.format(DateTimeFormatter.ISO_DATE) + "' AND t.checkoutdate>='" + ldate.format(DateTimeFormatter.ISO_DATE) + "' ORDER BY t.id");
	  Query accounts = null;
		try {
			accounts = dbhook.getEntity().createQuery("select t from Accounts t where t.checkindate<='" + ldate.format(DateTimeFormatter.ISO_DATE) + "' AND t.checkoutdate>='" + ldate.format(DateTimeFormatter.ISO_DATE) + "' ORDER BY t.id");
				
		} catch (Exception e) {
		}
		
            List<Accounts> alist = accounts.getResultList();
	    for(Accounts j:alist){
		    cbean.chargeServiceForCurrentOperationDate(j);
	    }
	    
	    
	}
	
}

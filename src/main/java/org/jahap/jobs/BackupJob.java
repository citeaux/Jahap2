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

import org.jahap.System.ExecuteShellCommand;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.databasetypes.databaseCommands;
import org.jahap.entities.jobs.Jobs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author russ
 */
public class BackupJob implements JobProcessor  {
	 Logger log = LoggerFactory.getLogger(BackupJob.class);
        JahapDatabaseConnector hhh;
	
	databaseCommands kkk;
	
	@Override
	public void execute(Jobs job) {
	     ExecuteShellCommand shell= new ExecuteShellCommand();
		String j,t;
		kkk=JahapDatabaseConnector.getDBCommmands();
             
             j=kkk.getBackupCommand(job.getDefinition(), "JahapBackup.sql");
	     t=shell.executeCommand(j);
	     log.debug("Function exit BackupJob " + t);
	}
	
}

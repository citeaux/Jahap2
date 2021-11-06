/*
 * The MIT License
 *
 * Copyright 2018 Open Jahap Community.
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
package org.jahap.gui;

import java.util.EventObject;
import javax.swing.event.EventListenerList;
import org.jahap.entities.Reports;

/**
 *
 * @author demokrite
 */
public class SelectedReport {
     private Reports rep;
     private Boolean setAsStandardReport;



    public Reports getRep() {
        return rep;
    }

    public Boolean getSetAsStandardReport() {
        return setAsStandardReport;
    }
     
    public void setReport(Reports report, Boolean setAsStandardReport){
        this.rep=report;
        this.setAsStandardReport=setAsStandardReport;
        notifyId(new SelectedReportEvent(this,rep,this.setAsStandardReport));
    } 
      private EventListenerList listeners = new  EventListenerList();
    
          public void addIDListener( SelectedReportListner listener )
  {
    listeners.add( SelectedReportListner.class, listener );
  }

  public void removeIDListener( SelectedReportListner listener )
  {
    listeners.remove( SelectedReportListner.class, listener );
  }

    private void notifyId(SelectedReportEvent selectedReportEvent) {
                for ( SelectedReportListner l : listeners.getListeners( SelectedReportListner.class ) )
      l.idinfo( selectedReportEvent );
    }
    
}

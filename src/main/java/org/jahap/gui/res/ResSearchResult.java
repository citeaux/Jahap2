/*
 * The MIT License
 *
 * Copyright 2014 Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>.
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


package org.jahap.gui.res;

import javax.swing.event.EventListenerList;
import javax.swing.table.TableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author russ
 */
public  class ResSearchResult {
    private long DbRecordId;
    private String TableNameofSource;
 Logger log = LoggerFactory.getLogger(ResSearchResult.class);
   
    public long getDbRecordId() {
        return DbRecordId;
    }
    

    public void setDbRecordId(long DataRecordId, String TableNameofSource) {
        //
        this.TableNameofSource=TableNameofSource;
        this.DbRecordId=DataRecordId;
        notifyId(new ResSearchResultEvent(this,DataRecordId,TableNameofSource));
    }
    
    private EventListenerList listeners = new  EventListenerList();
    
    
    public void addIDListener( ResSearchResultListener listener )
  {
    listeners.add( ResSearchResultListener.class, listener );
  }

  public void removeIDListener( ResSearchResultListener listener )
  {
    listeners.remove( ResSearchResultListener.class, listener );
  }

  

    
    protected synchronized void notifyId(ResSearchResultEvent event){
        for ( ResSearchResultListener l : listeners.getListeners( ResSearchResultListener.class ) )
      l.idinfo( event );

    }
}



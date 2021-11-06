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
package org.jahap.entities.acc;

import java.util.Collection;
import java.util.Date;
import jakarta.xml.bind.annotation.XmlTransient;
import org.jahap.entities.base.Address;
import org.jahap.entities.res.Res;

/**
 *
 * @author russ
 */
public interface accounts_ie {
    
   
    
    Collection<AccountPosition> getAccountPositionCollection();

    double getBalance();

    Date getCheckindate();

    boolean getCheckout();

    boolean getCheckin();
    
    Date getCheckoutdate();

    Long getId();
    
    Address getAddress();
    
    @XmlTransient
    Collection<Csc> getCscCollection();
    
    @XmlTransient
    Res getReservation();

    String getState();
  
    void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection);

    void setBalance(double balance);

    void setCheckindate(Date checkindate);

    void setState(String state);
    
    void setCheckout(boolean checkout);
    
    void setCheckin(boolean checkin);
    
    void setCscCollection(Collection<Csc> cscCollection);
    
    void setCheckoutdate(Date checkoutdate);

    void setReservation(Res reservation);
    
    void setAddress(Address address);
    
    
    
    
}

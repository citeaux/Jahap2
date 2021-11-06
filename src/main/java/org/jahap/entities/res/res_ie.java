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
package org.jahap.entities.res;

import org.jahap.entities.base.Address;
import org.jahap.entities.base.ResState;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author russ
 */
public interface res_ie {

    Address getAddresses();

    Date getArrivaldate();

    Date getArrivaltime();

    Date getDeparturedate();

    Date getDeparturetime();
    
    ResState getState();
            
    String getResno();

    Date getOptiondate();

    String getComment();

    Res_No getResnointern();

    void setComment(String comment);

    void setOptiondate(Date optiondate);
   
    void setAddresses(Address addresses);
    
    void setState(ResState state);
    
    void setArrivaldate(Date arrivaldate);

    void setArrivaltime(Date arrivaltime);

    void setDeparturedate(Date departuredate);

    void setDeparturetime(Date departuretime);

    void setResno();

    void setResnointern(Res_No resnointern);
    
}

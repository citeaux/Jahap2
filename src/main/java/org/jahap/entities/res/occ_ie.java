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
package org.jahap.entities.res;

import java.util.Date;
import org.jahap.entities.acc.Accounts;
import org.jahap.entities.base.Address;
import org.jahap.entities.base.Rooms;

/**
 *
 * @author demokrite
 */
public interface occ_ie {

    boolean equals(Object object);

    Accounts getAccount();

    Date getArrivaldate();

    Date getArrivaltime();

    Boolean getCheckin();

    Date getCheckinTimestamp();

    Boolean getCheckout();

    Date getCheckoutTimestamp();

    Date getDeparturedate();

    Date getDeparturetime();

    Address getGuest();

    Housekeepingblock getHousekeepingblock();

    Long getId();

    Maintenanceblock getMaintenanceblock();

    int getPax();

    Res getRes();

    Rooms getRoom();

    int hashCode();

    void setAccount(Accounts account);

    void setArrivaldate(Date arrivaldate);

    void setArrivaltime(Date arrivaltime);

    void setCheckin(Boolean checkin);

    void setCheckinTimestamp();

    void setCheckout(Boolean checkout);

    void setCheckoutTimestamp();

    void setDeparturedate(Date departuredate);

    void setDeparturetime(Date departuretime);

    void setGuest(Address guest);

    void setHousekeepingblock(Housekeepingblock housekeepingblock);

    void setMaintenanceblock(Maintenanceblock maintenanceblock);

    void setPax(int pax);

    void setRes(Res res);

    void setRoom(Rooms room);
    
}

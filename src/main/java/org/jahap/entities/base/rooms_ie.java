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
package org.jahap.entities.base;

/**
 *
 * @author russ
 */
public interface rooms_ie {

    String getName();
 
    Cat getCategory();

    String getCode();
    
    Location getLocation();
    
    boolean isNo_maintenance();
    
    boolean isClean();

    byte getPax();

    Long getId();

    void setCategory(Cat category);

    void setCode(String code);

    void setName(String name);
    
    void setLocaton(Location location);
    
    void setClean(boolean clean);
    
    void setNo_maintenance(boolean no_maintenance);

    void setPax(byte pax);
    
    
}

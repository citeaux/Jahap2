/*
 * The MIT License
 *
 * Copyright 2016 Open Jahap Community.
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
package org.jahap.config;

/**
 *
 * @author Sebastian
 */
public class ClientConfigDatabase {
    private String database_title;
  private String persitence_unit; 
   private String database_url; 
   private String database_type;
   private String database_path;
    private String database_driver;
    private String reports_relativpath;

    public String getDatabase_title() {
        return database_title;
    }

    public void setDatabase_title(String database_title) {
        this.database_title = database_title;
    }

    public String getPersitence_unit() {
        return persitence_unit;
    }

    public void setPersitence_unit(String persitence_unit) {
        this.persitence_unit = persitence_unit;
    }

    public String getDatabase_url() {
        return database_url;
    }

    public void setDatabase_url(String database_url) {
        this.database_url = database_url;
    }

    public String getDatabase_type() {
        return database_type;
    }

    public void setDatabase_type(String database_type) {
        this.database_type = database_type;
    }

    public String getDatabase_path() {
        return database_path;
    }

    public void setDatabase_path(String database_path) {
        this.database_path = database_path;
    }

    public String getDatabase_driver() {
        return database_driver;
    }

    public void setDatabase_driver(String database_driver) {
        this.database_driver = database_driver;
    }

    public String getReports_relativpath() {
        return reports_relativpath;
    }

    public void setReports_relativpath(String reports_relativpath) {
        this.reports_relativpath = reports_relativpath;
    }
    
    
    
}

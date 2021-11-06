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
public class ClientConfigUser {
    private String userloginsetup_login;
    private String userloginsetup_language;
    private String userloginsetup_prefereddatabase;
    private String userloginsetup_lastused;

    public String getUserloginsetup_login() {
        return userloginsetup_login;
    }

    public void setUserloginsetup_login(String userloginsetup_login) {
        this.userloginsetup_login = userloginsetup_login;
    }

    public String getUserloginsetup_language() {
        return userloginsetup_language;
    }

    public void setUserloginsetup_language(String userloginsetup_language) {
        this.userloginsetup_language = userloginsetup_language;
    }

    public String getUserloginsetup_prefereddatabase() {
        return userloginsetup_prefereddatabase;
    }

    public void setUserloginsetup_prefereddatabase(String userloginsetup_prefereddatabase) {
        this.userloginsetup_prefereddatabase = userloginsetup_prefereddatabase;
    }

    public String isUserloginsetup_lastused() {
        return userloginsetup_lastused;
    }

    public void setUserloginsetup_lastused(String userloginsetup_lastused) {
        this.userloginsetup_lastused = userloginsetup_lastused;
    }
    
    
}

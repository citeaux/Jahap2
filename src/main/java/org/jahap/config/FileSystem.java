/*
 * The MIT License
 *
 * Copyright 2017 Open Jahap Community.
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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Sebastian
 */
public final class FileSystem {
    private static FileSystemManager fsManager;
    private static Properties SystemProperties;
    private static String apppath;
    private static String configfilepath;
    private static URL configfileurl;
    private static URL reportfileurl;
    private static FileSystem  filesys;
    private static String languagefile;
    Logger log = LoggerFactory.getLogger(FileSystem.class);
    
    private FileSystem() throws FileSystemException, MalformedURLException {
        log.debug("Function entry FileSystem");
        fsManager = VFS.getManager();
        SystemProperties= new Properties(System.getProperties());
        apppath=SystemProperties.getProperty("user.dir");
        configfileurl=new URL("file:///"+ apppath + StandardRelativFilePaths.CONFIG.getPath());
       
        log.debug("Config URL: " + configfileurl);
    }
    
    public static FileSystem getFileSystem() throws FileSystemException, MalformedURLException{
        if(filesys==null){
                 return filesys=new FileSystem();
        }else{
            return filesys;
        }              
    }
    
    
    
    
    public URL getConfigFileUrl(){
        return configfileurl;
    }
    
//    public URL getReportFileUrl() throws MalformedURLException{
//        ClientConfig cf=cf.getClientConfig();
//        if (cf.getUsedbconfig().getReports_relativpath().isEmpty()){
//            reportfileurl = new URL("file:///" + apppath + StandardRelativFilePaths.REPORTS.getPath() );
//        }else{
//            reportfileurl = new URL("file:///" + cf.getUsedbconfig().getReports_relativpath());
//            return reportfileurl;
//        }
//       return null; 
//    }
    
   
    
}

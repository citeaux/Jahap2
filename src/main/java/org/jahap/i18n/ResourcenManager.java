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
package org.jahap.i18n;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.jahap.config.ApplicationLanguages;
import org.jahap.config.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russ
 */
public class ResourcenManager {
    static Logger log = LoggerFactory.getLogger(ResourcenManager.class);
  
    //private  static String               BUNDLE_PATH              ;
    private FileSystem fs;       

 
    
    private final Map<Locale, ResourceBundle> availableResourceBundles = new HashMap<>();

    private ResourceBundle                    currentResourceBundle    = null;
    private ResourceBundle   currentFxResourceBundle=null;
    
    private Locale                            currentLocale            = null;
    private static ResourcenManager klk;
    private ResourcenManager()
    {
    }
    
    
    private static final String INDICATOR_MISSING_RESOURCE = "?";
    private static final String INDICATOR_MISSING_KEY      = "??";

    
    
    
    
    public static String getLangString(final ResourceBundle resourceBundle, final SysTexts key)
	    
    {
	    log.debug("Function entry getLangString ");
        if (resourceBundle != null)
        {
            try
            {
                return resourceBundle.getString(key.name());
            }
            catch (final MissingResourceException e)
            {
                return INDICATOR_MISSING_KEY + key;
            }
        }
        log.debug("Function exit getLangString ");
        return INDICATOR_MISSING_RESOURCE + key;
    }
    
    public static String getFxLangString(final ResourceBundle resourceBundle, String key)
    {
	    log.debug("Function entry getFxLangString");
        if (resourceBundle != null)
        {
            try
            {
                return resourceBundle.getString(key);
            }
            
            catch (final MissingResourceException e)
            {
                return INDICATOR_MISSING_KEY + key;
            }
            catch(Exception e){
            	
            	e.printStackTrace();
            }
        }
        log.debug("Function exit getFxLangString");
        return INDICATOR_MISSING_RESOURCE + key;
    }
      

    public static ResourcenManager createInstance()
    {
	    log.debug("Function entry createInstance");    
        final ResourcenManager resourceManager = new ResourcenManager();
        
	resourceManager.init();
	
        resourceManager.activateLocale(Locale.ENGLISH);
        log.debug("Function exit createInstance ");
	
        return resourceManager;
    }
    /*
      public static Locale createLocaleFromBundleName(final String name)
    {
        // Pr?fix und Postfix abschneiden
	    log.debug("Function entry createLocaleFromBundleName");
        final int languageIndex = name.indexOf('_');
        final int countryIndex = name.indexOf('_', languageIndex + 1);

        String language = null;
        String country = "";

        if (languageIndex > 0 && name.length() > languageIndex + 3)
        {
            language = name.substring(languageIndex + 1, languageIndex + 3);
        }
        if (countryIndex > 0 && name.length() > countryIndex + 3)
        {
            country = name.substring(countryIndex + 1, countryIndex + 3);
        }

        if (language == null)
            return Locale.getDefault();
       
	    log.debug("Function exit createLocaleFromBundleName");
	
        return new Locale(language, country);
    }

    */
    
    
    private void init() 
    {
	    log.debug("Function entry init");   
        
	  
        
        
	
	//BUNDLE_PATH=fs.getLanguageFilesUrl();
	   
         int il=ApplicationLanguages.values().length;
         
         
        
	for (int i=0; i<=ApplicationLanguages.values().length-1; i++  ){
            final Locale locale = new Locale(ApplicationLanguages.values()[i].getLangCode(), ApplicationLanguages.values()[i].getLangCode());
                ResourceBundle resourceBundle = ResourceBundle.getBundle(ApplicationLanguages.values()[i].getLangPath());
                addResourceBundle(locale,resourceBundle );
            
        }
        
      /*
        for (final FileObject propertyFile : fs.getListLanguagefiles())
        {
            // JDK 7: ARM
		log.debug("init:4");
            try (final InputStream is = new BufferedInputStream(propertyFile.getContent().getInputStream()))
            {
                final PropertyResourceBundle resourceBundle = new PropertyResourceBundle(is);

                final Locale locale = new Locale(language, country);
                addResourceBundle(locale, resourceBundle);
            }
            catch (final IOException ex)
            {
                log.warn("Failed to load resource from file '"  + "'", ex);
            }
        }
	*/
	klk=this;
	    log.debug("Function exit init");
    }
   /*
    public void loadAndAddResourceBundle(final Locale locale)
    {
	    
	    log.debug("Function entry loadAndAddResourceBundle");    
        try
        {
            final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_PATH, locale);
            addResourceBundle(locale, resourceBundle);
        }
        catch (final MissingResourceException ex)
        {
            log.warn("Missing recource '" + BUNDLE_PATH + "' for locale: '" + locale + "'", ex);
        }
	
	    log.debug("Function exit loadAndAddResourceBundle");
    } */

    protected void addResourceBundle(final Locale locale, final ResourceBundle resourceBundle)
    {
	    log.debug("Function entry addResourceBundle");  
        availableResourceBundles.put(locale, resourceBundle);
	
	    log.debug("Function exit addResourceBundle");
    }

    public String getLangString(final SysTexts key)
    {
	    
        return getLangString(currentResourceBundle, key);
	    
    }
    
    public String getFxLangString(String key)
    {
	     
        return getFxLangString(currentFxResourceBundle, key);
    }

    public boolean activateLocale(final Locale locale)
    {
	    log.debug("Function entry activateLocale");
        if (supportsLocale(locale))
        {
            currentResourceBundle = availableResourceBundles.get(locale);
            currentLocale = locale;
            return true;
        }
        
	    log.debug("Function exit activateLocale");
        return false;
    }

    public boolean supportsLocale(final Locale locale)
    {
	    
	     
        return availableResourceBundles.containsKey(locale);
    }

    public Locale getCurrentLocale()
    {
	     
        return currentLocale;
    }
    
    public  ResourceBundle getFxResourceBundle(String FxSource){
	    log.debug("Function entry getFxResourceBundle");

	  ResourceBundle resourceBundle = null;
	    try {
		    resourceBundle = ResourceBundle.getBundle(FxSource, currentLocale);
	    } catch (Exception e) {
            log.debug("Error loading language Pack");
		    e.printStackTrace();
	    }
	    
	    log.trace("Location: " + currentLocale.getCountry() + " " + currentLocale.getLanguage());
	    currentFxResourceBundle=resourceBundle;
	    
	   return resourceBundle;
    }
    

    public List<Locale> getAvailableLocales()
    {
        return new ArrayList<Locale>(availableResourceBundles.keySet());
    }	
    public  ResourcenManager getInstance(){
	   
	   return klk;
   }
}

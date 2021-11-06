package org.jahap.gui;

import java.util.Date;

/**
 *  Interface for all GUI Modules in order to handle I18N
 *
 *
 */
public interface i18n {
    public String getI18NDateformat(Date cdate);
    public String getI18NDecformat(double number);
    public String getI18NTranslation(String totranslate);

}

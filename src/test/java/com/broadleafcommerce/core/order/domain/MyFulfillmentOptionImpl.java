/* 
 * Broadleaf Commerce Confidential
 * _______________________________
 * 
 *  [2009] - [2013] Broadleaf Commerce, LLC 
 *  All Rights Reserved.
 * 
 * NOTICE:  All information contained herein is, and remains
 * the property of Broadleaf Commerce, LLC
 * The intellectual and technical concepts contained
 * herein are proprietary to Broadleaf Commerce, LLC
 * and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Broadleaf Commerce, LLC.
 */
package com.broadleafcommerce.core.order.domain;

import org.broadleafcommerce.common.locale.domain.Locale;
import org.broadleafcommerce.common.locale.util.LocaleUtil;
import org.broadleafcommerce.common.web.BroadleafRequestContext;
import org.broadleafcommerce.core.order.domain.FulfillmentOptionImpl;

import com.broadleafcommerce.i18n.domain.order.FulfillmentOptionTranslation;
import com.broadleafcommerce.i18n.domain.order.I18NFulfillmentOption;

import javax.persistence.Embedded;

import java.util.Map;



/**
 * @author Andre Azzolini (apazzolini)
 */
public class MyFulfillmentOptionImpl extends FulfillmentOptionImpl implements I18NFulfillmentOption {
    private static final long serialVersionUID = -1849002347743308131L;
    
    @Embedded
    protected I18NFulfillmentOption i18nExtension;

    public Map<String, FulfillmentOptionTranslation> getTranslations() {
        return i18nExtension.getTranslations();
    }

    public void setTranslations(Map<String, FulfillmentOptionTranslation> translations) {
        i18nExtension.setTranslations(translations);
    }
    
    @Override
    public String getLongDescription() {
        if (getTranslations() != null && BroadleafRequestContext.hasLocale())  {
            Locale locale = BroadleafRequestContext.getBroadleafRequestContext().getLocale();

            // Search for translation based on locale
            String localeCode = locale.getLocaleCode();
            if (localeCode != null) {
                FulfillmentOptionTranslation translation = getTranslations().get(localeCode);
                if (translation != null && translation.getLongDescription() != null) {
                    return translation.getLongDescription();
                }
            }

            // try just the language
            String languageCode = LocaleUtil.findLanguageCode(locale);
            if (languageCode != null && ! localeCode.equals(languageCode)) {
                FulfillmentOptionTranslation translation = getTranslations().get(languageCode);
                if (translation != null && translation.getLongDescription() != null) {
                    return translation.getLongDescription();
                }
            }
        }
        return longDescription;
    }
    
    @Override
    public String getName() {
        if (getTranslations() != null && BroadleafRequestContext.hasLocale())  {
            Locale locale = BroadleafRequestContext.getBroadleafRequestContext().getLocale();

            // Search for translation based on locale
            String localeCode = locale.getLocaleCode();
            if (localeCode != null) {
                FulfillmentOptionTranslation translation = getTranslations().get(localeCode);
                if (translation != null && translation.getName() != null) {
                    return translation.getName();
                }
            }

            // try just the language
            String languageCode = LocaleUtil.findLanguageCode(locale);
            if (languageCode != null && ! localeCode.equals(languageCode)) {
                FulfillmentOptionTranslation translation = getTranslations().get(languageCode);
                if (translation != null && translation.getName() != null) {
                    return translation.getName();
                }
            }
        }
        return name;
    }
    
}

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
package com.broadleafcommerce.core.order.catalog;

import org.broadleafcommerce.common.locale.domain.Locale;
import org.broadleafcommerce.common.locale.util.LocaleUtil;
import org.broadleafcommerce.common.web.BroadleafRequestContext;
import org.broadleafcommerce.core.catalog.domain.ProductOptionValueImpl;

import com.broadleafcommerce.i18n.domain.catalog.I18NProductOptionValue;
import com.broadleafcommerce.i18n.domain.catalog.ProductOptionValueTranslation;

import javax.persistence.Embedded;

import java.util.Map;


/**
 * @author Andre Azzolini (apazzolini)
 */
public class MyProductOptionValueImpl extends ProductOptionValueImpl implements I18NProductOptionValue {
    
    @Embedded
    protected I18NProductOptionValue i18nExtension;

    public Map<String, ProductOptionValueTranslation> getTranslations() {
        return i18nExtension.getTranslations();
    }

    public void setTranslations(Map<String, ProductOptionValueTranslation> translations) {
        i18nExtension.setTranslations(translations);
    }
    
    @Override
    public String getAttributeValue() {
        if (getTranslations() != null && BroadleafRequestContext.hasLocale())  {
            Locale locale = BroadleafRequestContext.getBroadleafRequestContext().getLocale();

            // Search for translation based on locale
            String localeCode = locale.getLocaleCode();
            if (localeCode != null) {
                ProductOptionValueTranslation translation = getTranslations().get(localeCode);
                if (translation != null && translation.getAttributeValue() != null) {
                    return translation.getAttributeValue();
                }
            }

            // try just the language
            String languageCode = LocaleUtil.findLanguageCode(locale);
            if (languageCode != null && ! localeCode.equals(languageCode)) {
                ProductOptionValueTranslation translation = getTranslations().get(languageCode);
                if (translation != null && translation.getAttributeValue() != null) {
                    return translation.getAttributeValue();
                }
            }
        }

        return attributeValue;
    }

}

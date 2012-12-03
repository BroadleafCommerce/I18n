/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.broadleafcommerce.core.order.catalog;

import org.broadleafcommerce.common.locale.domain.Locale;
import org.broadleafcommerce.common.locale.util.LocaleUtil;
import org.broadleafcommerce.common.web.BroadleafRequestContext;
import org.broadleafcommerce.core.catalog.domain.ProductOptionValueImpl;
import org.broadleafcommerce.i18n.domain.catalog.I18NProductOptionValue;
import org.broadleafcommerce.i18n.domain.catalog.ProductOptionValueTranslation;

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

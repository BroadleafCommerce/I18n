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
import org.broadleafcommerce.core.catalog.domain.SkuImpl;

import com.broadleafcommerce.i18n.domain.catalog.I18NSku;
import com.broadleafcommerce.i18n.domain.catalog.SkuTranslation;

import javax.persistence.Embedded;

import java.util.Map;


/**
 * @author Andre Azzolini (apazzolini)
 */
public class MySkuImpl extends SkuImpl implements I18NSku {
    
    @Embedded
    protected I18NSku i18nExtension;

    public Map<String, SkuTranslation> getTranslations() {
        return i18nExtension.getTranslations();
    }

    public void setTranslations(Map<String, SkuTranslation> translations) {
        i18nExtension.setTranslations(translations);
    }
    
    @Override
    public String getName() {
        if (name == null && hasDefaultSku()) {
            return lookupDefaultSku().getName();
        } else {
            if (getTranslations() != null && BroadleafRequestContext.hasLocale()) {
                Locale locale = BroadleafRequestContext.getBroadleafRequestContext().getLocale();

                // Search for translation based on locale
                String localeCode = locale.getLocaleCode();
                if (localeCode != null) {
                    SkuTranslation translation = getTranslations().get(localeCode);
                    if (translation != null && translation.getName() != null) {
                        return translation.getName();
                    }
                }

                // try just the language
                String languageCode = LocaleUtil.findLanguageCode(locale);
                if (languageCode != null && !localeCode.equals(languageCode)) {
                    SkuTranslation translation = getTranslations().get(languageCode);
                    if (translation != null && translation.getName() != null) {
                        return translation.getName();
                    }
                }
            }
        }
        return name;
    }
    
    @Override
    public String getDescription() {
        if (description == null && hasDefaultSku()) {
            return lookupDefaultSku().getDescription();
        } else {
            if (getTranslations() != null && BroadleafRequestContext.hasLocale()) {
                Locale locale = BroadleafRequestContext.getBroadleafRequestContext().getLocale();

                // Search for translation based on locale
                String localeCode = locale.getLocaleCode();
                if (localeCode != null) {
                    SkuTranslation translation = getTranslations().get(localeCode);
                    if (translation != null && translation.getDescription() != null) {
                        return translation.getDescription();
                    }
                }

                // try just the language
                String languageCode = LocaleUtil.findLanguageCode(locale);
                if (languageCode != null && !localeCode.equals(languageCode)) {
                    SkuTranslation translation = getTranslations().get(languageCode);
                    if (translation != null && translation.getDescription() != null) {
                        return translation.getDescription();
                    }
                }
            }
        }
        return description;
    }
    
    @Override
    public String getLongDescription() {
        if (longDescription == null && hasDefaultSku()) {
            return lookupDefaultSku().getLongDescription();
        } else {
            if (getTranslations() != null && BroadleafRequestContext.hasLocale()) {
                Locale locale = BroadleafRequestContext.getBroadleafRequestContext().getLocale();

                // Search for translation based on locale
                String localeCode = locale.getLocaleCode();
                if (localeCode != null) {
                    SkuTranslation translation = getTranslations().get(localeCode);
                    if (translation != null && translation.getLongDescription() != null) {
                        return translation.getLongDescription();
                    }
                }

                // try just the language
                String languageCode = LocaleUtil.findLanguageCode(locale);
                if (languageCode != null && !localeCode.equals(languageCode)) {
                    SkuTranslation translation = getTranslations().get(languageCode);
                    if (translation != null && translation.getLongDescription() != null) {
                        return translation.getLongDescription();
                    }
                }
            }
        }
        return longDescription;
    }
    

}

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

package com.broadleafcommerce.i18n.weave.catalog.domain;

import org.broadleafcommerce.common.extensibility.jpa.copy.NonCopied;
import org.broadleafcommerce.common.locale.domain.Locale;
import org.broadleafcommerce.common.locale.util.LocaleUtil;
import org.broadleafcommerce.common.web.BroadleafRequestContext;

import com.broadleafcommerce.i18n.domain.catalog.I18NProductOption;
import com.broadleafcommerce.i18n.domain.catalog.I18NProductOptionImpl;
import com.broadleafcommerce.i18n.domain.catalog.ProductOptionTranslation;

import javax.persistence.Embedded;

import java.util.Map;

/**
 * @author ppatel
 */
public class I18nWeaveProductOptionImpl implements I18NProductOption {

    @Embedded
    protected I18NProductOptionImpl i18nExtension;

    @NonCopied
    protected String label;

    public Map<String, ProductOptionTranslation> getTranslations() {
        setI18nExtension();
        return i18nExtension.getTranslations();
    }

    public void setTranslations(Map<String, ProductOptionTranslation> translations) {
        setI18nExtension();
        i18nExtension.setTranslations(translations);
    }

    public String getLabel() {
        if (getTranslations() != null && BroadleafRequestContext.hasLocale()) {
            Locale locale = BroadleafRequestContext.getBroadleafRequestContext().getLocale();

            // Search for translation based on locale
            String localeCode = locale.getLocaleCode();
            if (localeCode != null) {
                ProductOptionTranslation translation = getTranslations().get(localeCode);
                if (translation != null && translation.getLabel() != null) {
                    return translation.getLabel();
                }
            }

            // try just the language
            String languageCode = LocaleUtil.findLanguageCode(locale);
            if (languageCode != null && !localeCode.equals(languageCode)) {
                ProductOptionTranslation translation = getTranslations().get(languageCode);
                if (translation != null && translation.getLabel() != null) {
                    return translation.getLabel();
                }
            }
        }
        return label;
    }

    protected void setI18nExtension() {
        if (i18nExtension == null) {
            i18nExtension = new I18NProductOptionImpl();
        }
    }
}

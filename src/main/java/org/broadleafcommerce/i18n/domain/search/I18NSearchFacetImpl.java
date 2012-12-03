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

package org.broadleafcommerce.i18n.domain.search;

import org.broadleafcommerce.common.locale.domain.LocaleImpl;
import org.broadleafcommerce.common.presentation.AdminPresentationMap;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.MapKey;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Andre Azzolini (apazzolini)
 */
public class I18NSearchFacetImpl implements I18NSearchFacet {
    
    @ManyToMany(targetEntity = SearchFacetTranslationImpl.class)
    @JoinTable(name = "BLC_SEARCH_FACET_TRANSLATION_XREF",
            joinColumns = @JoinColumn(name = "SEARCH_FACET_ID", referencedColumnName = "SEARCH_FACET_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRANSLATION_ID", referencedColumnName = "TRANSLATION_ID"))
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    @MapKey(columns = { @Column(name = "MAP_KEY", nullable = false) })
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
    @BatchSize(size = 10)
    @AdminPresentationMap(
            friendlyName = "searchFacetImpl_Translations",
            dataSourceName = "searchFacetTranslationDS",
            keyPropertyFriendlyName = "TranslationsImpl_Key",
            deleteEntityUponRemove = true,
            mapKeyOptionEntityClass = LocaleImpl.class,
            mapKeyOptionEntityDisplayField = "friendlyName",
            mapKeyOptionEntityValueField = "localeCode"

    )
    protected Map<String, SearchFacetTranslation> translations = new HashMap<String,SearchFacetTranslation>();

    @Override
    public Map<String, SearchFacetTranslation> getTranslations() {
        return translations;
    }

    @Override
    public void setTranslations(Map<String, SearchFacetTranslation> translations) {
        this.translations = translations;
    }
    
}

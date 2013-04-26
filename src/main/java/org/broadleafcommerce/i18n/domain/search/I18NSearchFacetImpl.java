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
package org.broadleafcommerce.i18n.domain.search;

import org.broadleafcommerce.common.locale.domain.LocaleImpl;
import org.broadleafcommerce.common.presentation.AdminPresentationMap;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;


/**
 * @author Andre Azzolini (apazzolini)
 */
public class I18NSearchFacetImpl implements I18NSearchFacet {
    
    @ManyToMany(targetEntity = SearchFacetTranslationImpl.class)
    @JoinTable(name = "BLC_SEARCH_FACET_TRXREF",
            joinColumns = @JoinColumn(name = "SEARCH_FACET_ID", referencedColumnName = "SEARCH_FACET_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRANSLATION_ID", referencedColumnName = "TRANSLATION_ID"))
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    @MapKeyColumn(name = "MAP_KEY")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
    @BatchSize(size = 10)
    @AdminPresentationMap(
            friendlyName = "searchFacetImpl_Translations",
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

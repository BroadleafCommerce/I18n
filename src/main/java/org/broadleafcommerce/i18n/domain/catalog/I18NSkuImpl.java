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
package org.broadleafcommerce.i18n.domain.catalog;

import org.broadleafcommerce.common.locale.domain.LocaleImpl;
import org.broadleafcommerce.common.presentation.AdminPresentationMap;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.MapKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Andre Azzolini (apazzolini)
 */
@Embeddable
public class I18NSkuImpl implements I18NSku {
    
    @ManyToMany(targetEntity = SkuTranslationImpl.class)
    @JoinTable(name = "BLC_SKU_TRANSLATION_XREF",
            joinColumns = @JoinColumn(name = "SKU_ID", referencedColumnName = "SKU_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRANSLATION_ID", referencedColumnName = "TRANSLATION_ID"))
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    @MapKey(columns = {@Column(name = "MAP_KEY", nullable = false)})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "blStandardElements")
    @BatchSize(size = 10)
    @AdminPresentationMap(
            friendlyName = "SkuImpl_Translations",
            //dataSourceName = "skuTranslationDS",
            keyPropertyFriendlyName = "TranslationsImpl_Key",
            deleteEntityUponRemove = true,
            mapKeyOptionEntityClass = LocaleImpl.class,
            mapKeyOptionEntityDisplayField = "friendlyName",
            mapKeyOptionEntityValueField = "localeCode"
    )
    protected Map<String, SkuTranslation> translations = new HashMap<String, SkuTranslation>();

    @Override
    public Map<String, SkuTranslation> getTranslations() {
        return translations;
    }

    @Override
    public void setTranslations(Map<String, SkuTranslation> translations) {
        this.translations = translations;
    }

}

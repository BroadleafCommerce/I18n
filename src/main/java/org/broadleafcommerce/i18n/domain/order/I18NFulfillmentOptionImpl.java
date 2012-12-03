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

package org.broadleafcommerce.i18n.domain.order;

import org.broadleafcommerce.common.presentation.AdminPresentationMap;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

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
public class I18NFulfillmentOptionImpl implements I18NFulfillmentOption {

    @ManyToMany(targetEntity = FulfillmentOptionTranslationImpl.class)
    @JoinTable(name = "BLC_FULFILLMENT_OPTION_TRANSLATION_XREF",
            joinColumns = @JoinColumn(name = "FULFILLMENT_OPTION_ID", referencedColumnName = "FULFILLMENT_OPTION_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRANSLATION_ID", referencedColumnName = "TRANSLATION_ID"))
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    @org.hibernate.annotations.MapKey(columns = { @Column(name = "MAP_KEY", nullable = false) })
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
    @BatchSize(size = 10)
    @AdminPresentationMap(
            friendlyName = "FulfillmentOptionImpl_Translations",
            dataSourceName = "productOptionTranslationDS",
            keyPropertyFriendlyName = "TranslationsImpl_Key",
            deleteEntityUponRemove = true,
            mapKeyOptionEntityClass = FulfillmentOptionTranslationImpl.class,
            mapKeyOptionEntityDisplayField = "friendlyName",
            mapKeyOptionEntityValueField = "translationsKey"

    )
    protected Map<String, FulfillmentOptionTranslation> translations = new HashMap<String,FulfillmentOptionTranslation>();
    
    @Override
    public Map<String, FulfillmentOptionTranslation> getTranslations() {
        return translations;
    }
    
    @Override
    public void setTranslations(Map<String, FulfillmentOptionTranslation> translations) {
        this.translations = translations;
    }
    
}

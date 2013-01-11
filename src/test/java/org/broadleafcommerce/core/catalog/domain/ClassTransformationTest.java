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

package org.broadleafcommerce.core.catalog.domain;

import org.broadleafcommerce.core.order.domain.FulfillmentOptionImpl;
import org.broadleafcommerce.core.search.domain.SearchFacet;
import org.broadleafcommerce.core.search.domain.SearchFacetImpl;
import org.broadleafcommerce.test.BaseTest;
import org.testng.annotations.Test;

import org.broadleafcommerce.i18n.domain.catalog.CategoryTranslation;
import org.broadleafcommerce.i18n.domain.catalog.I18NCategory;
import org.broadleafcommerce.i18n.domain.catalog.I18NProductOption;
import org.broadleafcommerce.i18n.domain.catalog.I18NProductOptionValue;
import org.broadleafcommerce.i18n.domain.catalog.I18NSku;
import org.broadleafcommerce.i18n.domain.catalog.ProductOptionTranslation;
import org.broadleafcommerce.i18n.domain.catalog.ProductOptionValueTranslation;
import org.broadleafcommerce.i18n.domain.catalog.SkuTranslation;
import org.broadleafcommerce.i18n.domain.order.FulfillmentOptionTranslation;
import org.broadleafcommerce.i18n.domain.order.I18NFulfillmentOption;
import org.broadleafcommerce.i18n.domain.search.I18NSearchFacet;
import org.broadleafcommerce.i18n.domain.search.SearchFacetTranslation;

import java.util.HashMap;
import java.util.Map;

/**
 * This test suite ensures that the JPA transformations are successfully performed
 * 
 * @author Andre Azzolini (apazzolini)
 */
public class ClassTransformationTest extends BaseTest {

    static {
        getModuleContexts().add("bl-i18n-applicationContext.xml");
    }

    @Test(groups = { "testTransformations" })
    public void testSeoTransformations() {
        ProductOptionValue opt = new ProductOptionValueImpl();
        Map<String, ProductOptionValueTranslation> poTransMap = new HashMap<String, ProductOptionValueTranslation>();
        ((I18NProductOptionValue) opt).setTranslations(poTransMap);
        assert ((I18NProductOptionValue) opt).getTranslations().equals(poTransMap);

        ProductOption po = new ProductOptionImpl();
        Map<String, ProductOptionTranslation> ptrans = new HashMap<String, ProductOptionTranslation>();
        ((I18NProductOption) po).setTranslations(ptrans);
        assert ((I18NProductOption) po).getTranslations().equals(ptrans);

        Category category = new CategoryImpl();
        Map<String, CategoryTranslation> catTransMap = new HashMap<String, CategoryTranslation>();
        ((I18NCategory) category).setTranslations(catTransMap);
        assert ((I18NCategory) category).getTranslations().equals(catTransMap);

        Sku sku = new SkuImpl();
        Map<String, SkuTranslation> skuTransMap = new HashMap<String, SkuTranslation>();
        ((I18NSku) sku).setTranslations(skuTransMap);
        assert ((I18NSku) sku).getTranslations().equals(skuTransMap);

        FulfillmentOptionImpl fulfil = new FulfillmentOptionImpl();
        Map<String, FulfillmentOptionTranslation> fulFilTransMap = new HashMap<String, FulfillmentOptionTranslation>();
        ((I18NFulfillmentOption) fulfil).setTranslations(fulFilTransMap);
        assert ((I18NFulfillmentOption) fulfil).getTranslations().equals(fulFilTransMap);

        SearchFacet search = new SearchFacetImpl();
        Map<String, SearchFacetTranslation> searchMap = new HashMap<String, SearchFacetTranslation>();
        ((I18NSearchFacet) search).setTranslations(searchMap);
        assert ((I18NSearchFacet) search).getTranslations().equals(searchMap);
    }
}
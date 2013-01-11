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

/**
 * Author: jerryocanas
 * Date: 9/19/12
 */
public interface ProductOptionValueTranslation {

    /**
     * Returns the id
     *
     * @return
     */
    public Long getId();

    /**
     * Sets the id
     *
     * @param id
     */
    public void setId(Long id);

    /**
     * Returns the attribute value
     *
     * @return
     */
    public String getAttributeValue();

    /**
     * Set the attribue value
     *
     * @param attributeValue
     */
    public void setAttributeValue(String attributeValue);

}

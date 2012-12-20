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
package com.broadleafcommerce.i18n.domain.search;

/**
 * Author: jerryocanas
 * Date: 9/25/12
 */
public interface SearchFacetTranslation {

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
     * Return the label
     *
     * @return
     */
    public String getLabel();

    /**
     * Sets the label
     *
     * @param label
     */
    public void setLabel(String label);

}

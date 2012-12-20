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
package com.broadleafcommerce.i18n.domain.order;

import org.broadleafcommerce.common.locale.domain.Locale;

/**
 * Author: jerryocanas
 * Date: 9/25/12
 */
public interface FulfillmentOptionTranslation {

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
     * Returns the long description
     *
     * @return
     */
    public String getLongDescription();

    /**
     * Return the name
     *
     * @return
     */
    public String getName();

    /**
     * Sets the name
     *
     * @param name
     */
    public void setName(String name);

    /**
     * Set the long description
     *
     * @param longDescription
     */
    public void setLongDescription(String longDescription);

}

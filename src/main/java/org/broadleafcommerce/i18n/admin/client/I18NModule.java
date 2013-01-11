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

package org.broadleafcommerce.i18n.admin.client;

import org.broadleafcommerce.openadmin.client.AbstractModule;
import org.broadleafcommerce.openadmin.client.BLCMain;
import org.broadleafcommerce.openadmin.client.i18nConstants;
import org.broadleafcommerce.openadmin.client.reflection.ModuleFactory;

import org.broadleafcommerce.i18n.admin.client.presenter.OneToOneProductSkuModiferView;
import org.broadleafcommerce.i18n.admin.client.presenter.OneToOneProductSkuModifiedPresenter;
import org.broadleafcommerce.i18n.admin.client.presenter.ProductOptionModiferView;
import org.broadleafcommerce.i18n.admin.client.presenter.ProductOptionModifiedPresenter;
import com.google.gwt.core.client.GWT;

/**
 * 
 * @author ppatel
 * 
 */
public class I18NModule extends AbstractModule {

    @Override
    public void onModuleLoad() {
        BLCMain.getMessageManager().addConstants(GWT.<i18nConstants> create(I18NMessages.class));
        setModuleTitle(BLCMain.getMessageManager().getString("merchandisingModuleTitle"));
        setModuleKey("BLCMerchandising");

        ModuleFactory moduleFactory = ModuleFactory.getInstance();
        OneToOneProductSkuModiferView view = new OneToOneProductSkuModiferView();
        OneToOneProductSkuModifiedPresenter presenter = new OneToOneProductSkuModifiedPresenter();
        presenter.setDisplay(view);
        moduleFactory.put("org.broadleafcommerce.admin.client.presenter.catalog.product.OneToOneProductSkuPresenter", presenter);
       
        ProductOptionModiferView productOptionview = new ProductOptionModiferView();
        ProductOptionModifiedPresenter productOptionPresenter = new  ProductOptionModifiedPresenter();
        productOptionPresenter.setDisplay(productOptionview);
        moduleFactory.put("org.broadleafcommerce.admin.client.presenter.catalog.product.ProductOptionPresenter", productOptionPresenter);

        registerModule();
    }

}
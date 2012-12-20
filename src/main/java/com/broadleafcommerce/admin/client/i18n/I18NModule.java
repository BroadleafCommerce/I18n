/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.broadleafcommerce.admin.client.i18n;

import org.broadleafcommerce.openadmin.client.AbstractModule;
import org.broadleafcommerce.openadmin.client.BLCMain;
import org.broadleafcommerce.openadmin.client.i18nConstants;
import org.broadleafcommerce.openadmin.client.reflection.ModuleFactory;

import com.broadleafcommerce.admin.client.i18n.presenter.OneToOneProductSkuModiferView;
import com.broadleafcommerce.admin.client.i18n.presenter.OneToOneProductSkuModifiedPresenter;
import com.broadleafcommerce.admin.client.i18n.presenter.ProductOptionModiferView;
import com.broadleafcommerce.admin.client.i18n.presenter.ProductOptionModifiedPresenter;
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
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

package org.broadleafcommerce.admin.client.presenter.i18n;

import org.broadleafcommerce.admin.client.datasource.catalog.category.MediaMapDataSourceFactory;
import org.broadleafcommerce.admin.client.datasource.i18n.ProductOptionTranslationMapDataSourceFactory;
import org.broadleafcommerce.admin.client.presenter.catalog.product.ProductOptionPresenter;
import org.broadleafcommerce.openadmin.client.BLCMain;
import org.broadleafcommerce.openadmin.client.datasource.dynamic.ListGridDataSource;
import org.broadleafcommerce.openadmin.client.presenter.entity.ModifiedPresenterAdapter;
import org.broadleafcommerce.openadmin.client.presenter.entity.SubPresentable;
import org.broadleafcommerce.openadmin.client.presenter.structure.MapStructurePresenter;
import org.broadleafcommerce.openadmin.client.setup.AsyncCallbackAdapter;
import org.broadleafcommerce.openadmin.client.setup.PresenterSetupItem;
import org.broadleafcommerce.openadmin.client.view.dynamic.dialog.MapStructureEntityEditDialog;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;

import java.util.HashMap;

/**
 * @author 
 */
public class ProductOptionModifiedPresenter extends ModifiedPresenterAdapter {

    protected SubPresentable translationsPresenter;

    protected HashMap<String, Object> library = new HashMap<String, Object>(10);

    public ProductOptionModifiedPresenter() {
        super();
    }

    @Override
    public void bind() {
        translationsPresenter.bind();
        translationsPresenter.setReadOnly(false);
        ((ProductOptionPresenter) getParentPresenter()).getDisplay().getProductOptionValueDisplay().getGrid().addSelectionChangedHandler(new SelectionChangedHandler() {
            @Override
            public void onSelectionChanged(SelectionEvent event) {
                ListGridRecord selectedRecord = event.getSelectedRecord();
                if (event.getState()) {
                    translationsPresenter.load(selectedRecord, getParentPresenter().getPresenterSequenceSetupManager().getDataSource("productOptionValueDS"), null);
                }
            }
        });
    }

    @Override
    public void changeSelection(final Record selectedRecord) {

    }

    @Override
    public void setup() {
        getParentPresenter().getPresenterSequenceSetupManager().addOrReplaceItem(new PresenterSetupItem("productOptionTranslationMapDS", new ProductOptionTranslationMapDataSourceFactory(getParentPresenter()), new AsyncCallbackAdapter() {
            @Override
            public void onSetupSuccess(DataSource result) {
                translationsPresenter = new MapStructurePresenter("", ((ProductOptionModiferView) getDisplay()).getTranslationsDisplay(), getMediaEntityView(), BLCMain.getMessageManager().getString("newMediaTitle"));
                translationsPresenter.setDataSource((ListGridDataSource) result, new String[] {}, new Boolean[] {});
            }

            protected MapStructureEntityEditDialog getMediaEntityView() {
                MapStructureEntityEditDialog mapEntityAdd2;
                mapEntityAdd2 = new MapStructureEntityEditDialog(MediaMapDataSourceFactory.MAPSTRUCTURE, getParentPresenter().getPresenterSequenceSetupManager().getDataSource("productOptionLocaleDS"), "friendlyName", "localeCode");
                mapEntityAdd2.setShowMedia(false);
                return mapEntityAdd2;
            }
        }));

    }

}
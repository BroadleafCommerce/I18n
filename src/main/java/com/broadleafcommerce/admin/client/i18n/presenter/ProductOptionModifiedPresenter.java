/*
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
package com.broadleafcommerce.admin.client.i18n.presenter;

import org.broadleafcommerce.admin.client.datasource.catalog.category.MediaMapDataSourceFactory;
import org.broadleafcommerce.admin.client.presenter.catalog.product.ProductOptionPresenter;
import org.broadleafcommerce.openadmin.client.BLCMain;
import org.broadleafcommerce.openadmin.client.datasource.dynamic.ListGridDataSource;
import org.broadleafcommerce.openadmin.client.presenter.entity.ModifiedPresenterAdapter;
import org.broadleafcommerce.openadmin.client.presenter.entity.SubPresentable;
import org.broadleafcommerce.openadmin.client.presenter.structure.MapStructurePresenter;
import org.broadleafcommerce.openadmin.client.setup.AsyncCallbackAdapter;
import org.broadleafcommerce.openadmin.client.setup.PresenterSetupItem;
import org.broadleafcommerce.openadmin.client.view.dynamic.dialog.MapStructureEntityEditDialog;

import com.broadleafcommerce.admin.client.i18n.datasource.ProductOptionTranslationMapDataSourceFactory;
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
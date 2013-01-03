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
package com.broadleafcommerce.i18n.admin.client.presenter;

import org.broadleafcommerce.admin.client.datasource.catalog.category.MediaMapDataSourceFactory;
import org.broadleafcommerce.admin.client.presenter.catalog.product.OneToOneProductSkuPresenter;
import org.broadleafcommerce.openadmin.client.BLCMain;
import org.broadleafcommerce.openadmin.client.datasource.dynamic.ListGridDataSource;
import org.broadleafcommerce.openadmin.client.presenter.entity.ModifiedPresenterAdapter;
import org.broadleafcommerce.openadmin.client.presenter.entity.SubPresentable;
import org.broadleafcommerce.openadmin.client.presenter.structure.MapStructurePresenter;
import org.broadleafcommerce.openadmin.client.setup.AsyncCallbackAdapter;
import org.broadleafcommerce.openadmin.client.setup.PresenterSetupItem;
import org.broadleafcommerce.openadmin.client.view.dynamic.dialog.MapStructureEntityEditDialog;

import com.broadleafcommerce.i18n.admin.client.datasource.SkuTranslationsMapDataSourceFactory;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;

import java.util.HashMap;

/**
 * @author 
 */
public class OneToOneProductSkuModifiedPresenter extends ModifiedPresenterAdapter {

    protected SubPresentable translationsPresenter;

    protected HashMap<String, Object> library = new HashMap<String, Object>(10);

    public OneToOneProductSkuModifiedPresenter() {
        super();
    }

    @Override
    public void bind() {
        translationsPresenter.bind();
        ((OneToOneProductSkuPresenter) getParentPresenter()).getDisplay().getSkusDisplay().getGrid().addSelectionChangedHandler(new SelectionChangedHandler() {
            @Override
            public void onSelectionChanged(SelectionEvent event) {
                if (event.getState()) {
                    translationsPresenter.load(event.getSelectedRecord(), getParentPresenter().getPresenterSequenceSetupManager().getDataSource("skusDS"), null);
                }
            }
        });
    }

    @Override
    public void setup() {
        getParentPresenter().getPresenterSequenceSetupManager().addOrReplaceItem(new PresenterSetupItem("skuTranslationMapDS", new SkuTranslationsMapDataSourceFactory(getParentPresenter()), new AsyncCallbackAdapter() {
            @Override
            public void onSetupSuccess(DataSource result) {
                translationsPresenter = new MapStructurePresenter("", ((OneToOneProductSkuModiferView) getDisplay()).getTranslationsDisplay(), getMediaEntityView(), BLCMain.getMessageManager().getString("newMediaTitle"));
                translationsPresenter.setDataSource((ListGridDataSource) result, new String[]{}, new Boolean[]{});
            }

            protected MapStructureEntityEditDialog getMediaEntityView() {
                MapStructureEntityEditDialog mapEntityAdd;
                mapEntityAdd = new MapStructureEntityEditDialog(MediaMapDataSourceFactory.MAPSTRUCTURE, getParentPresenter().getPresenterSequenceSetupManager().getDataSource("skuLocaleDS"), "friendlyName", "localeCode");
                mapEntityAdd.setShowMedia(true);
                mapEntityAdd.setMediaField("url");
                return mapEntityAdd;
            }
        }));
    }

    @Override
    public void changeSelection(Record selectedRecord) {
        // No changeSelection handle for this module
    }

}
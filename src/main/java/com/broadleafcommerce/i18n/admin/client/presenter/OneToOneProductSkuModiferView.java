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

import org.broadleafcommerce.openadmin.client.BLCMain;
import org.broadleafcommerce.openadmin.client.view.ViewModifierAdapter;
import org.broadleafcommerce.openadmin.client.view.dynamic.SubItemView;
import org.broadleafcommerce.openadmin.client.view.dynamic.form.FormBuilder;
import org.broadleafcommerce.openadmin.client.view.dynamic.form.FormOnlyView;
import org.broadleafcommerce.openadmin.client.view.dynamic.grid.GridStructureDisplay;
import org.broadleafcommerce.openadmin.client.view.dynamic.grid.GridStructureView;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.layout.Layout;

import java.util.List;

/**
 * @author ppatel
 */
public class OneToOneProductSkuModiferView extends ViewModifierAdapter {

    protected GridStructureView translationsDisplay;

    public OneToOneProductSkuModiferView() {
        super();
    }

    @Override
    public void build(List<DataSource> dataSourcesList) {
        translationsDisplay = new GridStructureView(BLCMain.getMessageManager().getString("productOptionImpl_Translations"), false, true);
        SubItemView topTabSet = (SubItemView) FormBuilder.findMemberById((Layout) getParentView(), "productSkuTabSubItemView");
        ((FormOnlyView) topTabSet.getFormOnlyDisplay()).addMember(translationsDisplay);
        
    }

    public GridStructureDisplay getTranslationsDisplay() {
        return translationsDisplay;
    }

}

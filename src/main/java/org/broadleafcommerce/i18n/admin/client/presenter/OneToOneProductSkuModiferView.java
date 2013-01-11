/*
 * Copyright 2008-2012 the original author or authors.
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
package org.broadleafcommerce.i18n.admin.client.presenter;

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

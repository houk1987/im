/**
 * $RCSfile: ,v $
 * $Revision: $
 * $Date: $
 * 
 * Copyright (C) 2004-2013 Jive Software. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ui.tree.renderer;



import com.imService.contact.Contact;
import com.ui.tree.ContactItem;

import javax.swing.*;
import java.awt.*;

/**
 * Extends ContactItem because ContactItem is a JPanel
 * When ContactItem will be redesigned, and won't implement JPanel, we will
 * extend JPanel here directly and customize here.
 *
 */
public class JContactItemRenderer extends ContactItem implements ListCellRenderer {
	JPanelRenderer basicPanelRenderer;

	public JContactItemRenderer() {
		super(null);
        setOpaque(true);
		basicPanelRenderer = new JPanelRenderer();
	}



	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, 
			boolean isSelected, boolean cellHasFocus) {
		
		basicPanelRenderer.getListCellRendererComponent(list, this, index, isSelected, cellHasFocus);
		ContactItem renderItem = (ContactItem)value;
		setFocusable(false);
        setHeadIconLabel(renderItem.getHeadIconLabel());
        setStatusIcon(renderItem.getStatusIcon());
        setUserName(renderItem.getUserName());
		return this;
	}
}

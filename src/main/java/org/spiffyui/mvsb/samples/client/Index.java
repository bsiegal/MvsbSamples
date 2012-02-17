/*******************************************************************************
 *
 * Copyright 2011 Spiffy UI Team   
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
 *
 ******************************************************************************/
package org.spiffyui.mvsb.samples.client;

import java.util.HashMap;
import java.util.Map;

import org.spiffyui.client.MainFooter;
import org.spiffyui.client.MainHeader;
import org.spiffyui.client.widgets.dialog.ConfirmDialog;
import org.spiffyui.client.widgets.multivaluesuggest.MultivalueSuggestBox;
import org.spiffyui.client.widgets.multivaluesuggest.MultivalueSuggestBoxBase;
import org.spiffyui.client.widgets.multivaluesuggest.MultivalueSuggestRESTHelper;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * This class is the main entry point for our GWT module.
 */
public class Index implements EntryPoint
{
    private static final SpiffyUiHtml STRINGS = (SpiffyUiHtml) GWT.create(SpiffyUiHtml.class);

    private static Index g_index;

    /**
     * The Index page constructor
     */
    public Index()
    {
        g_index = this;
    }


    @Override
    public void onModuleLoad()
    {
        /*
         This is where we load our module and create our dynamic controls.  The MainHeader
         displays our title bar at the top of our page.
         */
        MainHeader header = new MainHeader();
        header.setHeaderTitle("Hello Spiffy MvsbSamples!");
        
        /*
         The main footer shows our message at the bottom of the page.
         */
        MainFooter footer = new MainFooter();
        footer.setFooterString("MvsbSamples was built with the <a href=\"http://www.spiffyui.org\">Spiffy UI Framework</a>");
        
        /*
         This HTMLPanel holds most of our content.
         MainPanel_html was built in the HTMLProps task from MainPanel.html, which allows you to use large passages of html
         without having to string escape them.
         */
        HTMLPanel panel = new HTMLPanel(STRINGS.MainPanel_html());
        
        RootPanel.get("mainContent").add(panel);
        
        /*
         * so that spellcheck is not done on a selected crayon color of "Screamin' Green"
         */
        Element panelEle = panel.getElement();
        panelEle.setAttribute("spellcheck", "false");
        
        addSingleValued(panel);
        addMultiValued(panel);
        addMultiValuedFancy(panel);
        addLocalValues(panel);
    }

    private void addSingleValued(HTMLPanel panel)
    {
        final MultivalueSuggestBox msb = new MultivalueSuggestBox(new MultivalueSuggestRESTHelper("TotalSize", "Options", "DisplayName", "Value") {
            
            @Override
            public String buildUrl(String q, int indexFrom, int indexTo)
            {
                return "multivaluesuggestboxexample/colors?q=" + q + "&indexFrom=" + indexFrom + "&indexTo=" + indexTo;
            }
        }, false);
        
        panel.add(msb, "singleValued");
        
        createShowValuesButton(panel, msb, "singleValued");
    }

    /**
     * Add a button to the panel for showing values of the suggest box
     * @param panel - HTMLPanel to add to
     * @param msb - the suggest box
     * @param containerId - where to add the button
     */
    private void createShowValuesButton(HTMLPanel panel, final MultivalueSuggestBoxBase msb, String containerId)
    {
        final Button b = new Button("Show values");
        panel.add(b, containerId);
        b.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event)
            {
                showValues(msb, b);   
            }
        });
    }
    
    private void addMultiValued(HTMLPanel panel)
    {
        final MultivalueSuggestBox msb = new MultivalueSuggestBox(new MultivalueSuggestRESTHelper("TotalSize", "Options", "DisplayName", "Value") {
            
            @Override
            public String buildUrl(String q, int indexFrom, int indexTo)
            {
                return "multivaluesuggestboxexample/colors?q=" + q + "&indexFrom=" + indexFrom + "&indexTo=" + indexTo;
            }
        }, true);
        
        /*
         * have a default value of Electric Lime
         */
        Map<String, String> valueMap = new HashMap<String, String>();
        valueMap.put("Electric Lime", "#CEFF1D");
        msb.setValueMap(valueMap);
        
        panel.add(msb, "multiValued");
        
        createShowValuesButton(panel, msb, "multiValued");
    }

    private void addMultiValuedFancy(HTMLPanel panel)
    {
        final FancyAutocompleter msb = new FancyAutocompleter(new MultivalueSuggestRESTHelper("TotalSize", "Options", "DisplayName", "Value") {
            
            @Override
            public String buildUrl(String q, int indexFrom, int indexTo)
            {
                return "multivaluesuggestboxexample/colors?q=" + q + "&indexFrom=" + indexFrom + "&indexTo=" + indexTo;
            }
        }, true);
        msb.getFeedback().addStyleName("msg-feedback");
        msb.setPageSize(8); //since each value takes up more space, let's cut the size.
        
        panel.add(msb, "multiValuedFancy");
        
        createShowValuesButton(panel, msb, "multiValuedFancy");
        
    }
        
    private void addLocalValues(HTMLPanel panel)
    {
        final LocalSuggestBox msb = new LocalSuggestBox(true);        
        panel.add(msb, "localValues");
        
        createShowValuesButton(panel, msb, "localValues");
    }

    private void showValues(MultivalueSuggestBoxBase msb, Button b)
    {
        Map<String, String> values = msb.getValueMap();
        ConfirmDialog c = new ConfirmDialog("mvsb-results-dialog", "Value Map");
        c.setAutoHideEnabled(true);
        c.setModal(false);
        
        StringBuffer sb = new StringBuffer();
        for (String key : values.keySet()) {
            sb.append("<div style=\"background-color:" + values.get(key) + "\">" + key + "</div>");
        }
        c.replaceDialogBodyContents(new HTML(sb.toString()));
        c.addButton("mvsb-dialog-ok", "Close", "OK");
        c.showRelativeTo(b);
    }
}

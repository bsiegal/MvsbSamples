/*******************************************************************************
 *
 * Copyright 2012 Spiffy UI Team   
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

import org.spiffyui.client.widgets.multivaluesuggest.MultivalueSuggestRESTHelper;

import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * A subclass of MultivalueSuggestBox that shows suggestions in a fancier way
 * and also shows the selected items in a fancier way.
 */
public class FancierAutocompleter extends FancyAutocompleter 
{

    /**
     * Constructor
     * @param restHelper the REST helper for getting remote values
     * @param isMultivalued
     *                   true if this suggest box supports multiple values and false otherwise
     */
    public FancierAutocompleter(MultivalueSuggestRESTHelper restHelper, boolean isMultivalued) 
    {
        super(restHelper, isMultivalued);
    }
    
    /**
     * Constructor
     * @param restHelper the REST helper for getting remote values
     * @param isMultivalued
     *                   true if this suggest box supports multiple values and false otherwise
     * @param placeFormFeedback
     *                   true if this control should place a form feedback and false otherwise
     */
    public FancierAutocompleter(MultivalueSuggestRESTHelper restHelper, boolean isMultivalued, boolean placeFormFeedback) 
    {
        super(restHelper, isMultivalued, placeFormFeedback);
    }
    
    
    /**
     * Create and return a SelectedItem populated with the option
     * @param option - an Option bean that will be down cast to a FancyOption
     * @return the SelectedItem to be pushed
     */
    protected SelectedItem createSelectedItem(Option option)
    {
        return new FancySelectedItem(HTMLPanel.createUniqueId(), (FancyOption) option);
    }
    
    /**
     * A subclass of SelectedItem that uses FancyOption to generate a fancy selected item bubble 
     */
    public class FancySelectedItem extends SelectedItem
    {
        /**
         * Constructor
         * @param id - the elements unique id
         * @param option - a FancyOption to create the HTML
         */
        public FancySelectedItem(String id, FancyOption option)
        {
            super(id, option, "<span class=\"spiffy-mvsb-item\" id=\"" + id + 
                    "_main\"><span  class=\"facSelectedRgb\" style=\"float: left; background-color: rgb" + option.getRgb() + "\"></span>" +
                        option.getName() +
                    "</span>");

        }
        
    }    
}

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

import java.util.ArrayList;
import java.util.List;

import org.spiffyui.client.rest.RESTObjectCallBack;
import org.spiffyui.client.widgets.multivaluesuggest.MultivalueSuggestBoxBase;

public class LocalSuggestBox extends MultivalueSuggestBoxBase
{
    private static final List<String> BASIC_COLORS;
    
    static {
        BASIC_COLORS = new ArrayList<String>();
        BASIC_COLORS.add("Red");
        BASIC_COLORS.add("Orange");
        BASIC_COLORS.add("Yellow");
        BASIC_COLORS.add("Green");
        BASIC_COLORS.add("Blue");
        BASIC_COLORS.add("Purple");
        BASIC_COLORS.add("White");
        BASIC_COLORS.add("Brown");
        BASIC_COLORS.add("Black");
    }
    /**
     * Constructor
     * @param isMultivalued - whether or not to allow multiple values
     */
    public LocalSuggestBox(boolean isMultivalued)
    {
        super(null, isMultivalued);
    }

    @Override
    protected void queryOptions(String query, int from, int to, RESTObjectCallBack<OptionResultSet> callback)
    {
        OptionResultSet options = new OptionResultSet(BASIC_COLORS.size()); // this size isn't correct
        int totalSize = 0;
        for (String color : BASIC_COLORS) {
            if (color.toLowerCase().indexOf(query.toLowerCase()) >= 0 || query.equals("*")) {
                Option option = createOption(color);
                options.addOption(option);
                totalSize++;
            }
        }
        options.setTotalSize(totalSize);
        callback.success(options);
    }
    
    private Option createOption(String color)
    {
        Option option = new Option();
        option.setName(color);
        option.setValue(color);
        return option;
    }
    

}

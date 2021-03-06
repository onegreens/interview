/**
 * Copyright © 2016-2017 The Thingsboard Authors
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
package com.cl.interview.util;


import com.cl.interview.common.ToObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class DaoUtil {

    private DaoUtil() {
    }

    public static <T> List<T> convertDataList(Collection<? extends ToObject<T>> toObjectList) {
        List<T> list = Collections.emptyList();
        if (toObjectList != null && !toObjectList.isEmpty()) {
            list = new ArrayList<>();
            for (ToObject<T> object : toObjectList) {
                list.add(object.toObject());
            }
        }
        return list;
    }

    public static <T> T getData(ToObject<T> data) {
        T object = null;
        if (data != null) {
            object = data.toObject();
        }
        return object;
    }
    


}

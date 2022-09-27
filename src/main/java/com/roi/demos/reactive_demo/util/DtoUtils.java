package com.roi.demos.reactive_demo.util;

import com.roi.demos.reactive_demo.domain.Search;
import com.roi.demos.reactive_demo.domain.SearchDto;
import org.springframework.beans.BeanUtils;

public class DtoUtils {
    public static Search toEntity(SearchDto search){
        Search courseSearch = new Search();
        BeanUtils.copyProperties(search,courseSearch);
        return courseSearch;
    }
}

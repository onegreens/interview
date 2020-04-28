package com.cl.interview.common;

import lombok.Data;

import java.util.List;

@Data
public class MDData {
    public MDData() {
    }

    public MDData(String title, Integer level) {
        this.title = title;
        this.level = level;
    }


    String title;
    Integer level;
    String sequence;
    List<String> block;
    List<MDData> list;

}

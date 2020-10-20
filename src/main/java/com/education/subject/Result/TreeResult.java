package com.education.subject.Result;

import lombok.Data;

import java.util.List;


@Data
public class TreeResult<T> {

        private int code;
        private String msg;
        private List<T> data;


}

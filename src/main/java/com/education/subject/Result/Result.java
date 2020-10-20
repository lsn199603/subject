package com.education.subject.Result;

import lombok.Data;

import java.util.List;

@Data
public class Result<T> {
    private List<T> data;
}

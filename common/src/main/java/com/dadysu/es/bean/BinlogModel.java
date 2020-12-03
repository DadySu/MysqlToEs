package com.dadysu.es.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BinlogModel {

    private String tableName;

    private String method;

    private Serializable[] row;
}

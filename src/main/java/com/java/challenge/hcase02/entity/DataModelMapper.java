package com.java.challenge.hcase02.entity;

import com.java.challenge.hcase02.dto.DataModel;

import java.util.ArrayList;
import java.util.List;

public class DataModelMapper {

    public static List<DataModelEntity> mapDataModelToEntity(List<DataModel> dataModel) {

        List<DataModelEntity> dataModelEntityList = new ArrayList<>();
        dataModel.forEach(data -> {
            DataModelEntity dataModelEntity = new DataModelEntity();
            dataModelEntity.setCreatedDate(data.getCreatedDate());
            dataModelEntity.setMd5HashValue(data.getMd5HashValue());
            dataModelEntity.setRandomInt(data.getRandomInt());
            dataModelEntity.setMd5Last2Char(data.getMd5Last2Char());

            dataModelEntityList.add(dataModelEntity);
        });

        return dataModelEntityList;
    }
}
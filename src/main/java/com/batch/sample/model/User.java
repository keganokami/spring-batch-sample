package com.batch.sample.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;

    public void judgeAge(){
        if(20 > age){
            String errorMessage = String.format(
                    "20才未満は登録できません::id={%d},name={%s},age={%d}"
                    ,getId(), getName(),getAge());
            throw new RuntimeException(errorMessage);
        }
    }
}

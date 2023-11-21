package com.JEEProject.TableStore.Model;

import lombok.Getter;

@Getter
public enum ORDERSTATE {
    WAITING("Đang chờ xử lý"),
    DONE("Đã chấp nhận"),
    CANCEL("Đã hủy")
    ;

    private final String value;

    ORDERSTATE(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}

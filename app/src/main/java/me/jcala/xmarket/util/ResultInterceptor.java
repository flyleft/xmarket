package me.jcala.xmarket.util;

import me.jcala.xmarket.data.dto.Result;

public enum ResultInterceptor {
    instance;

    public boolean resultHandler(final Result<?> result){
        if (result==null){
            return false;
        }

        switch (result.getCode()) {
            case 100:
                return true;
            case 99:
                return false;
            default:
                return false;
        }
    }

    public boolean resultHandler(final Result<?> result,int...jump){

        if (result==null){
            return false;
        }

        for (int i:jump){
            if (result.getCode()==i){
                return true;
            }
        }

        switch (result.getCode()) {
            case 100:
                return true;
            case 99:
                return false;
            default:
                return false;
        }
    }
    public boolean resultDataHandler(final Result<?> result){

        if (result==null||result.getData()==null){
            return false;
        }
        switch (result.getCode()) {
            case 100:
                return true;
            case 99:
                return false;
            default:
                return false;
        }
    }
    public boolean resultDataHandler(final Result<?> result,int...jump){

        if (result==null||result.getData()==null){
            return false;
        }

        for (int i:jump){
            if (result.getCode()==i){
                return true;
            }
        }
        switch (result.getCode()) {
            case 100:
                return true;
            case 99:
                return false;
            default:
                return false;
        }
    }
}

package com.diazG.financialmovementsapp.utilities;

import java.util.List;

public class Utilities {

    public static <T> void print(List<T> elements){
        for(T item: elements){
            System.out.println(item);
        }
    }
}

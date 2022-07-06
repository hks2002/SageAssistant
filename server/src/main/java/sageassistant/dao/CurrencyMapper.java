package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import sageassistant.model.CurrencyHistory;

public interface CurrencyMapper {
    List<CurrencyHistory> findCurrencyRate(@Param("Sour") String Sour, @Param("Dest") String Dest,
            @Param("Date") String Date);
}

package com.my.mapper;

import com.my.pojo.Card;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface CardMapper {
    Card selectById(@Param("id") int id);

    List<Card> selectAll(RowBounds rowBounds);

    int insertBatch(@Param("cards") Card[] cards);

    int updateByCardId(@Param("cardId") long cardId);

    int add(Card card);
}
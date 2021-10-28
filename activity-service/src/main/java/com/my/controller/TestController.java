package com.my.controller;

import com.alibaba.fastjson.JSON;
import com.my.mapper.CardMapper;
import com.my.pojo.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RestController
public class TestController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private CardMapper cardMapper;


    @GetMapping("alive")
    public String alive() {
        Card flower = cardMapper.selectById(1);


        Card flower2 = cardMapper.selectById(1);
        return ":" + this.getClass().getClassLoader();
    }

    @PostMapping("pay")
    public String pay() {
        Object card = redisTemplate.boundListOps("ikangdental_card").rightPop();
        Card card1 = JSON.parseObject(card.toString(), Card.class);

        int i = cardMapper.updateByCardId(card1.getCardId());
        if (1 != 1) {
            redisTemplate.boundListOps("ikangdental_card").leftPush(card);
            return "支付失败";
        }

        return "支付成功：" + card1.getCardId();
    }

    @PostMapping("pay2")
    public String pay2() {
        Object card = redisTemplate.boundListOps("ikangdental_card").rightPop();
        Card card1 = JSON.parseObject(card.toString(), Card.class);

        int i = cardMapper.add(card1);
        if (1 != 1) {
            redisTemplate.boundListOps("ikangdental_card").leftPush(card);
            return "支付失败";
        }

        return "支付成功：" + card1.getCardId();
    }

    @GetMapping("init")
    public String init(@RequestParam("num") int num) {

        int res = 0;
        int pageSize = 10;
        int pagecount = num / pageSize;
        for (int i = 0; i < pagecount; i++) {//(c1,c2)->{return  c1.getId()>c2.getId();
            Card[] cards = getCards(pageSize);
            //Card[] cards1 = Arrays.stream(cards).sorted((c1, c2) -> c1.getCardId() > c2.getCardId() ? 1 : -1).skip(i * pageSize).limit(pageSize).collect(Collectors.toList()).toArray(new Card[0]);
            if (cards.length > 0) {
                res = res + cardInsertDb(cards);
                cardInserRedis(cards);
            }
        }

        if (num % pageSize > 0) {
            Card[] cards = getCards(num % pageSize);
            if (cards.length > 0) {
                res = res + cardInsertDb(cards);
                cardInserRedis(cards);
            }
        }
        return "影响行数：" + res;

    }


    private void cardInserRedis(Card[] cards) {
        String[] strings = new String[cards.length];
        for (int i = 0; i < cards.length; i++) {
            Card card = cards[i];
            strings[i] = JSON.toJSONString(card);
        }
        redisTemplate.boundListOps("ikangdental_card").leftPushAll(strings);
    }

    private int cardInsertDb(Card[] cards) {
        return cardMapper.insertBatch(cards);
    }

    private Card[] getCards(int num) {

        List<Card> cards = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            Card card = new Card();
            card.setAsync(0);
            card.setCardState(0);
            card.setCreateTime(new Date());
            card.setPassword(new Random().nextInt(9000) + 1000 + "");
            String s = String.valueOf(System.currentTimeMillis());
            s = s.substring(5, s.length() - 1);
            int i1 = new Random(UUID.randomUUID().toString().hashCode()).nextInt(9000) + 1000;
            int i2 = new Random(UUID.randomUUID().toString().hashCode()).nextInt(9000) + 1000;


            long cardid = Long.parseLong(s + i1 + i2);

            System.out.println("s = " + s + "    i1 = " + i1 + "  i2=" + i2 + "   cardid = " + cardid);
            card.setCardId(cardid);
            cards.add(card);
        }
        return cards.toArray(new Card[0]);
    }
}

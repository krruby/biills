package com.bills.bill;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BillsController {

    @GetMapping("/bills/{id}")
    ResponseEntity<String> getBill(@PathVariable int id){
        return ResponseEntity.ok("Total number of coins : "+ (int)coinChange(id));
    }

    public float coinChange( int amount) {
        float min= howSumBest(amount,coins);
        cnt = new int[]{100,100,100,100};
        return (min==Integer.MAX_VALUE-1)?-1:min;
    }
    int[] bills = new int[]{1,2,5,10,20,50,100};
    float[] coins = new float[]{0.01f ,0.05f, 0.10f, 0.25f};
    int[] cnt = new int[]{100,100,100,100};
    Map<Float, Integer> map= new HashMap<Float, Integer>();

    int howSumBest(float k, float arr[]) {
        if (map.containsKey(k))
            return map.get(k);
        if (k == 0) return 0;
        if (k < 0) return Integer.MAX_VALUE-1;
        int c=Integer.MAX_VALUE-1;
        for (int i = 0; i < arr.length; i++) {
            if(cnt[i]==0) continue;
            cnt[i]--;
            int min=howSumBest(k - arr[i], arr);
            cnt[i]++;
            if(min==Integer.MAX_VALUE) c= Math.min(c,min);
            c= Math.min(c,min+1);
        }
        map.put(k, c);
        return c;
    }
}
